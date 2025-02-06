package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class App {
    public static void main(String[] args) {
        // String url = 
        // String usuario = ; // Seu usuário do banco
        // String senha = ; // Sua senha do banco

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/biblioteca","postgres","AnaCarlaBG");
            if(conn != null){
                  System.out.println("Conexão estabelecida com sucesso!");
                  Statement stm = conn.createStatement();
            }else{
                System.out.println("Erro ao conectar com o banco de dados: ");
            }
            // Aqui você pode adicionar o código para interagir com o banco de dados
            // Exemplo: executar queries, inserir dados, etc.

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}