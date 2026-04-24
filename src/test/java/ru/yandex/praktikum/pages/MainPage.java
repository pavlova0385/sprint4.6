package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cookieButton = By.id("rcc-confirm-button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openAccordionItem(int index) {
        acceptCookiesIfPresent();

        By questionLocator = By.id(String.format("accordion__heading-%d", index));
        WebElement question = wait.until(ExpectedConditions.visibilityOfElementLocated(questionLocator));

        scrollToElement(question);
        wait.until(ExpectedConditions.elementToBeClickable(question)).click();
    }

    public String getAccordionText(int index) {
        By answerLocator = By.id(String.format("accordion__panel-%d", index));
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answer.getText();
    }

    private void acceptCookiesIfPresent() {
        if (!driver.findElements(cookieButton).isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
        }
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );
    }
}
