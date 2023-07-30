package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HillelPage {

    static WebDriver driverInHillelPage;

    @FindBy(xpath = "//*[@id=\"body\"]/div/div[1]/div[2]/div/div/nav/ul/li[2]/button")
    private WebElement courseButton;


    public HillelPage(WebDriver driver) {
        driverInHillelPage = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isCoursesButtonDisplayed() {
        return courseButton.isDisplayed();
    }

}
