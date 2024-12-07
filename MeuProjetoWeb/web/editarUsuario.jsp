<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Usuário</title>
</head>
<body>
    <h1>Editar Usuário</h1>

    <%
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = "";
        String email = "";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://localhost:3306/meu_banco";
            String user = "root"; // Altere conforme necessário
            String password = "Root@1234"; // Altere conforme necessário

            java.sql.Connection conn = java.sql.DriverManager.getConnection(url, user, password);
            java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            stmt.setInt(1, id);

            java.sql.ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nome = rs.getString("nome");
                email = rs.getString("email");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
    %>
        <p>Erro ao carregar dados do usuário: <%= e.getMessage() %></p>
    <%
        }
    %>

    <form action="Usuarios" method="POST">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= id %>">

        <label for="nome">Nome:</label><br>
        <input type="text" id="nome" name="nome" value="<%= nome %>" required><br><br>

        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" value="<%= email %>" required><br><br>

        <input type="submit" value="Atualizar">
    </form>

    <br>
    <a href="index.jsp">Voltar para Lista</a>
</body>
</html>
