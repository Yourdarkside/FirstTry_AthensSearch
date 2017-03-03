package com.epam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//** Google search by DBelikova */

public class SearchAthens {
	private static final String GOOGLE_BY = "https://google.by";
	private static final String PATH_TO_FIREFOX_DRIVER = "./src/main/resources/geckodriver.exe";
	private static final String ATHENS_SEARCH = "Athens";
	
	private static final By SEARCH_FIELD = By.xpath("//*[@id='lst-ib']");
	private static final By SEARCH_BUTTON = By.name("btnK");
	private static final By ATHENS_WIKI = By.xpath(".//a[contains(text(),'Wikipedia')]");
	private static final By ATHENS_TITLE = By.xpath(".//*[@id='firstHeading']");
	private static final By ATHENS_CULTURE = By.xpath(".//*[@id='toc']/ul/li[7]/a/span[2]");
	private static final By ATHENS_MUSEUMS = By.id("Museums");
	
	private WebDriver driver;
		
	@BeforeMethod
	private void SetUpDriver() {
        System.setProperty("webdriver.gecko.driver", PATH_TO_FIREFOX_DRIVER);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
	}
	
	@Test
	public void GoogleSearch(){
	// Open Google using driver
	driver.get(GOOGLE_BY);
	//Find Search field in Google and type to look for any information
	driver.findElement(SEARCH_FIELD).sendKeys(ATHENS_SEARCH);
	driver.findElement(SEARCH_BUTTON).click();
	WebElement ATHENS_SEARCH = (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[contains(text(),'Wikipedia')]")));
	//Select the first Link on Wikipedia
	driver.findElement(ATHENS_WIKI).click();
	WebElement ATHENS_WIKI = (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='firstHeading']")));
	Assert.assertTrue(driver.findElement(ATHENS_TITLE).isDisplayed());
	//Select "Culture and contemporary life" section on WIKIPEDIA
	driver.findElement(ATHENS_CULTURE).click();
	WebElement ATHENS_CULTURE = (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='Museums']")));
	}
	
	//** @AfterMethod
	//public void CloseDriver(){
	//	driver.quit();
	//* }

}
