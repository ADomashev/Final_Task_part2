package tests;

import java.util.List;

import org.testng.annotations.Test;

import domain.Ticket;
import pages.BasePage;
import pages.CalendarReturnPage;
import pages.MainPage;
import utils.CompareDepartureDateTicket;


public class TwoWaysTest extends BaseTestClass {

	@Test
	public void testMethod() {
		BasePage basePage = new BasePage(super.driver);
		MainPage mainPage = basePage.goToBelavia();

		mainPage.goToBelavia();
		mainPage.fillFromField();
		mainPage.fillToField();
		mainPage.withReturn();
		mainPage.fillDepartureDate();
		mainPage.fillReturnDate();
		CalendarReturnPage calendarReturnPage = mainPage.searchWithReturn();
		calendarReturnPage.goThroughTickets();
		List<Ticket> tickets = calendarReturnPage.getTickets();
		tickets.sort(new CompareDepartureDateTicket());
		for (Ticket ticket : tickets) {
			System.out.println(ticket);
		}
	}
}
