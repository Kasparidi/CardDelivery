package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
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
        String str = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] [role=button]").click();
        $("[class=calendar__layout] .calendar__day_state_current").click();
        $("[name='name']").setValue("Светлана Белая");
        $("[name='phone']").setValue("+79111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=notification] .notification__content").waitUntil(visible, 15000).shouldHave(exactText("Встреча успешно забронирована на " + str));
    }

}
