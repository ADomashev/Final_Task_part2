package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends BasePage {

    private static final String SEARCH_TERM_MINSK = "search.term.minsk";
    private static final String SEARCH_TERM_RIGA = "search.term.riga";

    @FindBy(xpath = ".//input[@id='OriginLocation_Combobox']/following-sibling::a/i")
    private WebElement searchFieldFromDropDown;
    @FindBy(xpath = ".//input[@id='OriginLocation_Combobox']")
    private WebElement searchFieldFrom;
    @FindBy(xpath = ".//input[@id='DestinationLocation_Combobox']/following-sibling::a/i")
    private WebElement searchFieldToDropDown;
    @FindBy(xpath = ".//input[@id='DestinationLocation_Combobox']")
    private WebElement searchFieldTo;
    
    @FindBy(xpath = ".//label[text()='One-way']")
    private WebElement oneWayRadioButton;
    @FindBy(xpath = ".//label[text()='Return']")
    private WebElement returnRadioButton;
    @FindBy(xpath = ".//input[@id='DepartureDate_Datepicker']/following-sibling::a/i")
    private WebElement calendarDepartureDateButton;
    @FindBy(xpath = ".//input[@id='DepartureDate_Datepicker']")
    private WebElement calendarDepartureDate;
    @FindBy(xpath = ".//div[contains(@class,'ui-datepicker-group-first')]//td[contains(@class,'ui-datepicker-today')]")
    private WebElement calendarDepartureCurrentDate;
    @FindBy(xpath = ".//input[@id='ReturnDate_Datepicker']/following-sibling::a/i")
    private WebElement calendarReturneDateButton;
    @FindBy(xpath = ".//input[@id='ReturnDate_Datepicker']")
    private WebElement calendarReturneDate;
    @FindBy(xpath = ".//button[@type='submit' and contains(text(),'Search')]")
    private WebElement searchButton;
    @FindBy(xpath = ".//div[contains(@class,'ui-datepicker')]")
    private WebElement calendarBlock;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public CalendarOneWayPage searchOneWay() {
        oneWay();
        this.searchButton.click();
        return PageFactory.initElements(super.driver, CalendarOneWayPage.class);
    }

    public CalendarReturnPage searchWithReturn() {
        this.searchButton.click();
        return PageFactory.initElements(super.driver, CalendarReturnPage.class);
    }

    public MainPage fillFromField() {
    	sleep(1000);
        this.searchFieldFromDropDown.click();sleep(1000);
        this.searchFieldFrom.click();sleep(1000);
        this.searchFieldFrom.sendKeys(getConfigProperty(SEARCH_TERM_MINSK));sleep(1000);
        this.searchFieldFrom.sendKeys(Keys.ENTER);
        return this;
    }

    public MainPage fillToField() {sleep(1000);
        this.searchFieldToDropDown.click();sleep(1000);
        this.searchFieldTo.click();sleep(1000);
        this.searchFieldTo.sendKeys(getConfigProperty(SEARCH_TERM_RIGA));sleep(1000);
        this.searchFieldTo.sendKeys(Keys.ENTER);
        return this;
    }

    public MainPage fillDepartureDate() {
        this.calendarDepartureDateButton.click();
        clickOnDateElement(String.valueOf(getDepartureYear()), String.valueOf(getDepartureMonth() -1),
                String.valueOf(getDepartureDay()));
        this.calendarDepartureDateButton.click();
        return this;
    }

    public MainPage fillReturnDate() {
        this.calendarReturneDateButton.click();
        clickOnDateElement(String.valueOf(getUncutReturnYear()), String.valueOf(getReturnMonth() -1),
                String.valueOf(getReturnDay()));
        return this;
    }

    public MainPage oneWay(){
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.oneWayRadioButton.click();
        return this;
    }

    public MainPage withReturn(){
        this.returnRadioButton.click();
        return this;
    }

    private void clickOnDateElement(String yearValue, String monthValue, String  dayValue){
        boolean isClicked = true;
        do {
            List<WebElement> calendarDateElements = this.calendarBlock.findElements(By.xpath(".//td[@data-handler='selectDay']"));

            for (WebElement calendarDateElement : calendarDateElements) {
                if (calendarDateElement.getAttribute("data-year").equals(yearValue)
                        && calendarDateElement.getAttribute("data-month").equals(monthValue)
                        && calendarDateElement.findElement(By.xpath(".//a")).getText().equals(dayValue)) {
                    calendarDateElement.click();
                    isClicked = false;
                    break;
                }
            }
            if(!isClicked){
                return;
            }
            this.calendarBlock.findElement(By.xpath(".//a[@data-handler='next' and @title='Next']")).click();
        } while (isClicked);
    }
    private void sleep(long mil) {
    	try {
			Thread.sleep(mil);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
