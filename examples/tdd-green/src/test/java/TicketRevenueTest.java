// EXECUTION_SCAFFOLD: imports only; original test follows.
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
public class TicketRevenueTest {
@Test
public void oneTicketSoldIsFiftyInRevenue() {
TicketRevenue venueRevenue = new TicketRevenue();
var expectedRevenue = new BigDecimal("50");
assertEquals(expectedRevenue, venueRevenue.estimateTotalRevenue(1));
}
}
