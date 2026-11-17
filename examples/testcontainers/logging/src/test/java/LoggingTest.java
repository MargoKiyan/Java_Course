// EXECUTION_SCAFFOLD: imports and enclosing class only; original fragments follow.
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.GenericContainer;
import redis.clients.jedis.RedisClient;
import org.testcontainers.containers.output.ToStringConsumer;
import org.testcontainers.containers.output.OutputFrame.OutputType;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

@Testcontainers
public class LoggingTest {
@Container static GenericContainer<?> redis =
new GenericContainer<>("redis:8.10.3-alpine")
.withExposedPorts(6379);
@Test void cached() {
try (var client = RedisClient.create(redis.getHost(),
redis.getMappedPort(6379))) {
client.set("price", "20");
assertEquals("20", client.get("price"));
}
}
public static ToStringConsumer consumer = new ToStringConsumer();
@BeforeAll
public static void setUp() {
redis.followOutput(consumer, OutputType.STDOUT, OutputType.STDERR);
}
@AfterAll
public static void tearDown() throws IOException {
Path log = Path.of("build", "tc.log");
Files.createDirectories(log.getParent());
Files.writeString(log, consumer.toUtf8String(), StandardCharsets.UTF_8);
}
}
