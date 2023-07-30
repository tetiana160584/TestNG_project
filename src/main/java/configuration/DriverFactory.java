package configuratio;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {
    public static WebDriver greateDriver(WEBDRIVERS webdrivers){
        WebDriver driver = null;
        switch (webdrivers){
            case CHROMECLEAN -> driver=createCleanChrome();
            case EDGEDRIVER -> driver=createEdge();
            case BNIGARSIA -> driver=createBONY();
        }
        return driver;
    }

    private static WebDriver createBONY() {
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        return  WebDriverManager.chromedriver().capabilities(options).create();
    }

    private static WebDriver createEdge() {
        return new EdgeDriver();
    }

    private static WebDriver createCleanChrome() {
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }
}
