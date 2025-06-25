package org.wit.edu.pl.elements;
import com.github.javafaker.*;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.wit.edu.pl.WebDriverFactory;
import org.wit.edu.pl.elements.TextBox.TextBox;
import org.wit.edu.pl.support.ConfigReader;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Stream;


public class TextBoxIT {
    static String browser = ConfigReader.get("browser");
    static Faker getData = new Faker(Locale.ENGLISH, new Random(24));
    static FakeValuesService write = new FakeValuesService(Locale.ENGLISH, new RandomService(new Random(24)));
    ThreadLocal<TextBox> theTextBox = new ThreadLocal<>();
    @BeforeEach
    public void createDriver(){
        theTextBox.set(new TextBox(WebDriverFactory.getWebDriver(browser)));
        TextBox textBox = theTextBox.get();
        textBox.openPage();
        textBox.deleteAdds();
    }

    @AfterEach
    public void quiteBrowser(){
        theTextBox.get().quitDriver();
    }

    @ParameterizedTest
    @MethodSource
    public void fillAllFields(List<String> data){
        TextBox textBox = theTextBox.get();
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

    @Test
    public void invalidEmail(){
        TextBox textBox = theTextBox.get();

        textBox.fillEmail(write.bothify("###??example.com"));
        textBox.clickSubmit();
        assertTrue(textBox.hasInvalidEmail());
    }

}
