import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CreateUser {

    private WebDriver driver;
    private List<WebElement> textField;
    private List<WebElement> passwordField;

    public CreateUser(WebDriver driver) {
        this.driver = driver;
    }

    //поиск полей для создания пользователя
    public boolean find() {
        if ((driver.findElements(By.xpath("//*[@type=\"text\"]")).size() != 3) || (driver.findElements(By.xpath("//*[@type=\"password\"]")).size() != 2)) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean isEmpty() {
        textField = driver.findElements(By.xpath("//*[@type=\"text\"]"));
        passwordField = driver.findElements(By.xpath("//*[@type=\"password\"]"));
        for (WebElement t : textField) {
            for (WebElement p : passwordField)
            if ((t.getAttribute("value").trim().length() == 0) && (p.getAttribute("value").trim().length() == 0)) {
                return true;
            }
        }
        return false;
    }

    //создание пользователя
    public Users setUser(boolean toContinue, String UserName, String FIO, String email, String password) {
        if (toContinue) {
            textField = driver.findElements(By.xpath("//*[@type=\"text\"]"));
            passwordField = driver.findElements(By.xpath("//*[@type=\"password\"]"));
            for (WebElement element : textField) {
                if (element.getAttribute("name").equals("username")) {
                    element.sendKeys(UserName);
                } else if (element.getAttribute("name").equals("fullname")) {
                    element.sendKeys(FIO);
                } else if (element.getAttribute("name").equals("email")) {
                    element.sendKeys(email);
                }
            }
            for (WebElement element : passwordField) {
                element.sendKeys(password);
            }
            WebElement button = driver.findElement(By.xpath("//button[text()=\"Create User\"]"));
            button.submit();
            return new Users(driver);
        }
        else {
            return null;
        }
    }
}
