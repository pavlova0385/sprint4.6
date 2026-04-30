package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameInput = By.xpath("//input[contains(@placeholder, 'Имя')]");
    private final By lastNameInput = By.xpath("//input[contains(@placeholder, 'Фамилия')]");
    private final By addressInput = By.xpath("//input[contains(@placeholder, 'Адрес')]");
    private final By metroInput = By.xpath("//input[contains(@placeholder, 'Станция метро')]");
    private final By phoneInput = By.xpath("//input[contains(@placeholder, 'Телефон')]");
    private final By nextButton = By.xpath("//button[contains(text(), 'Далее')]");
    private final By deliveryDateInput = By.xpath("//input[contains(@placeholder, 'Когда привезти')]");
    private final By rentPeriodDropdown = By.className("Dropdown-control");
    private final By rentPeriodOption = By.xpath("//div[contains(@class, 'Dropdown-option') and text()='сутки']");
    private final By blackColorCheckbox = By.id("black");
    private final By commentInput = By.xpath("//input[contains(@placeholder, 'Комментарий')]");
    private final By createOrderButton = By.xpath("//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']");
    private final By confirmOrderButton = By.xpath("//button[text()='Да']");
    private final By successModalHeader = By.xpath("//*[contains(text(), 'Заказ оформлен') or contains(text(), 'Номер заказа')]");

    public OrderPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void fillCustomerData(String firstName, String lastName, String address, String metroStation, String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addressInput)).sendKeys(address);
        wait.until(ExpectedConditions.elementToBeClickable(metroInput)).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(), '" + metroStation + "')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput)).sendKeys(phone);
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public void fillRentData(String deliveryDate, String comment) {
        WebElement dateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryDateInput));
        dateInput.sendKeys(deliveryDate);
        dateInput.sendKeys(Keys.TAB);

        new Actions(driver)
                .moveByOffset(10, 10)
                .click()
                .perform();

        wait.until(ExpectedConditions.elementToBeClickable(rentPeriodDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(rentPeriodOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(blackColorCheckbox)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(commentInput)).sendKeys(comment);
    }

    public void submitOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(createOrderButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton)).click();
    }

    public WebElement waitForSuccessModal() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successModalHeader));
    }
}
