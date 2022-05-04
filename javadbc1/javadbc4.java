package javadbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.InputMap;

public class javadbc4 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Connection con = connect();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Course ID  : ");
        int cid = input.nextInt();
        System.out.println("Enter Course Total  : ");
        String title = input.next();
        System.out.println("Enter Credits : ");
        int credits = input.nextInt();
        System.out.println("Enter Department ID : ");
        int did = input.nextInt();
        String sql = "insert into course values(?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, cid);
        stmt.setString(2, title);
        stmt.setInt(3, credits);
        stmt.setInt(4, did);
        System.out.println("Record entered Succesfully");
        stmt.close();
        con.close();
    } 
    public static Connection connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/EX2?characterEncoding=latin1&useConfigs=maxPerformance", "root",
                    "Mahabharata@123");
            System.out.println("Connection established Succesfully");
        return con;

    }
}
