import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManageJ {

    private WebDriver driver;
    private WebElement manageUsers;

    public ManageJ(WebDriver driver) {
        this.driver = driver;
    }

    //поиск ссылки "Manage Users"
    public boolean find() {
        if (driver.findElements(By.xpath("//dt[text()=\"Manage Users\"]/following-sibling::dd[text()=\"Create/delete/modify users that can log in to this Jenkins\"]/ancestor::a")).size() == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    //переход по ссылке
    public Users goToUsers(boolean toContinue) {
        if (toContinue) {
            manageUsers = driver.findElement(By.xpath("//dt[text()=\"Manage Users\"]/following-sibling::dd[text()=\"Create/delete/modify users that can log in to this Jenkins\"]/ancestor::a"));
            manageUsers.click();
            return new Users(driver);
        }
        else {
            return null;
        }
    }
}
