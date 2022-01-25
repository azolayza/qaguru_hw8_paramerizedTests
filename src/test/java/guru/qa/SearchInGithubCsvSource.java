package guru.qa;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class SearchInGithubCsvSource {

    static String str;

    @BeforeAll
    static void beforeAll() {
        str = "!";
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @CsvSource(value = {
        "selenide, selenide / selenide",
        "junit5, junit5"
    })

    @ParameterizedTest(name = "Тестирование поиска repo в github с тестовыми данными")
    void shouldFindRepositoryInGithub(String testData, String searchText){
        SelenideLogger.addListener("allure", new AllureSelenide());
        // открыть страницу github.com
        open("https://github.com/");
        // ввести в поле поиска тестовое слово 1 и нажать Enter
        $("[data-test-selector=nav-search-input]").setValue(testData).pressEnter();
        // нажимаем на линк от первого результата поиска
        $$("ul.repo-list li").first().$("a").click();
        // check: в заголовке встречается результат 1
        $("h1").shouldHave(text(searchText));
    }
    // Повторить для тестового названия репозитория 2 и результата 2
}
