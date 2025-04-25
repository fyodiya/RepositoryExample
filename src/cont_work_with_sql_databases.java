import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class cont_work_with_sql_databases {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:my.db";

        var sc = new Scanner(System.in);
        System.out.println("Please give me your name: ");
        var personsName = sc.nextLine();

        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                var statement = conn.createStatement();
//                statement.execute("CREATE TABLE IF NOT EXISTS people (id INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(20))");
                statement.execute("INSERT INTO people (name) VALUES ('" + personsName + "')");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

