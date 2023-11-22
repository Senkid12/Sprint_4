package org.yandex.prakrikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.yandex.prakrikum.pageObject.constants.ScooterColors.*;

public class PageAboutScooter {
    WebDriver driver;
    //    Шапка страницы со самоката
    private final By pageAboutScooterHeader = By.className("Header_Header__214zg");
    //    Поле с датой привоза самоката
    private final By fieldDateForOrder = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[1]/div/input");
    //    Поле со сроком аренды
    private final By fieldRentalPeriod = By.xpath(".//span[@class='Dropdown-arrow']");
    //    Чекбоксы с цветом самоката
    private final By blackColor = By.id("black");
    private final By greyColor = By.id("grey");
    // Поле с комментарием
    private final By commentForOrder = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/input");
    // Кнопка "Заказать"
    private final  By orderButton = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button[2]");
    // кнопка подтверждения заказа
    private final By orderConfirmation = By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[2]/button[2]");
//    всплывающее окно после подтверждения заказа
    private final By popUpWindowAfterOrder = By.xpath("/html/body/div/div/div[2]/div[5]");

    public PageAboutScooter(WebDriver driver) {
        this.driver = driver;
    }
    //Метод ожидания загрузки страницы
    public void waitLoadScooterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(pageAboutScooterHeader).getText() != null && !driver.findElement(pageAboutScooterHeader).getText().isEmpty()));
    }

    //    Метод заполнения формы "про аренду"
    public void inputRentalForm(String date, String rentalPeriod, Enum color, String comment) {
        driver.findElement(fieldDateForOrder).sendKeys(date);
        driver.findElement(fieldRentalPeriod).click();
        //Ожидание кликабельности выбадающего списка со сроком аренды
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-menu"))).click();
        //Проверка цвета
        if (color.equals(BLACK)) {
            driver.findElement(blackColor).click();
        } else if (color.equals(GREY)) {
            driver.findElement(greyColor).click();
        }
        driver.findElement(commentForOrder).sendKeys(comment);
    }
    //    Клик на кнопку "Заказать"
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
    // Потдверждение заказа
    public void clickOrderConfirm() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(driver.findElement(orderConfirmation))).click();
    }
    //    Метод ожидания загрузки всплывающего окна после заказа и возращение текста о заказе
    public String getPopUpWindowAfterOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(popUpWindowAfterOrder).getText() != null
                && !driver.findElement(popUpWindowAfterOrder).getText().isEmpty()
        ));
        return driver.findElement(popUpWindowAfterOrder).getText();
    }

}
