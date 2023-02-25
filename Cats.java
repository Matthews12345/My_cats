import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cats
{
    public static void main(String[] args)
    {
        Connection connection = null;
        try
        {
            // create a database connection
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:My_cats.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists cats");
            statement.executeUpdate("create table cats (id int PRIMARY KEY, type varchar(100) NOT NULL )");
            statement.executeUpdate("insert into cats values(1, 'British-blue')");
            statement.executeUpdate("insert into cats values(2, 'Sphinks')");
            ResultSet rs = statement.executeQuery("select * from cats");
            while(rs.next())
            {
                // read the result set
                System.out.println("type = " + rs.getString("type"));
                System.out.println("id = " + rs.getInt("id"));
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
