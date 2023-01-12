package StepDefinations;

import java.awt.AWTException;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.WeatherReporting.assignment.PIP_PramodTK_Assignment.CompareData;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.objects.Ndtv_Weather;
import page.objects.Open_weather;
import test.scenarious.MatcherException;
import test.scenarious.Test_Weather;

public class WeatherStepDefinations {

	static WebDriver driver;
	Test_Weather testWeather = new Test_Weather();
	Ndtv_Weather nw;
	Open_weather ow;
	static Double NDTVTemparature;
	static Double NDTVHumidy;
	static Double OpenWeatherTemperature;
	static Double OpenWeatherHumidity;

	@Given("I Am In NDTV Home Page")
	public void i_am_in_ndtv_home_page() {
		testWeather.readConfig();
		testWeather.open_Browser();
		driver = testWeather.getDriver();
		driver.get(testWeather.getProps().getProperty("URL"));
		System.out.println("URL IS OPENED>>>>>>>>>>>>>>>>>>>>");

		nw = new Ndtv_Weather(driver);
		try {
			nw.click_popUp();
		} catch (NoSuchElementException e) {
			System.out.println("Nosuchelement exception");
		}
	}

	@When("I Click On Menu")
	public void i_click_on_menu() {

		nw.click_Menu();
		System.out.println("Clicked on Menu in NDTV");
	}

	@When("Click On Weather Option In Nav Bar")
	public void click_on_weather_option_in_nav_bar() {
		nw.click_Weather_option();
		System.out.println("Clicked on WEATHER in NDTV");
	}

	@Then("I Should Be In Weather Page")
	public void i_should_be_in_weather_page() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.titleContains(testWeather.getProps().getProperty("NDTVWeatherTitile")));

		Assert.assertEquals(testWeather.getProps().getProperty("NDTVWeatherTitile"), driver.getTitle());
	}

	@When("I Enter City Name In Search Box and Also Click On the Suggested CheckBox")
	public void i_enter_city_name_in_search_box_And_also_click_on_the_suggested_checkbox()
			throws AWTException, InterruptedException {
		nw.searchCity(testWeather.getProps().getProperty("cityName"));
		System.out.println("Searching City in Weather Map");
	}

	@Then("City Name With Temperature and Humidity Must Appear")
	public void city_name_with_temperature_and_humidity_must_appear() {
		nw.clickMapData();
		Assert.assertTrue(nw.isMapDataDisplayed());
		nw.readMapData();
		NDTVHumidy = Double.parseDouble(nw.get_NDTVhumidity());
		NDTVTemparature = Double.parseDouble(nw.get_NDTVTemperature());
		System.out.println("Humididty is " + nw.get_NDTVhumidity() + "and " + "Temp is " + nw.get_NDTVTemperature());
		System.out.println("FINAL NDTV DATA :: " + NDTVHumidy + "\n" + NDTVTemparature);

	}

	@Given("I Am In OpenWeather Home Page")
	public void i_am_in_open_weather_home_page() throws InterruptedException, MatcherException {
		testWeather.readConfig();
		ow = new Open_weather(driver);
		driver.get(testWeather.getProps().getProperty("URL1"));
		System.out.println("Opened OpenWeather Website ");

	}

	@When("I Search the City Name In SerchBox")
	public void i_search_the_city_name_in_serch_box() {
		ow.setSearch_city(testWeather.getProps().getProperty("cityName"));
		System.out.println("Searching City in Test Field");
	}

	@When("Click On The Search Button")
	public void click_on_the_search_button() {
		ow.click_SearchBtn();
		System.out.println("Clicked on Search Button");
	}

	@When("Click On the Suggested Option")
	public void click_on_the_suggested_option() {
		ow.click_optionCity();
		System.out.println("Clicked on City");
	}

	@Then("City Name With Temperature and Humidity Must Must Be Seen")
	public void city_name_with_temperature_and_humidity_must_must_be_seen() {
		Assert.assertTrue(ow.openweatherTempIsDisplayed());
		Assert.assertTrue(ow.openweatherHumidityIsDisplayed());
//		System.out.println("Humdity in OW is  "+ow.getOpenweatherHumidity());
//		System.out.println("Humdity in OW is  "+ow.get_OpenweatherTemp());
		OpenWeatherHumidity = Double.parseDouble(ow.getOpenweatherHumidity());

		OpenWeatherTemperature = Double.parseDouble(ow.get_OpenweatherTemp());
		System.out.println("FINAL OW DATA:: " + OpenWeatherTemperature + "  " + OpenWeatherHumidity);

	}

	@Given("I Visited NDTV and OpenWeather Sites")
	public void i_visited_ndtv_And_open_weather_sites() {
		System.out.println("Visited NDTV and OpenWeather Sites \n Comparing the data now.... \n");
	}

	@Then("Compare Data")
	public void compare_data() throws MatcherException {
		System.out.println(NDTVTemparature + NDTVHumidy + OpenWeatherTemperature + OpenWeatherHumidity);

		CompareData.comparedata(NDTVTemparature, NDTVHumidy, OpenWeatherTemperature, OpenWeatherHumidity);

	}

	@And("Close Driver")
	public void close_Driver() {
		driver.quit();
	}

}
