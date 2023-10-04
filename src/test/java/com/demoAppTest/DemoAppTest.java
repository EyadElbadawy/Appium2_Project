package com.demoAppTest;

import demoApp.screens.HomeScreen;
import demoApp.utils.JsonReader;
import io.qameta.allure.Description;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class DemoAppTest extends BaseTest {
    String loginTestData = "src/test/resources/testData/loginTestData.json";

    String checkoutTestData = "src/test/resources/testData/checkoutTestData.json";

    String paymentTestData = "src/test/resources/testData/paymentTestData.json";
    static SoftAssert productsAssert = new SoftAssert();

    @Description
            (
                    "Given user open Sauce labs mobile application"+
                            "When click on Menu"+
                            "And click on Login"+
                            "And login Using Valid Email & Password"+
                            "And choose Product"+
                            "And click on counter Plus Button"+
                            "And click on add To Cart button"+
                            "And click on Cart Badge"+
                            "And click on Proceed To Checkout Button"+
                            "And fill Checkout Information"+
                            "And fill Payment Information"+
                            "And click on place order button"+
                            "And click on continue shopping button"+
                            "Then user should return to Home screen"
            )
    @Test(description = "Executing E2E ordering item scenario")
    public void CheckOutOrderTest() throws IOException, ParseException {
        new HomeScreen(driver)
                .ClickOnOpenMenu()
                .ClickOnLoginButton()
                .LoginWithValidEmailAndPassword
                        (
                                JsonReader.TestJson.getJson(loginTestData, "email"),
                                JsonReader.TestJson.getJson(loginTestData, "password")
                        )
                .ClickOnProduct1()
                .clickOnCounterPlusButton()
                .clickOnAddToCartButton()
                .clickOnCartBadge()
                .clickOnProceedToCheckoutButton()
                .fillCheckoutInformation
                        (
                                JsonReader.TestJson.getJson(checkoutTestData, "FullName"),
                                JsonReader.TestJson.getJson(checkoutTestData, "AddressLine"),
                                JsonReader.TestJson.getJson(checkoutTestData, "AddressLine2"),
                                JsonReader.TestJson.getJson(checkoutTestData, "City"),
                                JsonReader.TestJson.getJson(checkoutTestData, "State"),
                                JsonReader.TestJson.getJson(checkoutTestData, "ZipCode"),
                                JsonReader.TestJson.getJson(checkoutTestData, "Country")
                        )
                .fillPaymentInformation
                        (
                                JsonReader.TestJson.getJson(paymentTestData, "FullName"),
                                JsonReader.TestJson.getJson(paymentTestData, "CardNumber"),
                                JsonReader.TestJson.getJson(paymentTestData, "ExpirationDate"),
                                JsonReader.TestJson.getJson(paymentTestData, "SecurityCode")
                        )
                .clickOnPlaceOrderButton()
                .ClickOnContinueShoppingButton();

        String productText = driver.findElement(HomeScreen.products).getText();
        productsAssert.assertEquals(productText, "Products");
    }
}