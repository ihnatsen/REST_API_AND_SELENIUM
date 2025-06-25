package org.wit.edu.pl.elements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wit.edu.pl.WebDriverFactory;
import org.wit.edu.pl.elements.RadioButton.RadioButton;
import org.wit.edu.pl.support.ConfigReader;

import static org.junit.jupiter.api.Assertions.*;

public class RadioButtonIT {
    ThreadLocal<RadioButton> theRadioButton = new ThreadLocal<>();
    static String browser = ConfigReader.get("browser");

    @BeforeEach
    public void createDriver(){
        theRadioButton.set(new RadioButton(WebDriverFactory.getWebDriver(browser)));
        RadioButton radioButton = theRadioButton.get();
        radioButton.openPage();
        radioButton.deleteAdds();
    }

    @AfterEach
    public void quiteBrowser(){
        theRadioButton.get().quitDriver();
    }

    @Test
    public void clickToButtons(){
        RadioButton radioButton = theRadioButton.get();
        boolean hasYes = radioButton.outputHas(radioButton.clickToYesButton());
        boolean hasImpressive = radioButton.outputHas(radioButton.clickToImpressiveButton());
        boolean hasDisabled = radioButton.noButtonHasDisabledAttribute();
        assertAll(
                () -> assertTrue(hasYes),
                () -> assertTrue(hasImpressive),
                () -> assertTrue(hasDisabled)
        );
    }


}
