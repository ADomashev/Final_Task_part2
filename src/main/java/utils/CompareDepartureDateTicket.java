package utils;

import java.util.Comparator;

import domain.Ticket;

public class CompareDepartureDateTicket implements Comparator<Ticket>{

	@Override
	public int compare(Ticket o1, Ticket o2) {
		
		return o1.getDate().compareTo(o2.getDate());
	}

}
