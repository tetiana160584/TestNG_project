import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import page.CertificateFactory;
import page.HillelPage;

public class Test {
    private WebDriver driver;
    private HillelPage hillelPage;
    private CertificateFactory certificateFactory;

    @BeforeClass
    public void setUp() {
        // Предполагая, что исполняемый файл ChromeDriver доступен в системной переменной PATH
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

    @org.testng.annotations.Test
    public void testCoursesButtonExists() {
        // Проверяем, что кнопка "Курсы" существует на странице
        Assert.assertTrue(hillelPage.isCoursesButtonDisplayed(), "Кнопка 'Курсы' не отображается на странице.");
    }

    @AfterClass
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

    @Test( dataProvider = "getCertificateNumber" )
    public void testCertificateFactory(String certificateNumber) throws Exception {
        certificateFactory.enterNumber(certificateNumber);
        boolean result = certificateFactory.checkCertifCheckForm();
        if (certificateNumber.equals("123")) {
            Assert.assertFalse(result, "Failed for certificate number: " + certificateNumber +
                    ". Expected the certificate to be not found (false), but it was found (true).");
        } else {
            Assert.assertTrue(result, "Failed for certificate number: " + certificateNumber +
                    ". Expected the certificate to be found (true), but it was not found (false).");
        }

    }

}
