package ru.yandex.praktikum.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    protected OrderPage orderPage;
    protected static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void startBrowser() {
        String browser = System.getProperty("browser", "chrome");

        if (browser.equals("chrome")) {
            startBrowserChrome();
        } else if (browser.equals("firefox")) {
            startBrowserFirefox();
        } else {
            throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }

        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    public void startBrowserChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public void startBrowserFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @After
    public void tearDown() {

        driver.quit();
    }
}
