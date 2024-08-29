import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:my.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            if (conn != null) {
                var meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");

                String createAuthorsTable = "CREATE TABLE IF NOT EXISTS Authors ("
                        + "id INTEGER PRIMARY KEY, "
                        + "name TEXT NOT NULL)";
                stmt.execute(createAuthorsTable);

                String createGenresTable = "CREATE TABLE IF NOT EXISTS Genres ("
                        + "id INTEGER PRIMARY KEY, "
                        + "name TEXT NOT NULL)";
                stmt.execute(createGenresTable);

                String createBooksTable = "CREATE TABLE IF NOT EXISTS Books ("
                        + "id INTEGER PRIMARY KEY, "
                        + "title TEXT NOT NULL, "
                        + "author_id INTEGER, "
                        + "genre_id INTEGER, "
                        + "FOREIGN KEY(author_id) REFERENCES Authors(id), "
                        + "FOREIGN KEY(genre_id) REFERENCES Genres(id))";
                stmt.execute(createBooksTable);

                String insertAuthors = "INSERT INTO Authors (name) VALUES "
                        + "('J.K. Rowling'), "
                        + "('Agatha Christie'), "
                        + "('Cat Bohannon'), "
                        + "('Stuart Turton')";
                stmt.execute(insertAuthors);

                String insertGenres = "INSERT INTO Genres (name) VALUES "
                        + "('Fantasy for children'), "
                        + "('Non-fiction'), "
                        + "('Detective'), "
                        + "('Mystery')";
                stmt.execute(insertGenres);

                String insertBooks = "INSERT INTO Books (title, author_id, genre_id) VALUES "
                        + "('Harry Potter and the Deathly Hallows', 1, 1), "
                        + "('Death on the Nile', 2, 3), "
                        + "('Eve', 3, 2), "
                        + "('The devil and the dark water', 4, 4)";
                stmt.execute(insertBooks);

                System.out.println("Tables were created, data was inserted successfully!");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
