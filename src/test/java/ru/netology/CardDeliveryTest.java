package ru.netology;

import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


class CardDeliveryTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void happyPath() {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR,3);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        String str = format1.format(c.getTime());
        $("[data-test-id=city] input").setValue("Владивосток");
        $("[placeholder='Дата встречи']").clear();
        $("[placeholder='Дата встречи']").setValue(str);
        $("[name='name']").setValue("Светлана Белая");
        $("[name='phone']").setValue("+79111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $(Selectors.withText("Встреча успешно забронирована")).waitUntil(visible,12000);



    }
}
