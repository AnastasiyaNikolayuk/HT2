import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//a[text()=\"Настроить Jenkins\"]")
    private WebElement manageJenkins;

    //переход по ссылке
    public ManageJ goToManageJ() {
        manageJenkins.click();
        return new ManageJ(driver);
    }
}
