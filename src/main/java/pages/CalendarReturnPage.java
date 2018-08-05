package pages;

import domain.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.DateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.Clock.systemUTC;

public class CalendarReturnPage extends BasePage {

    private static final String CALENDAR_ELEMENTS_LOCATOR = "//div[@id='matrix' and @class='return']//div[@class='price']";
    private static final String PRICE_LOCATOR = ".//label";
    private static final String DATE_LOCATOR = ".//input[@name='date']";
    private static final String VALUE_STRING = "value";

    private List<Ticket> tickets;
    private LocalDate departureDate;
    private LocalDate returnDate;

    @FindBy(xpath = ".//div[@class='d-outbound']//div[@class='nav-right']/a")
    private WebElement rightButton;
    @FindBy(xpath = ".//div[@class='d-inbound']//div[@class='nav-right']/a")
    private WebElement upArrow;
    @FindBy(xpath = ".//div[@class='d-inbound']//div[@class='nav-left']/a")
    private WebElement downArrow;

    public CalendarReturnPage(WebDriver driver) {
        super(driver);
        this.tickets = new ArrayList<>();
    }

    public CalendarReturnPage goThroughTickets() {
        LocalDate start = LocalDate.now(systemUTC());
        LocalDate end = LocalDate.of(getUncutReturnYear(), getReturnMonth(), getReturnDay());

        LocalDate departureDate = start;
        LocalDate returnDate = end;
        while (departureDate.isBefore(end)) {
            while (start.isBefore(returnDate)) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<WebElement> elements = super.driver.findElements(By.xpath(CALENDAR_ELEMENTS_LOCATOR));
                returnDate = getTicketsDateAndPrice(elements, start, end);
                if (!isScrolledToStartDate(this.departureDate.format(DateTimeFormatter.ofPattern(DateUtils.DATE_PATTERN_TICKET)))) {
                    break;
                }
                clickUpButton();
            }
            if(!this.departureDate.isBefore(end)){
                break;
            }
            clickRightButton();
            scrollVerticalToEndDate(end.format(DateTimeFormatter.ofPattern(DateUtils.DATE_PATTERN_TICKET)));
        }
        return this;
    }

    private LocalDate getTicketsDateAndPrice(List<WebElement> calendarElements, LocalDate startDate, LocalDate endDate) {
        LocalDate earliestReturnDate = endDate;
        for (WebElement calendarElement : calendarElements) {
            String[] ticketDates = calendarElement.findElement(By.xpath(DATE_LOCATOR)).getAttribute(VALUE_STRING).split(":");
            this.departureDate = DateUtils.toLocalDate(ticketDates[0]);
            this.returnDate = DateUtils.toLocalDate(ticketDates[1]);
            String price = calendarElement.findElement(By.xpath(PRICE_LOCATOR)).getText();
            double priceValue = Double.parseDouble(price.replace(",", "").substring(0, price.length() - 4));
            if(departureDate.isBefore(endDate) && returnDate.isBefore(endDate)) {
                Ticket ticket = new Ticket();
                ticket.setDepartureDate(departureDate);
                ticket.setReturnDate(returnDate);
                ticket.setPrice(priceValue);
                tickets.add(ticket);
            }
            if (returnDate.isBefore(earliestReturnDate)) {
                earliestReturnDate = returnDate;
            }
            if (earliestReturnDate.isBefore(startDate)) {
                break;
            }
        }
        return earliestReturnDate;
    }

    private void clickRightButton() {
        this.rightButton.click();
    }

    private void clickUpButton() {
        this.upArrow.click();
    }

    private void clickDownButton() {
        this.downArrow.click();
    }

    public List<Ticket> getTickets() {
        return this.tickets;
    }

    private void scrollVerticalToEndDate(String endDate) {
        boolean isClicked = true;
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<WebElement> borderDates = super.driver.findElements(By.xpath("//div[@id='inbound_" + endDate + "']"));
            if (borderDates.isEmpty()) {
                clickDownButton();
            } else {
                isClicked = false;
            }
        } while (isClicked);
    }

    private boolean isScrolledToStartDate(String startDate) {
        List<WebElement> borderDates = super.driver.findElements(By.xpath("//div[@id='inbound_" + startDate + "']"));
        return borderDates.isEmpty();

    }

}
