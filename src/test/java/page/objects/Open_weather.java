package page.objects;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Open_weather {

	WebDriver driver;
	public static String temperature;
	public static String humidity;

	public Open_weather(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='weather-widget']/div[1]/div/div/div[2]/div[1]/div/input")
	WebElement Search_city;

	@FindBy(css = "button[type='submit']")
	WebElement SearchBtn;

	@FindBy(css = "ul[class='search-dropdown-menu'] li")
	WebElement optionCity;

	@FindBy(xpath = "/html/body/main/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/span")
	WebElement OpenweatherTemp;

	@FindBy(xpath = "//*[@id='weather-widget']/div[2]/div[1]/div[1]/div[2]/ul/li[3]")
	WebElement  OpenweatherHumidity;

	@FindBy(css = "div[class='current-container mobile-padding'] div h2")
	public WebElement resultCityName;
	
	public WebDriver getDriver() {
		return driver;
	}
	public boolean openweatherTempIsDisplayed() {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollBy(0,250)", "");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		 wait.until(ExpectedConditions.visibilityOfAllElements(OpenweatherTemp));
		
//		JavascriptExecutor jee = (JavascriptExecutor) driver;
//		jee.executeScript("arguments[0].scrollIntoView(true);",OpenweatherTemp);

		return OpenweatherTemp.isDisplayed();
	}
	
	public boolean openweatherHumidityIsDisplayed() {
		return OpenweatherHumidity.isDisplayed();
	}
	
	public WebElement getResultCityName() {
		return resultCityName;
	}
	public void setSearch_city(String cityName) {
		
		Search_city.clear();
		Search_city.sendKeys(cityName);

	}

	public void click_SearchBtn() {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		 wait.until(ExpectedConditions.elementToBeClickable(SearchBtn));
		
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", SearchBtn);
	}

	public void click_optionCity() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		 wait.until(ExpectedConditions.visibilityOf(optionCity));

		optionCity.click();
		
	}
	


	public String get_OpenweatherTemp() {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);",OpenweatherTemp);


		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span)[5]")));

		
		System.out.println("Test OWT:"+OpenweatherTemp.getText());
		 temperature = OpenweatherTemp.getText().substring(0, 2);
		return OpenweatherTemp.getText().substring(0, 2);
	}

	public String getOpenweatherHumidity() {
		
		
		humidity = OpenweatherHumidity.getText().substring(10, 12);
		return OpenweatherHumidity.getText().substring(10, 12);
	}

	
}
