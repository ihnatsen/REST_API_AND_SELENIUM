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
    ThreadLocal<TextBox> textBox = new ThreadLocal<>();
    @BeforeEach
    public void createDriver(){
        textBox.set(new TextBox(WebDriverFactory.getWebDriver(browser)));
        textBox.get().openPage();
        textBox.get().deleteAdds();
    }

    @AfterEach
    public void closePage(){
        textBox.get().closePage();
    }

    @ParameterizedTest
    @MethodSource
    public void fillAllFields(List<String> data){
        textBox.get().fillFullName(data.get(0));
        textBox.get().fillEmail(data.get(1));
        textBox.get().fillCurrentAddress(data.get(2));
        textBox.get().fillPermanentAddress(data.get(3));
        textBox.get().clickSubmit();

        assertAll(
                () -> assertEquals(data.get(0), textBox.get().getOutputName()),
                () -> assertEquals(data.get(1), textBox.get().getOutputEmail()),
                () -> assertEquals(data.get(2), textBox.get().getCurrentAddress()),
                () -> assertEquals(data.get(3), textBox.get().getPermanentAddress())
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
        textBox.get().fillEmail(write.bothify("###??example.com"));
        textBox.get().clickSubmit();
        assertTrue(textBox.get().hasInvalidEmail());
    }

}
