import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
public class EdgeIncognitoExtension 
{

        public static void main(String[] args) throws MalformedURLException, InterruptedException {


        String username = System.getenv("LT_USERNAME");
        String accessKey = System.getenv("LT_ACCESS_KEY");

        // LambdaTest Remote WebDriver URL
        String remoteUrl = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("-inprivate");
        edgeOptions.addExtensions(new File("D:\\Code\\extension.crx")); //extension directory in local

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "MicrosoftEdge");
        capabilities.setCapability("browserVersion", "119.0");
        capabilities.setCapability(EdgeOptions.CAPABILITY, edgeOptions);

        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", username);
        ltOptions.put("accessKey", accessKey);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("project", "Untitled");
        capabilities.setCapability("LT:Options", ltOptions);
        // Create a Remote WebDriver instance on LambdaTest
        WebDriver driver = new RemoteWebDriver(new URL(remoteUrl), capabilities);
        driver.get("edge://extensions/");
        Thread.sleep(10000);
        driver.quit();
    }
}