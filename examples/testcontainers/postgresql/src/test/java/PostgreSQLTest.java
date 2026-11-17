// EXECUTION_SCAFFOLD: imports and enclosing class only; original fragments follow.
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import java.sql.DriverManager;
import java.sql.SQLException;

@Testcontainers
public class PostgreSQLTest {
@Container static PostgreSQLContainer postgres =
new PostgreSQLContainer("postgres:18")
.withDatabaseName("theater_db")
.withUsername("theater")
.withPassword("password")
.withInitScript("init.sql");
@Test void databaseIsReady() throws SQLException {
try (var conn = DriverManager.getConnection(
postgres.getJdbcUrl(), postgres.getUsername(),
postgres.getPassword())) {
assertTrue(conn.isValid(2));
}
}
}
