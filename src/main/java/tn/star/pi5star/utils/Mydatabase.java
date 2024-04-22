package tn.star.pi5star.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mydatabase {
    private static Mydatabase instance ;

    private  final String username="root";
    private  final String password="";
    private  final String url="jdbc:mysql://127.0.0.1:3306/pidb";

    private Connection cnx;
    private Mydatabase (){

        try {
            cnx = DriverManager.getConnection(url,username,password);
            System.out.println("  " +
                    "Connected ... ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(" ___ Connection Failed ___");
        }
    }


    public static Mydatabase getInstance() {
        if(instance == null)
            instance = new Mydatabase();

        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }


}
