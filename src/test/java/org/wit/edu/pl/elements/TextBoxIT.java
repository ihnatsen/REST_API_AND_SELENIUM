package org.wit.edu.pl.elements;
import com.github.javafaker.*;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Stream;


public class TextBoxIT {
    static Faker getData = new Faker(Locale.ENGLISH, new Random(24));
    static FakeValuesService write = new FakeValuesService(Locale.ENGLISH, new RandomService(new Random(24)));
    WebDriver driver = new ChromeDriver();
    TextBox textBox = new TextBox(driver);

    @BeforeEach
    public void createDriver(){
        driver.manage().window().maximize();
        textBox.openPage();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        new Actions(driver).pause(Duration.ofSeconds(5)).perform();
        js.executeScript(
                "let adds = document.querySelectorAll('iframe, .advertisement');" +
                        "adds.forEach(a => a.remove());");
        js.executeScript("document.querySelector('footer').remove();");


    }

    @ParameterizedTest
    @MethodSource
    public void fillAllFields(List<String> data){
        textBox.fillFullName(data.get(0));
        textBox.fillEmail(data.get(1));
        textBox.fillCurrentAddress(data.get(2));
        textBox.fillPermanentAddress(data.get(3));
        textBox.clickSubmit();

        assertAll(
                () -> assertEquals(data.get(0), textBox.getOutputName()),
                () -> assertEquals(data.get(1), textBox.getOutputEmail()),
                () -> assertEquals(data.get(2), textBox.getCurrentAddress()),
                () -> assertEquals(data.get(3), textBox.getPermanentAddress())
                );

    }

    public static Stream<Arguments> fillAllFields(){
        return Stream.of(Arguments.of(
                List.of(
                        getData.name().fullName(),
                        write.bothify("###@gmail.com"),
                        getData.address().streetName(),
                        getData.address().streetName()
                )
        ));
    }
}
