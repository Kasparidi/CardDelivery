package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class CityCalendarTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldChooseCity() {
        String str = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=city] input").setValue("Во");
        $$("[class=popup__container] .menu-item__control");
        $(byText("Волгоград")).click();
        $("[data-test-id=date] [value]").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        $("[data-test-id=date] [value]").setValue(str);
        $("[name='name']").setValue("Светлана Белая");
        $("[name='phone']").setValue("+79111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=notification] .notification__content").waitUntil(visible, 15000).shouldHave(exactText("Встреча успешно забронирована на " + str));
    }

    @Test
    void shouldChooseDataInCalendar() {
        $("[data-test-id=city] input").setValue("Москва");
        LocalDate defaultDay = LocalDate.now().plusDays(3);
        LocalDate planDay = LocalDate.now().plusDays(30);
        String str = LocalDate.now().plusDays(30).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] [value]").click();
        if ((planDay.getYear() > defaultDay.getYear() | planDay.getMonthValue() > defaultDay.getMonthValue())) {
            $(".calendar__arrow_direction_right[data-step='1']").click();
        }
        String seekingDay = String.valueOf(planDay.getDayOfMonth());
        $$("td.calendar__day").find(text(seekingDay)).click();
        $("[name='name']").setValue("Светлана Белая");
        $("[name='phone']").setValue("+79111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=notification] .notification__content").waitUntil(visible, 15000).shouldHave(exactText("Встреча успешно забронирована на " + str));
    }

}
