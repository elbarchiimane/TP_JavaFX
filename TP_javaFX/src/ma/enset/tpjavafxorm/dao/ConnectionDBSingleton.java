package ma.enset.tpjavafxorm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDBSingleton {
    private static Connection cnx;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/db_stock","root","");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  static  Connection getCnx(){
        return  cnx;
    }
}
