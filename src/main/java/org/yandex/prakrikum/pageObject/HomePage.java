package org.yandex.prakrikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.yandex.prakrikum.pageObject.constants.OrderButtonsConstants.*;

public class HomePage
{
    WebDriver driver;
    //Элемент с шапкой страницы
    private final By homePageHeader = By.className("Header_Header__214zg");
    //Элемент с блоком «Вопросы о важном»
    private final By questionDiv = By.className("Home_FAQ__3uVm4");
    //    Элемент с верхней кнопкой "Заказать"
    private final By upOrderButton = By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div[2]/button[1]");
    //    Элемент с нижней кнопкой "Заказать"
    private final By downOrderButton = By.xpath("//*[@id=\"root\"]/div/div[1]/div[4]/div[2]/div[5]/button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    //Метод ожидания загрузки страницы
    public void waitLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(homePageHeader).getText() != null && !driver.findElement(homePageHeader).getText().isEmpty()));
    }

    //Метод прокрутки до элемента страницы с вопросами
    public void scrollToQuewstion() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionDiv));
    }
    //Клик по вопросу
    public void clickQuestion(By question) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(question)).click();
    }
//    Клик по верхней кнопке "Заказать"
    public void clickUpButtonForOrder() {
        driver.findElement(upOrderButton).click();
    }

    //   Прокрутка до нижей кнопки и клик по кнопке "Заказать"
    public void clickDownButtonForOrder() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        driver.findElement(downOrderButton).click();
    }
//    метод проверки верхней\нижней кнопки и клик по ней
    public void clickOrderCreateButton(Enum button) {
        if (button.equals(UP_BUTTON)) {
            clickUpButtonForOrder();
        } else if (button.equals(DOWN_BUTTON)) {
            clickDownButtonForOrder();
        }
    }


}
