package org.yandex.prakrikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class PageAboutCustomer {
    WebDriver driver;
    // Поля формы с заказчиком. Исправил локаторы на более читаемые
    //    Поле ввода имени закачика
    private final By fieldName = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Имя']");
    //    Поле ввода фамилии закачика
    private final By fieldSurname = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Фамилия']");
    // Поле с адресом заказчика
    private final By fieldAdress = By.xpath(".//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Адрес: куда привезти заказ']");
    // Поле со станцией метро заказчика
    private final By fieldMetroStation = By.className("select-search__input");
    // Поле с телефоном заказчика
    private final By fieldPhoneNumber = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Телефон: на него позвонит курьер']");
    //    Кнопка "Далее"
    private final By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public PageAboutCustomer(WebDriver driver) {
        this.driver = driver;
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
