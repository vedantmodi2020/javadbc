package javadbc1;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.InputMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class javaObject_car {
    public static void main(String[] args)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        dbms d = new dbms();
        Scanner input = new Scanner(System.in);
        d.connect("Mahabaharata@123");
        boolean t = true;
        do {
            System.out.println("Menu:-");
            System.out.println("1. Display database");
            System.out.println("2. Display Average Price");
            System.out.println("3. Update Database ");
            System.out.println("4. Delete data");
            System.out.println("5. Exit");

            System.out.println("\n Enter your choice : ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    d.display_sql();
                    break;
                case 2:
                    d.avg_price();
                    break;
                case 3:
                    d.update();
                    break;
                case 4:
                    d.Delete();
                    break;
                case 5:
                    t = false;
                    break;

                default:
                    System.out.println("Error Input");
                    break;
            }

        } while (t == true);
    }
}

class dbms {
    Scanner input = new Scanner(System.in);

    public Connection connect(String password)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/EX2?characterEncoding=latin1&useConfigs=maxPerformance", "root",
                "Mahabharata@123");

        System.out.println("Connection established Succesfully");
        return con;
    }

    public void close_connection(Statement stmt, Connection con) throws SQLException {
        stmt.close();
        con.close();
    }

    public void update() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/EX2?characterEncoding=latin1&useConfigs=maxPerformance", "root",
                    "Mahabharata@123");
            System.out.println("Connection established Succesfully");
            Statement stmt = (Statement) con.createStatement();
            System.out.println("Enter the carid and ModelName : ");
            int id = input.nextInt();
            String x = input.next();
            int result = stmt.executeUpdate("update cars set ModelName='" + x + "' where carid= '" + id + "' ");
            if (result > 0) {
                System.out.println("Record updated successfully");
            } else {
                System.out.println("No such user in the database");
            }
            close_connection(stmt, con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void display_sql() throws SQLException, ClassNotFoundException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/EX2?characterEncoding=latin1&useConfigs=maxPerformance", "root",
                    "Mahabharata@123");
            System.out.println("Connection established Succesfully");
            Statement stmt = (Statement) con.createStatement();
            String sql = "create table cars"+
            "(carid int(10) not null,"+
            "ModelName varchar(50) not null,"+
            "BrandName varchar(50) not null,"+
            "Price int(10) not null);";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(" insert into cars values(1120,'Seltos','Kia',1019000);");
            stmt.executeUpdate("insert into cars values(1112,'Mercedes-Benz GLA','Mercedes',4400000);");
            stmt.executeUpdate("insert into cars values(1234,'Sonet','Kia',715000);");
            stmt.executeUpdate("insert into cars values(1109,'Mercedes-Benz S-Class','Mercedes',15800000);");
            stmt.executeUpdate("insert into cars values(1103,'carens','kia',959000);");
            stmt.executeUpdate("insert into cars values(1193,'BMW X7','BMW',11500000);");
            stmt.executeUpdate("insert into cars values(1093,'BMW 3 Series','BMW',4540000);");
            stmt.executeUpdate("insert into cars values(2093,'carnival','Kia',2600000);");
            stmt.executeUpdate("insert into cars values(1113,'BMW Z4','BMW',6990000);");
            stmt.executeUpdate("insert into cars values(1113,'BMW Z4','BMW',6990000);");
            stmt.executeUpdate("insert into cars values(1100,'Lamborghini Aventador','Lamborghini',62500000);");
            System.out.println("Enter the BrandName for the car : ");
            String mn = input.nextLine();
            
            
            ResultSet rs = (ResultSet) stmt
                    .executeQuery("select  carid,BrandName,Price from cars where BrandName='"+mn+"' ");
            if (rs.next() == false) {
                System.out.println("No such record found in the datbase ");
            } else {
                do {
                    System.out.println("car Id : " + rs.getInt("carid") + " Model Name : " + rs.getString("ModelName") +" Price : "+ rs.getInt("Price") );
                } while (rs.next());
            }
            close_connection(stmt, con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void avg_price() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/EX2?characterEncoding=latin1&useConfigs=maxPerformance", "root",
                    "Mahabharata@123");
            System.out.println("Connection established Succesfully");
            Statement stmt = (Statement) con.createStatement();
            ResultSet rs = (ResultSet) stmt
                    .executeQuery("select avg(Price ),BrandName from cars group by BrandName");
            if (rs.next()) {
                do {
                    System.out.println("Model Name : " + rs.getString(2) + " Average Price : " + rs.getInt(1));
                } while (rs.next());
                
            } else {
                System.out.println("No such record fpund in the datbase ");
            }
            close_connection(stmt, con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Delete() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/EX2?characterEncoding=latin1&useConfigs=maxPerformance", "root",
                    "Mahabharata@123");
            System.out.println("Connection established Succesfully");
        Statement stmt = (Statement) con.createStatement();
        System.out.println("Enter the Brand Name Which you want to remove : ");
        String value = input.next();
        int result = stmt.executeUpdate("delete from cars where ModelName='" + value + "' ");
        if (result > 0) {
            System.out.println("Deleted the record succesfully");
        } else {
            System.out.println("No such BrandName is Present in the Table");
        }
        close_connection(stmt, con);
    }

}
