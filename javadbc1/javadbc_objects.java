package javadbc1;
//20BCE2126
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class javadbc_objects {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        try {
            javaStudent s = new javaStudent();
            s.get_details();
            s.insert_student();
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
    }
}

class javaStudent {
    private String name;
    private String password;
    private String country;
    private int mark;

    public void get_details() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your Name");
        name = input.nextLine();
        System.out.println("Enter your password");
        password = input.nextLine();
        System.out.println("Enter your Country");
        country = input.nextLine();
        System.out.println("Enter the mark");
        mark = input.nextInt();
    }

    public void insert_student() throws InstantiationException, IllegalAccessException,
            ClassNotFoundException, SQLException {
        // establish a database connection
        dbmsconnection connect = new dbmsconnection();
        Connection con = connect.getConnection("jdbc:mysql://localhost:3306/EX2", "root",
                "Mahabharata@123");
        String sql = "insert into student values (?,?,?,?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, password);
        stmt.setString(3, country);
        stmt.setInt(4, mark);
        stmt.execute();
        System.out.println("Record inserted successfully");
        connect.closeConnection(stmt, con);
    }
}

class dbmsconnection {
    String url;
    String username;
    String password;

    public Connection getConnection(String url, String username, String password)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Connection con = null;
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        con = DriverManager.getConnection(url, username, password);
        System.out.println("Connection Established Successfully");
        return con;
    }

    public void closeConnection(Statement stmt, Connection con) throws SQLException {
        stmt.close();
        con.close();
    }
}