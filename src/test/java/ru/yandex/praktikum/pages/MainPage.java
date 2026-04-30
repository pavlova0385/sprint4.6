package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private static final String URL = "https://qa-scooter.praktikum-services.ru";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By topOrderButton = By.xpath("(//button[text()='Заказать'])[1]");
    private final By bottomOrderButton = By.xpath("(//button[text()='Заказать'])[2]");
    private final By cookieButton = By.id("rcc-confirm-button");

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get(URL);
        acceptCookiesIfPresent();
    }

    public void clickTopOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(topOrderButton)).click();
    }

    public void clickBottomOrderButton() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(bottomOrderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    public void openAccordionItem(int questionIndex) {
        By question = By.id("accordion__heading-" + questionIndex);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(question));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public String getAccordionText(int questionIndex) {
        By answer = By.xpath("//div[@id='accordion__panel-" + questionIndex + "']/p");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(answer)).getText();
    }

    private void acceptCookiesIfPresent() {
        if (!driver.findElements(cookieButton).isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
        }
    }
}
