package org.wit.edu.pl.elements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wit.edu.pl.WebDriverFactory;
import org.wit.edu.pl.elements.RadioButton.RadioButton;
import org.wit.edu.pl.support.ConfigReader;

import static org.junit.jupiter.api.Assertions.*;

public class RadioButtonIT {
    ThreadLocal<RadioButton> radioButton = new ThreadLocal<>();
    static String browser = ConfigReader.get("browser");

    @BeforeEach
    public void createDriver(){

        radioButton.set(new RadioButton(WebDriverFactory.getWebDriver(browser)));
        radioButton.get().openPage();
        radioButton.get().deleteAdds();
    }

    @AfterEach
    public void closePage(){
        radioButton.get().closePage();
    }

    @Test
    public void clickToButtons(){
        boolean hasYes = radioButton.get().outputHas(radioButton.get().clickToYesButton());
        boolean hasImpressive = radioButton.get().outputHas(radioButton.get().clickToImpressiveButton());
        boolean hasDisabled = radioButton.get().noButtonHasDisabledAttribute();
        assertAll(
                () -> assertTrue(hasYes),
                () -> assertTrue(hasImpressive),
                () -> assertTrue(hasDisabled)
        );
    }


}
