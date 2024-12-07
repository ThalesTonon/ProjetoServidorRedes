package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para operações CRUD com usuários.
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/Usuarios"})
public class UsuarioServlet extends HttpServlet {

    // Configurações do banco de dados
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/meu_banco";
    private static final String DB_USER = "root"; // Substitua por seu usuário
    private static final String DB_PASSWORD = "Root@1234"; // Substitua pela sua senha

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Exibe a lista de usuários
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // HTML inicial
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista de Usuários</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de Usuários</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Nome</th><th>Email</th><th>Ações</th></tr>");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT * FROM usuarios";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");

                    out.println("<tr>");
                    out.println("<td>" + id + "</td>");
                    out.println("<td>" + nome + "</td>");
                    out.println("<td>" + email + "</td>");
                    out.println("<td>");
                    out.println("<a href='editarUsuario.jsp?id=" + id + "'>Editar</a> | ");
                    out.println("<form action='Usuarios' method='POST' style='display:inline;'>");
                    out.println("<input type='hidden' name='action' value='delete'>");
                    out.println("<input type='hidden' name='id' value='" + id + "'>");
                    out.println("<input type='submit' value='Excluir'>");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("</tr>");
                }

                rs.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p>Erro ao conectar ao banco de dados: " + e.getMessage() + "</p>");
            }

            out.println("</table>");
            out.println("<br><a href='novoUsuario.jsp'>Cadastrar Novo Usuário</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Executa operações baseadas no parâmetro "action"
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action"); // create, update, delete

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PrintWriter out = response.getWriter()) {

            if ("create".equalsIgnoreCase(action)) {
                // Criar usuário
                String nome = request.getParameter("nome");
                String email = request.getParameter("email");

                String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, nome);
                stmt.setString(2, email);
                stmt.executeUpdate();
                stmt.close();

                response.sendRedirect("Usuarios"); // Redireciona para a listagem

            } else if ("update".equalsIgnoreCase(action)) {
                // Atualizar usuário
                int id = Integer.parseInt(request.getParameter("id"));
                String nome = request.getParameter("nome");
                String email = request.getParameter("email");

                String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, nome);
                stmt.setString(2, email);
                stmt.setInt(3, id);
                stmt.executeUpdate();
                stmt.close();

                response.sendRedirect("Usuarios"); // Redireciona para a listagem

            } else if ("delete".equalsIgnoreCase(action)) {
                // Excluir usuário
                int id = Integer.parseInt(request.getParameter("id"));

                String sql = "DELETE FROM usuarios WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                stmt.close();

                response.sendRedirect("Usuarios"); // Redireciona para a listagem

            } else {
                out.println("<p>Ação inválida!</p>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<p>Erro ao processar requisição: " + e.getMessage() + "</p>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para operações CRUD com usuários";
    }
}
