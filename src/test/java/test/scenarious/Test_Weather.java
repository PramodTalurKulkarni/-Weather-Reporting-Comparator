package test.scenarious;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import page.objects.Ndtv_Weather;
import page.objects.Open_weather;

@Test
public class Test_Weather {

	static WebDriver driver;
	FileReader reader = null;
	Properties props = new Properties();
	Ndtv_Weather nw;
	Open_weather ow;

	String NDTVbengaluruTemp;
	String OpenweatherTemp;
	String NDTVBengaluruhumidity;
	String OWbengaluruHumidity;

	public WebDriver getDriver() {

		return driver;
	}

	public Properties getProps() {
		return props;
	}

	@Test(priority = 0, enabled = true)

	public void readConfig() {

		try {
			reader = new FileReader(".//config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			props.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("OPENING URL: " + props.getProperty("URL"));
	}

	@Test(priority = 1, enabled = true)

	public void open_Browser() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50)).implicitlyWait(Duration.ofSeconds(10));

	}

	@Test(priority = 2, enabled = true)

	public void Ndtv_Weather() throws InterruptedException, AWTException {
		driver.get(props.getProperty("URL"));
		System.out.println("URL IS OPENED>>>>>>>>>>>>>>>>>>>>");

		nw = new Ndtv_Weather(driver);
		try {
			nw.click_popUp();
		} catch (NoSuchElementException e) {
			System.out.println(".nosuchelement exception");
		}

		nw.click_Menu();
		System.out.println("Clicked on Menu in NDTV");

		nw.click_Weather_option();
		System.out.println("Clicked on WEATHER in NDTV");

		nw.searchCity(props.getProperty("cityName"));
		System.out.println("Searching City in Weather Map");

		nw.clickMapData();
		nw.readMapData();

		System.out.println("Temperature in NDTV: " + nw.get_NDTVTemperature());
		System.out.println("Humidity in NDTV: " + nw.get_NDTVhumidity());

	}

	@Test(priority = 3, enabled = true)

	public void Open_weather() throws InterruptedException, MatcherException {

		driver.get(props.getProperty("URL1"));
		ow = new Open_weather(driver);
		System.out.println("Opened OpenWeather Website ");

		ow.setSearch_city(props.getProperty("cityName"));
		System.out.println("Searching City in Test Field");

		ow.click_SearchBtn();
		System.out.println("Clicked on Search Button");

		ow.click_optionCity();
		System.out.println("Clicked on City");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.textToBePresentInElement(ow.resultCityName, props.getProperty("cityName")));

		System.out.println("Temperature in open weather : " + ow.get_OpenweatherTemp());
		System.out.println("Humidity in open weather: " + ow.getOpenweatherHumidity());

	}

	@Test(priority = 5, enabled = true)

	public void comparedata() throws MatcherException {
//		System.out.println("Compare data"+NDTVbengaluruTemp);
		double temp1 = Double.parseDouble(Ndtv_Weather.Temperature);

		double temp2 = Double.parseDouble(Open_weather.temperature);

		if (temp1 > temp2) {
			if ((temp1 - temp2) <= 1) {
				System.out.println("Success: Temperature difference is acceptable");
			} else {
				System.out.println("Temperature difference is not acceptable");
				throw new MatcherException("Data Mismatch");
			}

		} else if (temp2 > temp1) {

			if ((temp2 - temp1) <= 1) {
				System.out.println("Success: Temperature difference is acceptable");
			} else {
				System.out.println("Temperature difference is not acceptable");
				throw new MatcherException("Data Mismatch");
			}
		}

		int humidity1 = Integer.parseInt(Ndtv_Weather.humidity);

		int humidity2 = Integer.parseInt(Open_weather.humidity);

		if (humidity1 == humidity2) {
			System.out.println("Success: Humidity difference is acceptable");
		} else if (humidity1 > humidity2) {
			if ((humidity1 - humidity2) <= 10) {
				System.out.println("Success: Humidity difference is acceptable");
			} else {
				System.out.println("Humidity difference is not acceptable");
				throw new MatcherException("Data Mismatch");
			}

		} else if (humidity2 > humidity1) {
			if ((humidity2 - humidity1) <= 10) {
				System.out.println("Success: Humidity difference is acceptable");
			} else {
				System.out.println("Humidity difference is not acceptable");
				throw new MatcherException("Data Mismatch");
			}
		}

	}
}
