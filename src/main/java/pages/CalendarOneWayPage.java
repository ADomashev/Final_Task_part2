package pages;

import domain.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.DateUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.Clock.systemUTC;

public class CalendarOneWayPage extends BasePage {

    private static final String CALENDAR_ELEMENTS_LOCATOR = "//div[@id='matrix' and @class='one-way']//div[@class='price']";
    private static final String PRICE_LOCATOR = ".//label";
    private static final String DATE_LOCATOR = ".//input[@name='date']";
    private static final String VALUE_STRING = "value";

    private List<Ticket> tickets;

    @FindBy(xpath = ".//div[@class='nav-right']/a")
    private WebElement nextButton;

    public CalendarOneWayPage(WebDriver driver) {
        super(driver);
        this.tickets = new ArrayList<>();
    }

    public CalendarOneWayPage goThroughTickets() {
        LocalDate start = LocalDate.now(systemUTC());
        LocalDate end = LocalDate.of(getUncutReturnYear(), getReturnMonth(), getReturnDay());
        LocalDate date = start;
        while (date.isBefore(end)){
            List<WebElement> elements = super.driver.findElements(By.xpath(CALENDAR_ELEMENTS_LOCATOR));
            date = getTicketsDateAndPrice(elements, date, end);
            clickNextButton();
        }
        return this;
    }

    private LocalDate getTicketsDateAndPrice(List<WebElement> calendarElements, LocalDate currentDate, LocalDate endDate){
        LocalDate lastDate = currentDate;
        for(WebElement calendarElement: calendarElements){
            LocalDate date =
                    DateUtils.toLocalDate(calendarElement.findElement(By.xpath(DATE_LOCATOR)).getAttribute(VALUE_STRING));
            String price = calendarElement.findElement(By.xpath(PRICE_LOCATOR)).getText();
            double priceValue = Double.parseDouble(price.substring(0, price.length() -4));
            Ticket ticket = new Ticket();
            ticket.setDepartureDate(date);
            ticket.setPrice(priceValue);
            tickets.add(ticket);
            if(currentDate.isBefore(date)){
                lastDate = date;
            }
            if (!lastDate.isBefore(endDate)){
                break;
            }
        }
        return lastDate;
    }

    private void clickNextButton(){
        this.nextButton.click();
    }

    public List<Ticket> getTickets(){
        return this.tickets;
    }
}
