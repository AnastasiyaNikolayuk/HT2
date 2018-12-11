import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeletingUser {

    private WebDriver driver;

    public DeletingUser(WebDriver driver) {
        this.driver = driver;
    }

    //поиск текста
    public boolean findText() {
        if (driver.findElements(By.xpath("//*[text()=\"Are you sure about deleting the user from Jenkins?\"]")).size() == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    //нажатие кнопки подтверждения
    public Users ensureToDelete() {
        WebElement button = driver.findElement(By.xpath("//button[text()=\"Yes\"]"));
        button.click();
        return new Users(driver);
    }
}
