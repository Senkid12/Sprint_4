package org.yandex.prakrikum.pageObject;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.yandex.prakrikum.pageObject.constants.HomePageConstants.*;


@RunWith(Parameterized.class)
public class HomePageTest {
    private WebDriver driver;
    private final String homePageUrl = "https://qa-scooter.praktikum-services.ru/";
    private final By question;
    private final By answer;
    private final String expected;

    public HomePageTest(By question, By answer, String expected) {
        this.question = question;
        this.answer = answer;
        this.expected = expected;
    }
    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][] {
                {QUESTION1, ANSWER1, TEXT_FOR_ANSWER_1},
                {QUESTION2, ANSWER2, TEXT_FOR_ANSWER_2},
                {QUESTION3, ANSWER3, TEXT_FOR_ANSWER_3},
                {QUESTION4, ANSWER4, TEXT_FOR_ANSWER_4},
                {QUESTION5, ANSWER5, TEXT_FOR_ANSWER_5},
                {QUESTION6, ANSWER6, TEXT_FOR_ANSWER_6},
                {QUESTION7, ANSWER7, TEXT_FOR_ANSWER_7},
                {QUESTION8, ANSWER8, TEXT_FOR_ANSWER_8},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(homePageUrl);
    }

    @Test
    public void checkQuestion() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        HomePage objHomePage = new HomePage(driver);
        objHomePage.scrollToQuewstion();
        objHomePage.clickQuestion(question);

        String actualTextForQuestion = driver.findElement(answer).getText();
        assertEquals("Ожидаемый ответ не совпадает с действительным", expected, actualTextForQuestion);

    }

    @After
    public void tearDown() {
        driver.quit();
    }


}

