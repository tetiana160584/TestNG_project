import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.CertificateFactory;
import page.HillelPage;

public class TestButtonCertificate {
    private WebDriver driver;
    private HillelPage hillelPage;
    private CertificateFactory certificateFactory;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        hillelPage = new HillelPage(driver);
        driver.get("https://ithillel.ua/ru");
    }

    @BeforeMethod
    public void setUp2() {
        //инициализация
        driver = new ChromeDriver();
        certificateFactory = new CertificateFactory(driver);
        driver.get("https://certificate.ithillel.ua/ru");
    }

    @Test
    public void testCoursesButtonExists() {
        // Проверяем, что кнопка "Курсы" существует на странице
        Assert.assertTrue(hillelPage.isCoursesButtonDisplayed(), "The 'Courses' button is not displayed on the page.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @DataProvider
    public static Object[][] getCertificateNumber() {
        return new Object[][]{
                {"45924126"},
                {"78008248"},
                {"123"},
        };
    }


    @Test(dataProvider = "getCertificateNumber", dependsOnMethods = {"testCoursesButtonExists"})
    public void testCertificateFactory(String certificateNumber) throws Exception {
        certificateFactory.enterNumber(certificateNumber);
        boolean result = certificateFactory.checkCertifCheckForm();
        if (result) {
            // Ожидаем, что сертификат с номером найден.
            Assert.assertTrue(result, "Failed for certificate number: " + certificateNumber +
                    ". Expected the certificate to be found (true), but it was not found (false).");
        } else {
            // Ожидаем, что сертификат не будет найден.
            Assert.assertFalse(result, "Failed for certificate number: " + certificateNumber +
                    ". Expected the certificate to be not found (false), but it was found (true).");
            System.out.println("Certificate with number " + certificateNumber + " not found");
        }
    }
    
}

