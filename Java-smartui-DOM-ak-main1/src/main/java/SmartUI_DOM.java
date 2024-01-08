import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;

public class SmartUI_DOM 
{
    public static void main(String[] args) throws MalformedURLException, InterruptedException 
    {
        RemoteWebDriver driver = null;

        Hashtable<String, Integer> errorColor= new Hashtable<>();
        errorColor.put("red",500);
        errorColor.put("green",500);
        errorColor.put("blue",0);

        HashMap<String,Object> output= new HashMap<String, Object>();
        output.put("errorColor",errorColor);//Output Difference error color
        output.put("errorType","movement");//Flat Differences/Movement
        output.put("transparency",0.5);// Set transparency of Output
        output.put("largeImageThreshold",1200);// the granularity to which the comparison happens(the scale or level of detail in a set of data.)Range-100-1200
        output.put("useCrossOrigin",false);//source -localmachine
        output.put("outputDiff",true);//don't want to comparison set as false

        HashMap<String, Object> sm=new HashMap<String, Object>();
        sm.put("output",output);
        sm.put("scaleToSameSize",true);//scale to same size
        sm.put("ignore","antialiasing");//remove picture grouping


        String username = System.getenv("LT_USERNAME");
        String access_key = System.getenv("LT_ACCESS_KEY");

        String jsCommand = "smartui.takeScreenshot,{" +
                "\"screenshotName\":\"Testing the hook\", " +
                "\"ignoreDOM\":{\"id\":[\"clock0_bg\"]}" +
                "}";


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "SmartUI demo");
        capabilities.setCapability("name", "test 1");
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version","latest");
        //capabilities.setCapability("tunnel",true);

//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("deviceName", "Galaxy S20 Plus");
//        capabilities.setCapability("platformVersion","10");

//        capabilities.setCapability("platformName", "iOS");
//        capabilities.setCapability("deviceName", "iPhone 5");
//        capabilities.setCapability("platformVersion","10.3");


        capabilities.setCapability("visual",true);
        capabilities.setCapability("smartUI.project","DOM DEMO");

        // capabilities.setCapability("smartUI.build","build");
       // capabilities.setCapability("smartUI.baseline", true);
       capabilities.setCapability("smartUI.options",sm);

        driver = new RemoteWebDriver(new URL("http://" + username + ":" + access_key + "@hub.lambdatest.com/wd/hub"), capabilities);

        driver.get("https://time.is/");

        //Thread.sleep(4000);
        //driver.findElementByXPath("//*[@id='qc-cmp2-ui']/div[2]/div/button[3]").click(); // Uncomment if getting Cookie pop-up

         Thread.sleep(3000);

     //   driver.executeScript("smartui.takeScreenshot=Testing the hook"); // NORMAL SCREENSHOT


        driver.executeScript(jsCommand); // DOM SCREENSHOT

        driver.quit();

    }
}