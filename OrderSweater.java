import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;

public class OrderSweater {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {

            driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");

//            driver.findElement(By.cssSelector("a.login")).click();
            driver.findElement(By.name("email")).sendKeys("kmlyorymqdscfvdosa@poplk.com");
            driver.findElement(By.name("password")).sendKeys("AaBbCc123");
            driver.findElement(By.id("submit-login")).click();

            driver.get("https://mystore-testlab.coderslab.pl/index.php");
//driver.findElement(By.className("logo img-fluid")).click();
//            driver.findElement(By.xpath("//div/div[@class='logo']")).click();
//            driver.navigate().back();
            driver.findElement(By.linkText("Hummingbird Printed Sweater")).click();


            Select sizeDropdown = new Select(driver.findElement(By.id("group_1")));
            sizeDropdown.selectByVisibleText("M");
//            driver.findElement(By.cssSelector("btn btn-primary add-to-cart")).click();
//           driver.findElement(By.xpath("//div/div[[@class='add']]/button[contains(text(),Add to cart')]")).click();



            WebElement quantityInput = driver.findElement(By.id("quantity_wanted"));
//           to mi nie działa, nie czyści pola tylko do jedynki dodaje 5
            quantityInput.clear();
            quantityInput.sendKeys("5");

            driver.findElement(By.xpath("//div/div[@class='add']/button")).click();
//            driver.findElement(By.cssSelector("button[data-button-action='add-to-cart']"))
//                    .click();


            driver.findElement(By.xpath("//div/div[@class='cart']/button")).click();
            WebElement btnCheckOut = driver.findElement(By.xpath("//div/div[@class='text']"));
            btnCheckOut.click();


            driver.findElement(By.name("confirm-addresses")).click();

//zaznacza się z automatu
//            driver.findElement(By.id("delivery_option_1")).click();
            driver.findElement(By.name("confirmDeliveryOption")).click();


            driver.findElement(By.id("payment-option-1")).click();


            driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();


            driver.findElement(By.cssSelector("#payment-confirmation button")).click();


            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File("order_confirmation.png");
            FileUtils.copyFile(srcFile, destFile);

            System.out.println("Order placed successfully. Screenshot saved at " + destFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            driver.quit();
        }
    }
}