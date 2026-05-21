package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.logevents.SelenideLogger;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;
import io.qameta.allure.selenide.AllureSelenide;
import static com.codeborne.selenide.Selenide.open;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
class DeliveryTest {

    @BeforeAll
    static void setUpAll() {
        // Добавляем листенер в тестовый класс перед выполнением всех тестов
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        // Удаляем листенер после выполнения всех тестов
        SelenideLogger.removeListener("allure");
    }


    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        // TODO: добавить логику теста в рамках которого будет выполнено планирование и перепланирование встречи.
        // Для заполнения полей формы можно использовать пользователя validUser и строки с датами в переменных
        // firstMeetingDate и secondMeetingDate. Можно также вызывать методы generateCity(locale),
        // generateName(locale), generatePhone(locale) для генерации и получения в тесте соответственно города,
        // имени и номера телефона без создания пользователя в методе generateUser(String locale) в датагенераторе

        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input")
                .press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE)
                .setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").find(Condition.text("Запланировать")).click();
        $("[data-test-id = 'success-notification']")
                .should(Condition.visible, Duration.ofSeconds(15))
                .should(Condition.text("Встреча успешно запланирована на " + firstMeetingDate));
        $("[data-test-id='date'] input")
                .press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE)
                .setValue(secondMeetingDate);
        $$("button").find(Condition.text("Запланировать")).click();
        $("[data-test-id = 'replan-notification']")
                .should(Condition.visible, Duration.ofSeconds(15))
                .should(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $$("button").find(Condition.text("Перепланировать")).click();
        $("[data-test-id = 'success-notification']")
                .should(Condition.visible, Duration.ofSeconds(15))
                .should(Condition.text("Встреча успешно запланирована на " + secondMeetingDate));




    }
}