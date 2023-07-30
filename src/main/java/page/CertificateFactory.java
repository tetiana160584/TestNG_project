package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CertificateFactory {
    //45924126
    //78008248
    @FindBy (name = "certificate")
    WebElement findField;

    @FindBy(id="certificateCheckForm")
    WebElement checkFormMassage;


    WebDriver driver;

    public CertificateFactory(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void enterNumber(String number){
        findField.sendKeys(number);
        findField.sendKeys(Keys.ENTER);
    }

    public boolean checkLinkCertificate(){
     return driver.getCurrentUrl().contains("view");
    }


    public boolean checkCertifCheckForm() throws Exception {
        System.out.println(checkFormMassage.getAttribute("class"));

        int i = 0;
        boolean result=false;
        while (true) {
            if (checkLinkCertificate()) {
                result = true;
                break;
            }
            if (checkFormMassage.getAttribute("class").contains("invalid")) {
                result = false;
                break;
            }

            try {
                Thread.sleep(3000);
                i++;
                if (i > 10) {
                    throw new Exception("Out of time wait certificate checker");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}


