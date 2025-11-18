import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FlightBooking {

    WebDriver driver;
    @Test
    public void bookFlight() throws InterruptedException {
         try{


         driver = new ChromeDriver();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
         driver.manage().window().maximize();
         driver.get("https://www.rahulshettyacademy.com/dropdownsPractise/");
         System.out.println("Flight booking test executed.");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[id='autosuggest']")).sendKeys("ind");
        List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item']"));
        for(WebElement option : options)
        {
            if(option.getText().equalsIgnoreCase("India"))
            {
                option.click();
                break;
            }
        }

        // Select Round trip
        driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();

        // Select From and To city
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXTaction"));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[value='BLR']")));
        driver.findElement(By.cssSelector("a[value='BLR']")).click();
           /* we can perform click operation using javascript executor also
            WebElement we = driver.findElement(By.cssSelector("a[value='BLR']"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();",we);*/
        Thread.sleep(2000);
        driver.findElement(By.partialLinkText("MAA")).click();

       List<WebElement> options1 = driver.findElements(By.cssSelector("div[id='discount-checkbox'] div label"));
        for(WebElement option : options1)
        {
            if(option.getText().equalsIgnoreCase(" Student"))
            {
                option.click();
                System.out.println(option.getText() + "checkbox");

            }
        }

        // pick a future date from the calendar
        driver.findElement(By.xpath("//*[contains(text(),'Depart date')]/parent::div/following-sibling::input[@name='ctl00$mainContent$view_date1']")).click();
        driver.findElement(By.xpath("//td[@class=' ui-datepicker-week-end  ui-datepicker-current-day']/following-sibling::td")).click();
        Thread.sleep(3000);
        // pick return date from the calendar
        driver.findElement(By.xpath("//*[contains(text(),'Return date')]/parent::div/following-sibling::button[@class='ui-datepicker-trigger']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//td[@data-month='4']/a[text()='16']")).click();

        // Select Passenger
        driver.findElement(By.id("divpaxinfo")).click();
        WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("hrefIncAdt")));
        driver.findElement(By.xpath("//div[@id='divAdult']/div[@class='ad-row-right']//span[@id='hrefIncAdt']")).click(); // increase adult count by 1
        driver.findElement(By.xpath("//input[@value='Done']")).click();

        driver.findElement(By.xpath("//input[@name='ctl00$mainContent$btn_FindFlights']")).click();
        Thread.sleep(5000);

        driver.quit();
         } catch (Exception e) {
             throw new RuntimeException(e);
         }

    }

}
