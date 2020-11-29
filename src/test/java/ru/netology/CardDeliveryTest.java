package ru.netology;

import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
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
        c.add(Calendar.DAY_OF_YEAR, 3);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        String str = format1.format(c.getTime());
        $("[data-test-id=city] input").setValue("Владивосток");
        $("[data-test-id=date] [value]").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] [value]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [value]").setValue(str);
        $("[name='name']").setValue("Светлана Белая");
        $("[name='phone']").setValue("+79111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $(Selectors.withText("Встреча успешно забронирована")).waitUntil(visible, 15000);
    }

    @Test
    void ifCityFilledWithEnglishLetters() {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        String str = format1.format(c.getTime());
        $("[data-test-id='city'] input").setValue("Moscow");
        $("[data-test-id=date] [value]").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] [value]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [value]").setValue(str);
        $("[name='name']").setValue("Светлана Белая");
        $("[name='phone']").setValue("+79111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='city'] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void ifSelectTodayData() {
        Calendar c = new GregorianCalendar();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        String str = format1.format(c.getTime());
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] [value]").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] [value]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [value]").setValue(str);
        $("[name='name']").setValue("Светлана Белая");
        $("[name='phone']").setValue("+79111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void ifUseEnglishLettersInName() {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        String str = format1.format(c.getTime());
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] [value]").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] [value]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [value]").setValue(str);
        $("[name='name']").setValue("Eve Cooper");
        $("[name='phone']").setValue("+79111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='name'] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void ifNotUseCheckbox() {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        String str = format1.format(c.getTime());
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] [value]").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] [value]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [value]").setValue(str);
        $("[name='name']").setValue("Рената Литвинова");
        $("[name='phone']").setValue("+79111111111");
        $("[class='button__text']").click();
        $("[data-test-id='agreement'] .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void ifStayEmptyFieldCity() {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        String str = format1.format(c.getTime());
        $("[data-test-id=date] [value]").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] [value]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [value]").setValue(str);
        $("[name='name']").setValue("Рената Литвинова");
        $("[name='phone']").setValue("+79111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='city'] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void ifStayEmptyFieldData() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] [value]").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] [value]").sendKeys(Keys.BACK_SPACE);
        $("[name='name']").setValue("Рената Литвинова");
        $("[name='phone']").setValue("+79111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='date'] .input__sub").shouldHave(exactText("Неверно введена дата"));
    }

    @Test
    void ifStayEmptyFieldName() {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        String str = format1.format(c.getTime());
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] [value]").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] [value]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [value]").setValue(str);
        $("[name='phone']").setValue("+79111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='name'] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void ifStayEmptyFieldPhone() {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        String str = format1.format(c.getTime());
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] [value]").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] [value]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [value]").setValue(str);
        $("[name='name']").setValue("Рената Литвинова");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void ifSendEmptyForm() {
        $("[class='button__text']").click();
        $("[data-test-id='city'] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

}
