<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Novo Usuário</title>
</head>
<body>
    <h1>Cadastrar Novo Usuário</h1>

    <form action="Usuarios" method="POST">
        <input type="hidden" name="action" value="create">

        <label for="nome">Nome:</label><br>
        <input type="text" id="nome" name="nome" required><br><br>

        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br><br>

        <input type="submit" value="Cadastrar">
    </form>

    <br>
    <a href="index.jsp">Voltar para Lista</a>
</body>
</html>
