package org.yandex.prakrikum.pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.yandex.prakrikum.pageObject.constants.OrderButtonsConstants.*;
import static org.yandex.prakrikum.pageObject.constants.RentalPeriodConstants.*;
import static org.yandex.prakrikum.pageObject.constants.ScooterColors.*;


@RunWith(Parameterized.class)
public class CreateOrderTest {
    private WebDriver driver;
    private final String homePageUrl = "https://qa-scooter.praktikum-services.ru/";
    private final Enum button;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phoneNumber;
    private final String date;
    private final String rentalPeriod;
    private final Enum color;
    private final String comment;
//    переменная строки ожидаемого результата после заказа
    private final String expectedStatusOrder = "Заказ оформлен";


    public CreateOrderTest(Enum button, String name, String surname, String address, String metro, String phoneNumber, String date, String rentalPeriod, Enum color, String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object getParameters(){
        return new Object[][] {
                {UP_BUTTON, "Даня", "Козловский", "Москва", "Юго-Западная", "+79646373583", "22.11.2023", SIX_DAYS, BLACK, "Коммент 1"},
                {DOWN_BUTTON, "Доминик", "Торетто", "Семейная д.5", "Выхино", "+79646373523", "25.11.2023", ONE_DAY, GREY, "Для семьи"},
                {DOWN_BUTTON, "Доминик", "Дизель", "Семейная д.4", "Проспект Вернадского", "+79646373523", "27.11.2023", TWO_DAYS, BLACK, "Еще раз для семьи"},
                {UP_BUTTON, "Вин", "Торетто", "Семейная д.6", "Выхино", "+79646373523", "28.11.2023", THREE_DAYS, BLACK, "Последний заезд ради семьи"}
        };
    }

    @Before
    public void startUp() {

        WebDriverManager.firefoxdriver().clearDriverCache().clearResolutionCache().setup();
        driver = new FirefoxDriver();
        driver.get(homePageUrl);
    }
    @Test
    public void testCreateOrder() {
        // Добавил неявное ожидание
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickOrderCreateButton(button);

        PageAboutCustomer objPageAboutCustomer = new PageAboutCustomer(driver);
        objPageAboutCustomer.inputFormAboutCustomer(name, surname, address, metro, phoneNumber);
        objPageAboutCustomer.clickNextButton();

        PageAboutScooter objPageAboutScooter = new PageAboutScooter(driver);
        objPageAboutScooter.inputRentalForm(date, rentalPeriod, color, comment);
        objPageAboutScooter.clickOrderButton();
        objPageAboutScooter.clickOrderConfirm();
        // Согласен тут не подумал). Исправляю
        assertTrue(objPageAboutScooter.getPopUpWindowAfterOrder().contains(expectedStatusOrder));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
