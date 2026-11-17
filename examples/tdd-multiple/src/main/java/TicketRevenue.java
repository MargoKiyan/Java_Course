// EXECUTION_SCAFFOLD: import only; original class follows.
import java.math.BigDecimal;
public class TicketRevenue {
private final static int TICKET_PRICE = 50; // Уникнули магічного числа
public BigDecimal estimateTotalRevenue(int numberOfTicketsSold) {
BigDecimal totalRevenue = BigDecimal.ZERO;
totalRevenue = new BigDecimal(TICKET_PRICE * numberOfTicketsSold);
return totalRevenue;
}
}
