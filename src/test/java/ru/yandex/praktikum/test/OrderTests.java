package ru.yandex.praktikum.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class OrderTests extends BaseTest {

    @Test
    public void orderFormPage() {
        OrderPage orderPage = new OrderPage(driver, wait);

        orderPage.open();
        orderPage.clickOrderButtonOnMainPage();
        orderPage.fillCustomerData(
                "Анна",
                "Павлова",
                "Москва, улица Ленина 1",
                "Ростокино",
                "+79657237099"
        );
        orderPage.clickNextButton();
        orderPage.fillRentData("30.04.2026", "жду с нетерпением");
        orderPage.submitOrder();

        WebElement successModal = orderPage.waitForSuccessModal();
        Assert.assertTrue(successModal.isDisplayed());
    }
}