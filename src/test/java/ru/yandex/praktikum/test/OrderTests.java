package ru.yandex.praktikum.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderTests {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void startBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void orderFormPage() {
        driver.get("https://qa-scooter.praktikum-services.ru");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.className("Button_Button__ra12g"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='* Имя']"))).sendKeys("Анна");

        driver.findElement(By.xpath("//input[@placeholder='* Фамилия']"))
                .sendKeys("Павлова");

        driver.findElement(By.xpath("//input[@placeholder='* Адрес: введите улицу']"))
                .sendKeys("Москва, улица Ленина 1");

        driver.findElement(By.xpath("//input[@placeholder='* Станция метро']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(), 'Ростокино')]"))).click();

        driver.findElement(By.xpath("//input[@placeholder='* Телефон']"))
                .sendKeys("+79657237099");

        driver.findElement(By.xpath("//button[contains(text(), 'Далее')]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@placeholder='* Когда привезти самокат']")))
                .sendKeys("30.04.2026");

        driver.findElement(By.className("Dropdown-control")).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'Dropdown-option') and text()='сутки']"))).click();

        driver.findElement(By.id("black")).click();

        driver.findElement(By.xpath("//input[@placeholder='Комментарий для курьера']"))
                .sendKeys("жду с нетерпением");

        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Заказать']")));
        orderButton.click();

        WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Да']")));
        yesButton.click();

        WebElement successModal = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'Order_ModalHeader') and contains(text(), 'Заказ оформлен')]")));

        Assert.assertTrue(successModal.isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
