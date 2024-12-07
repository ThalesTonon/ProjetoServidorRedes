<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Usuários</title>
</head>
<body>
    <h1>Lista de Usuários</h1>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <%
                try {
                    Class.forName("org.mariadb.jdbc.Driver");
                    String url = "jdbc:mariadb://localhost:3306/meu_banco";
                    String user = "root"; // Altere conforme necessário
                    String password = "Root@1234"; // Altere conforme necessário

                    java.sql.Connection conn = java.sql.DriverManager.getConnection(url, user, password);
                    java.sql.Statement stmt = conn.createStatement();
                    java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String nome = rs.getString("nome");
                        String email = rs.getString("email");
            %>
                        <tr>
                            <td><%= id %></td>
                            <td><%= nome %></td>
                            <td><%= email %></td>
                            <td>
                                <a href="editarUsuario.jsp?id=<%= id %>">Editar</a> |
                                <form action="Usuarios" method="POST" style="display:inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="<%= id %>">
                                    <input type="submit" value="Excluir">
                                </form>
                            </td>
                        </tr>
            <%
                    }

                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
            %>
                    <tr>
                        <td colspan="4">Erro ao carregar usuários: <%= e.getMessage() %></td>
                    </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <br>
    <a href="novoUsuario.jsp">Cadastrar Novo Usuário</a>
</body>
</html>
