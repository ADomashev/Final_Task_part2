package tests;

import domain.Ticket;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.CalendarOneWayPage;
import pages.MainPage;
import utils.CompareTicketPrice;

import java.util.List;

public class OneWayTest extends BaseTestClass {

	@Test
	public void testMethod() {
		BasePage basePage = new BasePage(super.driver);
		MainPage mainPage = basePage.goToBelavia();
		mainPage.fillFromField();
		mainPage.fillToField();
		mainPage.oneWay();
		mainPage.fillDepartureDate();
		CalendarOneWayPage calendarOneWayPage = mainPage.searchOneWay();
		calendarOneWayPage.goThroughTickets();
		List<Ticket> tickets = calendarOneWayPage.getTickets();
		tickets.sort(new CompareTicketPrice());
		for (Ticket ticket : tickets) {
			System.out.println(ticket);
		}
	}
}
