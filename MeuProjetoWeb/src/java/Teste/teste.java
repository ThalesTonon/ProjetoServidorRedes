/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Teste;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author thale
 */
public class teste {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/meu_banco";
        String user = "root";
        String password = "";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão bem-sucedida!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
