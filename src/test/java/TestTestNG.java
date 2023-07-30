import configuratio.BaseClass;
import liseners.TestTestNGLisener;
import org.testng.annotations.*;
@Listeners(TestTestNGLisener.class)
public class TestTestNG extends BaseClass {
    @BeforeClass
    public void bef() {
        System.out.println("befor class");
    }

    @BeforeMethod
    public void bM() {
        System.out.println("befor Method");
    }

    @Test(description = "some test <data>",groups = {"smoke","regration","myTest"},priority = 10)
    public void test1() {
        System.out.println("test 1");
    }

    @Test(groups = {"myTest"},dependsOnMethods = {"test3"},alwaysRun = true)
    public void test2() {
        System.out.println("test 2");
    }

    @Test(priority = 1)
    public void test3() throws Exception {
        System.out.println("test 3");
        driver.get("https://www.youtube.com/");
        throw new Exception("hi");
    }

    @AfterMethod
    public void afterM() {
        System.out.println("afterMethod");
    }

    @AfterClass
    public void afteR() {
        System.out.println("after Class");
    }
}
