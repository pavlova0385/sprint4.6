package ru.yandex.praktikum.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By orderButtonOnMainPage = By.className("Button_Button__ra12g");
    private final By firstNameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: введите улицу']");
    private final By metroInput = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон']");
    private final By nextButton = By.xpath("//button[contains(text(), 'Далее')]");
    private final By deliveryDateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentPeriodDropdown = By.className("Dropdown-control");
    private final By rentPeriodOption = By.xpath("//div[contains(@class, 'Dropdown-option') and text()='сутки']");
    private final By blackColorCheckbox = By.id("black");
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By createOrderButton = By.xpath("//button[text()='Заказать']");
    private final By confirmOrderButton = By.xpath("//button[text()='Да']");
    private final By successModalHeader = By.xpath("//div[contains(@class, 'Order_ModalHeader') and contains(text(), 'Заказ оформлен')]");

    public OrderPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    public void clickOrderButtonOnMainPage() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonOnMainPage)).click();
    }

    public void fillCustomerData(String firstName, String lastName, String address, String metroStation, String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(metroInput).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + metroStation + "')]"))).click();
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void fillRentData(String deliveryDate, String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryDateInput)).sendKeys(deliveryDate);
        driver.findElement(rentPeriodDropdown).click();
        wait.until(ExpectedConditions.elementToBeClickable(rentPeriodOption)).click();
        driver.findElement(blackColorCheckbox).click();
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void submitOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(createOrderButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton)).click();
    }

    public WebElement waitForSuccessModal() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successModalHeader));
    }
}