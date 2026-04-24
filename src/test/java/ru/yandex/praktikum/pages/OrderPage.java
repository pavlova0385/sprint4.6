package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//
import java.time.Duration;

public class OrderPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By orderButtonOnMainPage = By.xpath("//button[text()='Заказать']");

    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: введите улицу']");
    private final By metroInput = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон']");
    private final By nextButton = By.xpath("//button[contains(text(), 'Далее')]");

    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentPeriodDropdown = By.className("Dropdown-control");
    private final By blackCheckbox = By.id("black");
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[text()='Заказать']");
    private final By yesButton = By.xpath("//button[text()='Да']");

    private final By successModal = By.xpath(
            "//div[contains(@class, 'Order_ModalHeader') and contains(text(), 'Заказ оформлен')]"
    );

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickOrderButtonOnMainPage() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonOnMainPage)).click();
    }

    public void fillName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
    }

    public void fillSurname(String surname) {
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void fillAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    public void selectMetroStation(String stationName) {
        driver.findElement(metroInput).click();
        By metroStation = By.xpath(String.format("//div[contains(text(),'%s')]", stationName));
        wait.until(ExpectedConditions.elementToBeClickable(metroStation)).click();
    }

    public void fillPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public void fillDeliveryDate(String date) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateInput)).sendKeys(date);
    }

    public void selectRentPeriod(String period) {
        driver.findElement(rentPeriodDropdown).click();
        By rentOption = By.xpath(String.format("//div[contains(@class, 'Dropdown-option') and text()='%s']", period));
        wait.until(ExpectedConditions.elementToBeClickable(rentOption)).click();
    }

    public void selectBlackColor() {
        wait.until(ExpectedConditions.elementToBeClickable(blackCheckbox)).click();
    }

    public void fillComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void clickOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

    public void clickYesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
    }

    public boolean isOrderCreated() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successModal)).isDisplayed();
    }
}