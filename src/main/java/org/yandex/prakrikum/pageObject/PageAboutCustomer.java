package org.yandex.prakrikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageAboutCustomer {
    WebDriver driver;
    //    Шапка страницы с заказчиком
    private final By pageAboutCustomerHeader = By.className("Header_Header__214zg");
    //    Поле ввода имени закачика
    private final By fieldName = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/input");
    //    Поле ввода фамилии закачика
    private final By fieldSurname = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/input");
    // Поле с адресом заказчика
    private final By fieldAdress = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[3]/input");
    // Поле со станцией метро заказчика
    private final By fieldMetroStation = By.className("select-search__input");
    // Поле с телефоном заказчика
    private final By fieldPhoneNumber = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/input");
    //    Кнопка "Далее"
    private final By nextButton = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button");

    public PageAboutCustomer(WebDriver driver) {
        this.driver = driver;
    }

    //Метод ожидания загрузки страницы
    public void waitLoadCusomerPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(pageAboutCustomerHeader).getText() != null && !driver.findElement(pageAboutCustomerHeader).getText().isEmpty()));
    }
//    Метод заполнения формы с информацией о заказчике и нажатие на кнопку "Далее"
    public void inputFormAboutCustomer(String name, String surname, String adress, String metroStation, String phoneNumber) {
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldSurname).sendKeys(surname);
        driver.findElement(fieldAdress).sendKeys(adress);
        driver.findElement(fieldMetroStation).sendKeys(metroStation, Keys.DOWN, Keys.ENTER);
        driver.findElement(fieldPhoneNumber).sendKeys(phoneNumber);
    }
//    Клик на кнопку "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }


}
