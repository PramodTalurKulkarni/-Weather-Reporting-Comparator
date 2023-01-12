package page.objects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ndtv_Weather {
	WebDriver driver;
	WebDriverWait wait;

	public static String Temperature;
	public static String humidity;

	public Ndtv_Weather(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='__cricketsubscribe']/div[2]/div[2]/a[1]")
	WebElement popUp;

	@FindBy(xpath = "//*[@class='topnavmore']")
	WebElement Menu;

	@FindBy(xpath = "//a[contains(text(),'WEATHER')]")
	WebElement Weather_option;

	@FindBy(xpath = "//input[@id='searchBox']")
	WebElement Searchbox;

	@FindBy(css = ".tempRedText")
	WebElement MapData;

	public void click_popUp() {

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='__cricketsubscribe']/div[2]/div[2]/a[1]")));
		popUp.click();
	}

	public void click_Menu() {
		Menu.click();
		System.out.println("------------------------------------");
		System.out.println("Clicked Menu Option from the NDTV NavBar ");
	}

	public void click_Weather_option() {

		Weather_option.click();
		System.out.println("------------------------------------ ");
		System.out.println("Clicked on Weather Option from the NDTV NavBar ");
	}

	public void searchCity(String cityName) throws InterruptedException {

		List<WebElement> cities = driver.findElements(By.xpath("//input[@type='checkbox']"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(cities));

		for (WebElement ele : cities) {
			if (ele.isSelected()) {
				ele.click();
			}
		}
//		driver.findElement(By.xpath("//input[@id='searchBox']")).sendKeys(cityName);
		Searchbox.sendKeys(cityName);
		System.out.println("label[for='" + cityName + "']");
		WebElement cityClick = driver.findElement(By.cssSelector("label[for='" + cityName + "']"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(cityClick));
		cityClick.click();

	}

	public void clickMapData() {
		MapData.click();
	}

	public void readMapData() {

		String s = driver.findElement(By.cssSelector(".leaflet-popup-content-wrapper")).getText();
		String[] data = s.split("\n");

		for (String ss : data) {
			if (ss.contains("Humidity")) {
				humidity = ss.substring(10, 12);
			}
			if (ss.contains("Temp in Degrees")) {
				Temperature = ss.substring(17);

			}
		}
	}

	public boolean isMapDataDisplayed() {
		return MapData.isDisplayed();
	}

	public String get_NDTVTemperature() {

		return Temperature;

	}

	public String get_NDTVhumidity() {

		return humidity;

	}

}
