// EXECUTION_SCAFFOLD: imports and enclosing class only; original fragments follow.
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.GenericContainer;
import redis.clients.jedis.RedisClient;

@Testcontainers
public class RedisTest {
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
}
