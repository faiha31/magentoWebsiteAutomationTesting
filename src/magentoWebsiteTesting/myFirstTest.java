package magentoWebsiteTesting;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.SuiteRunState;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

import static org.testng.Assert.assertEquals;

public class myFirstTest {

	WebDriver driver = new ChromeDriver();
	String myWebsite = "https://magento.softwaretestingboard.com/";
	String password = "faihaa0987@#!";
	Random rand = new Random();
	String emailAdressToLogin = "";
	String logout = "https://magento.softwaretestingboard.com/customer/account/logout/";

	@BeforeTest
	public void mySetup() {

		driver.manage().window().maximize();
		driver.get(myWebsite);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test(priority = 1, enabled = true)
	public void creatAccoutTest() {

		WebElement creatAccountPage = driver.findElement(By.linkText("Create an Account"));
		creatAccountPage.click();

		WebElement firstNameInput = driver.findElement(By.id("firstname"));
		WebElement lasttNameInput = driver.findElement(By.id("lastname"));
		WebElement emailAderssInput = driver.findElement(By.id("email_address"));
		WebElement PasswordInput = driver.findElement(By.id("password"));
		WebElement ConfirmPasswordInput = driver.findElement(By.id("password-confirmation"));
		WebElement creatAccoutButton = driver.findElement(By.xpath("//button[@title='Create an Account']"));

		String[] first_Names = { "Alice", "Bob", "Charlie", "Diana", "Ethan", "Fiona" };
		String[] last_Names = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia" };

		int randomIndexForTheFirstName = rand.nextInt(first_Names.length);
		int randomIndexForTheLastName = rand.nextInt(last_Names.length);

		System.out.println(randomIndexForTheFirstName);
		System.out.println(randomIndexForTheLastName);

		String firstName = first_Names[randomIndexForTheFirstName];
		String lastName = last_Names[randomIndexForTheLastName];

		System.out.println(firstName);
		System.out.println(lastName);

		int randomNumber = rand.nextInt(9658);
		String domainName = "@gmail.com";
		String emailAdress = firstName + lastName + randomNumber + domainName;

		firstNameInput.sendKeys(firstName);
		lasttNameInput.sendKeys(lastName);
		emailAderssInput.sendKeys(emailAdress);
		PasswordInput.sendKeys(password);
		ConfirmPasswordInput.sendKeys(password);
		creatAccoutButton.click();

		emailAdressToLogin = emailAdress;

		WebElement MessageAsWebElement = driver.findElement(By.className("messages"));
		String TheActualMessage = MessageAsWebElement.getText();
		String ExpectedMessage = "Thank you for registering with Main Website Store.";

		assertEquals(TheActualMessage,ExpectedMessage);

	}

	@Test(priority = 2, enabled = true)
	public void logoutTest() {

		driver.get(logout);
		WebElement LogoutMessage = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));
		String ActualMsg = LogoutMessage.getText();
		String ExpectedMsg = "You are signed out";

		assertEquals(ActualMsg, ExpectedMsg);

	}

//	@Test(priority = 3, enabled = true)
//	public void logInTest() {
//		WebElement signinPage = driver.findElement(By.linkText("Sign In"));
//		signinPage.click();
//
//		WebElement emailLoginInput = driver.findElement(By.id("email"));
//		WebElement passwordInput = driver.findElement(By.id("pass"));
//		WebElement signinButton = driver.findElement(By.id("send2"));
//
//		emailLoginInput.sendKeys(emailAdressToLogin);
//		passwordInput.sendKeys(password);
//		signinButton.click();
//
//		String WelcomeMessage = driver.findElement(By.className("logged-in")).getText();
//		boolean ActualValue = WelcomeMessage.contains("Welcome");
//		boolean ExpectedValue = true;
//
//		assertEquals(ActualValue, ExpectedValue);
//
//	}
	@Test(priority = 3, enabled = true)
	public void loginTest() {
		WebElement LoginPage = driver.findElement(By.linkText("Sign In"));
		LoginPage.click();

		WebElement EmailLoginInput = driver.findElement(By.id("email"));
		WebElement passwordInput = driver.findElement(By.id("pass"));
		WebElement LoginButton = driver.findElement(By.cssSelector(".action.login.primary"));

		EmailLoginInput.sendKeys(emailAdressToLogin);
		passwordInput.sendKeys(password);
		LoginButton.click();

		String WelcomeMessage = driver.findElement(By.className("logged-in")).getText();

		boolean ActualValue = WelcomeMessage.contains("Welcome");
		boolean ExpectedValue = true;

		assertEquals(ActualValue, ExpectedValue);
	}

	@Test(priority = 4, enabled = false)

	public void addMenItemMen() throws InterruptedException {
		WebElement MenSection = driver.findElement(By.id("ui-id-5"));
		MenSection.click();
		WebElement productITemsContainer = driver.findElement(By.className("product-items"));
		List<WebElement> AllItems = productITemsContainer.findElements(By.tagName("li"));
		int totalNumberOfItems = AllItems.size();
		int randomItem = rand.nextInt(totalNumberOfItems);
		AllItems.get(randomItem).click();

		WebElement theContainerOfSizes = driver.findElement(By.cssSelector(".swatch-attribute-options.clearfix"));
		List<WebElement> ListOfSizes = theContainerOfSizes.findElements(By.className("swatch-option"));
		int numberofSizes = ListOfSizes.size();
		int randomSize = rand.nextInt(numberofSizes);
		ListOfSizes.get(randomSize).click();

		WebElement ColorsContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
		List<WebElement> ListOfClors = ColorsContainer.findElements(By.tagName("div"));
		int numberOfColors = ListOfClors.size();
		int randomColor = rand.nextInt(numberOfColors);
		ListOfClors.get(randomColor).click();

		WebElement AddToCartButton = driver.findElement(By.id("product-addtocart-button"));
		AddToCartButton.click();

		WebElement MessageAdded = driver.findElement(By.className("message-success"));
		System.out.println(MessageAdded.getText().contains("You added"));

		assertEquals(MessageAdded.getText().contains("You added"), true);

	}

	@Test(priority = 4)
	public void addMenItemWomen() {
		WebElement WomenSelection = driver.findElement(By.cssSelector("#ui-id-4"));
		WomenSelection.click();
		WebElement productITemsContainer = driver.findElement(By.className("product-items"));
		List<WebElement> AllItems = productITemsContainer.findElements(By.tagName("li"));
		int numberOfItems = AllItems.size();
		int RandomItem = rand.nextInt(numberOfItems);
		AllItems.get(RandomItem).click();

		WebElement ThecontanerOfSize = driver
				.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
		List<WebElement> AllSizes = ThecontanerOfSize.findElements(By.tagName("div"));
		int NumberOfSizes = AllSizes.size();
		int RandomOfSize = rand.nextInt(NumberOfSizes);
		AllSizes.get(RandomOfSize).click();

		WebElement TheContanerOfColor = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
		List<WebElement> Allcolors = TheContanerOfColor.findElements(By.tagName("div"));
		int NumberOfColor = Allcolors.size();
		int RandomOfColor = rand.nextInt(NumberOfColor);
		Allcolors.get(RandomOfColor).click();

		WebElement AddToCartButton = driver.findElement(By.cssSelector("#product-addtocart-button"));
		AddToCartButton.click();

		WebElement MsgAdded = driver.findElement(By.cssSelector("div[role='alert']"));
		assertEquals(MsgAdded.getText().contains("You added"), true);

	}

	@Test(priority = 5)
	public void ReveiewsTest() {	
		WebElement ReviewSection = driver.findElement(By.id("tab-label-reviews-title"));
		ReviewSection.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,1200)");

		WebElement RatingStars = driver.findElement(By.cssSelector(".control.review-control-vote"));

		System.out.println(RatingStars.findElements(By.tagName("label")).size() + "*****************");

		String[] ids = { "Rating_1", "Rating_2", "Rating_3", "Rating_4", "Rating_5" };
		int randomIndex = rand.nextInt(ids.length);
		WebElement element = driver.findElement(By.id(ids[randomIndex]));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

		WebElement nickname = driver.findElement(By.id("nickname_field"));
		nickname.sendKeys("faihaa");

		WebElement summary = driver.findElement(By.id("summary_field"));
		summary.sendKeys("fa9654");

		WebElement review = driver.findElement(By.id("review_field"));
		review.sendKeys("hello this is a test");

		WebElement submitReviewButton = driver.findElement(By.cssSelector(".action.submit.primary"));
		submitReviewButton.click();

		String ActualTextForReview = driver.findElement(By.cssSelector(".message-success.success.message")).getText();
		String ExpectedTextForReview = "You submitted your review for moderation.";

		assertEquals(ActualTextForReview, ExpectedTextForReview);

	}

}
