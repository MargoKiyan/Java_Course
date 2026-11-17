// EXECUTION_SCAFFOLD: imports only; original test follows.
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
public class TicketRevenueTest {
private TicketRevenue venueRevenue;
@BeforeEach
public void setUp() {
venueRevenue = new TicketRevenue();
}
@Test
public void failIfLessThanZeroTicketsAreSold() {
assertThrows(IllegalArgumentException.class, () ->
venueRevenue.estimateTotalRevenue(-1));
}
@Test
public void zeroSalesEqualsZeroRevenue() {
assertEquals(BigDecimal.ZERO, venueRevenue.estimateTotalRevenue(0));
}
@Test
public void oneTicketSoldIsFiftyInRevenue() {
var expectedRevenue = new BigDecimal("50");
assertEquals(expectedRevenue, venueRevenue.estimateTotalRevenue(1));
}
@Test
public void failIfMoreThanHundredTicketsAreSold() {
assertThrows(IllegalArgumentException.class, () ->
venueRevenue.estimateTotalRevenue(101));
}
}
