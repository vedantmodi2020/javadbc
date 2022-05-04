package javadbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class javadbc3 {
    public static void main(String[] args) {
        try {
            Connection con = connect();
            System.out.println("Enter the student details");
            Scanner input = new Scanner(System.in);
            Statement st = con.createStatement();
            int num;
            System.out.println("Enter the total no of values you want to inser : ");
            num = input.nextInt();
            for(int i=0;i<num;i++)
            {
                System.out.println("Enter course id : ");
                int cid = input.nextInt();
                System.out.println("Enter Course Title : ");
                String title = input.next();
                System.out.println("Enter Course Credits : ");
                int credits = input.nextInt();
                System.out.println("Enter Department id : ");
                int did = input.nextInt();
                st.executeUpdate("insert into course values('"+cid+"','"+title+"','"+credits+"','"+did+"');");
            }
            
            System.out.println("record Entered succesfully");
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
