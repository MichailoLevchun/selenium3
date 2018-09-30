package com.epam.lab.selen4.test;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.lab.selen4.ScreenShot;
import com.epam.lab.selen4.PageObject.GmailDraftPage;
import com.epam.lab.selen4.PageObject.GmailLoginPage;
import com.epam.lab.selen4.PageObject.LetterTypePage;

public class GmailDraftsTest {
	WebDriver driver = new ChromeDriver();

	@BeforeClass
	public void driverSetup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.gmail.com");
	}

	@Test
	public void testSendLetter() throws IOException {
		GmailLoginPage loginPage = new GmailLoginPage(driver);
		LetterTypePage letter = new LetterTypePage(driver);
		GmailDraftPage draftPage = new GmailDraftPage(driver);
		 
		loginPage.inputEmail();
		loginPage.inputPassword();
		
		letter.clickNewLetter();
		letter.inputLetterEmail();
		letter.inputLetterTheme();
		letter.inputLetterText();
		
		draftPage.clicDraft();
		draftPage.openDraftLetter();
		draftPage.sendLetter();
		ScreenShot.takeScreenShot(driver);
		Assert.assertTrue(driver.findElement(By.cssSelector("div.vh")).isDisplayed());
		driver.quit();
	}
}
