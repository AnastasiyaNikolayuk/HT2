import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private WebDriver driver;
    private WebElement createUser;

    public Users(WebDriver driver) {
        this.driver = driver;
    }

    //достает строки из таблицы пользоателей
    public List<WebElement> getRows() {
        WebElement table = driver.findElement(By.xpath("//table"));
        List<WebElement> rows = table.findElements(By.xpath(".//tr"));
        rows.remove(0);
        return rows;
    }

    //создает список списков, хранящий данные ячеек таблицы пользователей
    public List<List<WebElement>> getRowsWithColumns() {
        List<WebElement> rows = getRows();
        List<List<WebElement>> rowsWithColumns = new ArrayList<List<WebElement>>();
        for (WebElement row : rows) {
            List<WebElement> rowWithColumns = row.findElements(By.xpath(".//td"));
            rowsWithColumns.add(rowWithColumns);
        }
        return rowsWithColumns;
    }

    //поиск записи о пользователе
    public boolean findUser(String UserName) {
        List<List<WebElement>> rows = getRowsWithColumns();
        for (List<WebElement> row : rows) {
            for (WebElement cell : row) {
                if (cell.getText().equals(UserName)) {
                    return false;
                }
            }
        }
        return true;
    }

    //поиск ссылки
    public boolean findCreateUser() {
        if (driver.findElements(By.xpath("//a[text()=\"Create User\"]")).size() == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    //переход по ссылке "Create user"
    public CreateUser goToCreateUser(boolean toContinue) {
        if (toContinue) {
            createUser = driver.findElement(By.xpath("//a[text()=\"Create User\"]"));
            createUser.click();
            return new CreateUser(driver);
        }
        else {
            return null;
        }
    }

    //поиск ссылки удаления
    public boolean tryToFindDelete() {
        if (driver.findElements(By.xpath("//a[@href=\"user/someuser/delete\"]")).size() == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    //переход по ссылке удаления пользователя
    public DeletingUser deleteUser() {
        WebElement delete = driver.findElement(By.xpath("//a[@href=\"user/someuser/delete\"]"));
        delete.click();
        return new DeletingUser(driver);
    }
}
