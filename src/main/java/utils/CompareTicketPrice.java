package utils;

import java.util.Comparator;
import domain.Ticket;
public class CompareTicketPrice implements Comparator<Ticket> {

	@Override
	public int compare(Ticket o1, Ticket o2) {	
		return Double.compare(o1.getPrice(),o2.getPrice());
	}
}
