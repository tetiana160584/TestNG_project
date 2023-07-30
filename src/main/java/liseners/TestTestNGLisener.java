package liseners;

import configuratio.BaseClass;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenS;

public class TestTestNGLisener extends BaseClass implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        ScreenS.takeScreenShot(driver,"_"+result.getTestClass().getName()+"_"+result.getMethod().getMethodName());
    }
}
