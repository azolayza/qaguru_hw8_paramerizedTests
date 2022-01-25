package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class SearchContributerWithValueSource {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com/";
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("@BeforeEach");
    }

    static Stream<Arguments> commonSearchTestDataProvider() {
        return Stream.of(
                Arguments.of("selenide/selenide", "Andrei Solntsev"),
                Arguments.of("allure-framework/allure2", "Dmitry Baev ")
        );
    }

        @MethodSource("commonSearchTestDataProvider")
        @ParameterizedTest(name = "Проверка репозиториев на наличие контрибьютеров с тестовыми данными")
        void searchContributerTest(String repo, String expected){
        SelenideLogger.addListener("allure", new AllureSelenide());
        // открыть страничку тестируемого репозитория
        open(repo);
        // подвести мышку к первому элементу в области Contributors
        $$(".Layout-sidebar .BorderGrid-row").find(text("Contributors"))
                .$$("ul li").first().hover();
        // check: в появившемся окошке проверяем наличие текст expected1 = Andrei Solntsev
        $$(".Popover-message").find(visible).shouldHave(text(expected));
        //Повторить для репозитория Allure и найти Contributer = Dmitry Baev
    }
}
