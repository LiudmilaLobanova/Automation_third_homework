package ru.netology.newcard;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewCardTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll(){
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
        driver = null;
    }

    @Test
    void shouldBeSuccess() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Елизавета");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+74991111111");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected,actual);

    }
}
