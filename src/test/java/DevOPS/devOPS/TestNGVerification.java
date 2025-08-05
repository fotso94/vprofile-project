package DevOPS.devOPS;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGVerification {
    
    @Test
    public void verifyTestNGIsWorking() {
        System.out.println("TestNG is working correctly!");
        System.out.println("URL parameter: " + System.getProperty("url"));
        System.out.println("User parameter: " + System.getProperty("usr"));
        System.out.println("Screenshot path: " + System.getProperty("sShotPath"));
        Assert.assertTrue(true, "TestNG verification test");
    }
}
