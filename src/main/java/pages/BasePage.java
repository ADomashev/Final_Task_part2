package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import services.PropertyReader;
import utils.DateUtils;


public class BasePage {

    private static final String CONFIG_PROPERTIES_FILE_NAME = "config";
    private static final String URL_PROPERTY_NAME = "belavia.url";
    protected static final String CALENDAR_DATE_TO = "calendar.date.to";
    private static final String DOT = "\\.";

    protected PropertyReader config;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.config = new PropertyReader(CONFIG_PROPERTIES_FILE_NAME);
    }

    public MainPage goToBelavia(){
        this.driver.get(getConfigProperty(URL_PROPERTY_NAME));
        return PageFactory.initElements(this.driver, MainPage.class);
    }

    public String getConfigProperty(String propertyName){
        return this.config.getProperty(propertyName);
    }


    protected int getReturnYear(){
        String [] calendarDateTo = config.getProperty(CALENDAR_DATE_TO).split(DOT);
        String cuttedYear = calendarDateTo[2].substring(2, 4);
        return Integer.parseInt(cuttedYear);
    }

    protected int getUncutReturnYear(){
        String [] calendarDateTo = config.getProperty(CALENDAR_DATE_TO).split(DOT);
        return Integer.parseInt(calendarDateTo[2]);
    }

    protected int getDepartureYear(){
        String [] calendarDateTo = DateUtils.getCurrentDate().split(DOT);
        return Integer.parseInt(calendarDateTo[2]);
    }

    protected int getReturnMonth(){
        String [] calendarDateTo = config.getProperty(CALENDAR_DATE_TO).split(DOT);
        return Integer.parseInt(calendarDateTo[1]);
    }

    protected int getDepartureMonth(){
        String [] calendarDateTo = DateUtils.getCurrentDate().split(DOT);
        return Integer.parseInt(calendarDateTo[1]);
    }

    protected int getReturnDay(){
        String [] calendarDateTo = config.getProperty(CALENDAR_DATE_TO).split(DOT);
        return Integer.parseInt(calendarDateTo[0]);
    }

    protected int getDepartureDay(){
        String [] calendarDateTo = DateUtils.getCurrentDate().split(DOT);
        return Integer.parseInt(calendarDateTo[0]);
    }
}
