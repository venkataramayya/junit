import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.javainuse.main.AvrDatabase;

import java.sql.*;

public class AvrDatabaseTest {

    private Connection con;
    private Statement statement;
    private ResultSet resultSet;
    private AvrDatabase avrDatabase;

    @Before
    public void setUp() throws Exception {
        // Initialize the AvrDatabase instance
        avrDatabase = new AvrDatabase();
        
        // Set up a real database connection (ensure your database is up and running)
        String url = "jdbc:mysql://localhost:3306/my3"; // Database URL
        String username = "root";  // Your DB username
        String password = "Avr@2003"; // Your DB password

        // Establish connection
        con = DriverManager.getConnection(url, username, password);
        statement = con.createStatement();
        
        // Create the ResultSet for testing
        String query = "select * from avr"; // Example query
        resultSet = statement.executeQuery(query);
    }

    @Test
    public void testDatabaseConnectionAndDataRetrieval() throws Exception {
        // Execute the code being tested
        while (resultSet.next()) {
            // Assert the expected values from the result set
            assertEquals(1, resultSet.getInt("id"));
            assertEquals("John Doe", resultSet.getString("name"));
            assertEquals(123, resultSet.getInt("roll_no"));
            assertEquals("123 Street", resultSet.getString("adress"));
        }
    }

    @Test
    public void testDatabaseConnectionFailure() {
        try {
            // Force a failed connection by passing incorrect credentials
            String invalidUrl = "jdbc:mysql://localhost:3306/invaliddb"; // Invalid DB
            DriverManager.getConnection(invalidUrl, "root", "wrongpassword");
            fail("SQLException expected, but not thrown");
        } catch (SQLException e) {
            // Assert that the exception is of type SQLException
            assertTrue(e instanceof SQLException);
        }
    }

    @Test
    public void testEmptyResultSet() throws SQLException {
        // Set up an empty result set (you can modify the query or database data to create an empty result)
        String emptyQuery = "SELECT * FROM avr WHERE id = -1"; // Assuming no record with id -1
        resultSet = statement.executeQuery(emptyQuery);
        
        // Assert that there is no data in the result set
        assertFalse(resultSet.next()); // No rows should be returned
    }


    @After
    public void tearDown() throws Exception {
        // Clean up the resources after the tests
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
        if (con != null) con.close();
    }
}
