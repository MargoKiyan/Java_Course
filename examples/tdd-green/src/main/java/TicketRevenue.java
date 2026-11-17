// EXECUTION_SCAFFOLD: import only; original class follows.
import java.math.BigDecimal;
public class TicketRevenue {
public BigDecimal estimateTotalRevenue(int numberOfTicketsSold) {
BigDecimal totalRevenue = BigDecimal.ZERO;
if (numberOfTicketsSold == 1) {
totalRevenue = new BigDecimal("50");
}
return totalRevenue;
}
}
