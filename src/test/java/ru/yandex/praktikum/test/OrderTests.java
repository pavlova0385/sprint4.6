package ru.yandex.praktikum.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import ru.yandex.praktikum.base.BaseTest;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.OrderPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderTests extends BaseTest {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String deliveryDate;
    private final String comment;
    private final String orderButtonType;

    public OrderTests(String firstName,
                      String lastName,
                      String address,
                      String metroStation,
                      String phone,
                      String deliveryDate,
                      String comment,
                      String orderButtonType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.orderButtonType = orderButtonType;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getOrderData() {
        return Arrays.asList(new Object[][]{
                {"Анна", "Павлова", "Москва, улица Ленина 1", "Ростокино", "+79657237099", "30.05.2026", "жду с нетерпением", "top"},
                {"Иван", "Смирнов", "Москва, улица Тверская 5", "Сокольники", "+79991234567", "31.05.2026", "позвоните заранее", "bottom"}
        });
    }

    @Test
    public void orderFormPageShouldCreateOrder() {
        MainPage mainPage = new MainPage(driver, wait);
        OrderPage orderPage = new OrderPage(driver, wait);

        mainPage.open();

        if ("top".equals(orderButtonType)) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        orderPage.fillCustomerData(firstName, lastName, address, metroStation, phone);
        orderPage.clickNextButton();
        orderPage.fillRentData(deliveryDate, comment);
        orderPage.submitOrder();

        WebElement successModal = orderPage.waitForSuccessModal();
        Assert.assertTrue(successModal.isDisplayed());
    }
}
