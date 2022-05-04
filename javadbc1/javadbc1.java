package javadbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class javadbc1 {
    public static void main(String[] args) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/EX2?characterEncoding=latin1&useConfigs=maxPerformance", "root",
                    "Mahabharata@123");
            // here sonoo is database name, root is username and password
            System.out.println("Connection established Succesfully");
            Statement st = con.createStatement();
            st.executeUpdate("insert into course values('919','Science','10','1004')");
            System.out.println("record Entered succesfully");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
