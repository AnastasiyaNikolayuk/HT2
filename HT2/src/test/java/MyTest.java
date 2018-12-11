import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MyTest {
    private WebDriver driver;
    private MainPage mainPage;
    private ManageJ manageJ;
    private Users users;
    private CreateUser createUser;
    private DeletingUser deletingUser;

    @BeforeClass
    public void Beginning() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\IdeaProjects\\HT2\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/");
        WebElement login = driver.findElement(By.xpath("//input[@name=\"j_username\"]"));
        WebElement password = driver.findElement(By.xpath("//input[@name=\"j_password\"]"));
        login.sendKeys("UserName"); //ВВЕДИТЕ ЛОГИН И ПАРОЛЬ
        password.sendKeys("Pssword");
        WebElement button = driver.findElement(By.xpath("//input[@name=\"Submit\"]"));
        button.click();
    }

    @Test
    public void TestForManageJ() {
        mainPage = PageFactory.initElements(driver, MainPage.class);
        manageJ = mainPage.goToManageJ();
        if (!manageJ.find()) {
            Assert.fail();
        }
        else {
            users = manageJ.goToUsers(true);
        }
    }

    @Test
    public void TestToCreateUser() {
        if (!users.findCreateUser()) {
            Assert.fail();
        }
        else {
            createUser = users.goToCreateUser(true);
        }
    }

    @Test
    public void TestToFillInForm() {
        if (!createUser.find()) {
            Assert.fail();
        }
        if (!createUser.isEmpty()) {
            Assert.fail();
        }
        else {
            users = createUser.setUser(true, "someuser", "Some Full Name", "some@addr.dom", "somepassword");
        }
    }

    @Test
    public void TestNewUser() {
        if (!users.findUser("someuser")) {
            Assert.fail();
        }
    }

    @Test
    public void TestToDelete() {
        if (!users.tryToFindDelete()) {
            Assert.fail();
        }
        else {
            deletingUser = users.deleteUser();
            Assert.assertTrue(deletingUser.findText());
            users = deletingUser.ensureToDelete();
            Assert.assertTrue(users.findUser("someuser"));
        }
    }

    @AfterClass
    public void End() {
        driver.quit();
    }
}
