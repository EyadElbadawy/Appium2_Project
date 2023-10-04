package demoApp.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ReviewProductScreen {
    private final By placeOrderButton = AppiumBy.xpath("//*[@content-desc=\"Place Order button\"]");

    AndroidDriver driver;

    public ReviewProductScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * @return CheckoutCompleteScreen
     */
    public CheckoutCompleteScreen clickOnPlaceOrderButton() {
        driver.findElement(placeOrderButton).click();
        return new CheckoutCompleteScreen(driver);
    }
}
