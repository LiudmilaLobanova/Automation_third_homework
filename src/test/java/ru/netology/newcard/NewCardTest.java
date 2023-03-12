package ru.netology.newcard;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewCardTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll(){
//        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
//        driver = new ChromeDriver();

    }

    @AfterEach
    void tearDown(){
        driver.quit();
        driver = null;
    }

    @Test
    void shouldBeSuccess() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анна-Мария Виктория");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+74991111111");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected,actual);

    }

    @Test
    void shouldNotBeSuccessLatinName() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("ertgfggs");
        driver.findElement(By.className("button__content")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText().trim();

        Assertions.assertEquals(expected,actual);

    }

    @Test
    void shouldNotBeSuccessDigitsInName() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анна25");
        driver.findElement(By.className("button__content")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText().trim();

        Assertions.assertEquals(expected,actual);

    }

    @Test
    void shouldNotBeSuccessSpecialCharactersInName() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анна$");
        driver.findElement(By.className("button__content")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText().trim();

        Assertions.assertEquals(expected,actual);

    }

    @Test
    void shouldNotBeSuccessEmptyName() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+74911111");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText().trim();

        Assertions.assertEquals(expected,actual);

    }

    @Test
    void shouldNotBeSuccessEmptyPhone() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анна");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText().trim();

        Assertions.assertEquals(expected,actual);

    }

    @Test
    void shouldNotBeSuccessShortPhone() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анна-Мария Виктория");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+74911111");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText().trim();

        Assertions.assertEquals(expected,actual);

    }

    @Test
    void shouldNotBeSuccessLongtPhone() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анна-Мария Виктория");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+749992222222");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText().trim();

        Assertions.assertEquals(expected,actual);

    }
    @Test
    void shouldNotBeSuccessWrongFormatPhone() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анна-Мария Виктория");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("74991112233");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText().trim();

        Assertions.assertEquals(expected,actual);

    }

    @Test
    void shouldNotBeSuccessCheckMarkNotCheckedTestByCode() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анна-Мария Виктория");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+74991111111");
        driver.findElement(By.className("button__content")).click();

        Assertions.assertTrue(driver.findElement(By.className("input_invalid")).isDisplayed());

    }

}
