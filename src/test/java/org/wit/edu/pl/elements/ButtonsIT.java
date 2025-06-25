package org.wit.edu.pl.elements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wit.edu.pl.WebDriverFactory;
import org.wit.edu.pl.elements.Buttons.Buttons;
import org.wit.edu.pl.support.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ButtonsIT {
    ThreadLocal<Buttons> theButtons = new ThreadLocal<>();
    static String browser = ConfigReader.get("browser");

    @BeforeEach
    public void createDriver(){
        theButtons.set(new Buttons(WebDriverFactory.getWebDriver(browser)));
        Buttons buttons = theButtons.get();

        buttons.openPage();
        buttons.deleteAdds();
    }

    @AfterEach
    public void quiteBrowser(){
        Buttons buttons = theButtons.get();
        buttons.quitDriver();
    }

    @Test
    public void clickDoubleClick(){
        Buttons buttons = theButtons.get();
        buttons.clickDoubleClick();
        assertEquals("You have done a double click", buttons.getDoubleClickMessage());
    }

    @Test
    public void clickRightClick(){
        Buttons buttons = theButtons.get();
        buttons.clickRightClick();
        assertEquals("You have done a right click", buttons.getRightClickMessage());
    }

    @Test
    public void clickClickMe(){
        Buttons buttons = theButtons.get();
        buttons.clickClickMe();
        assertEquals("You have done a dynamic click", buttons.getClickMeClickMessage());
    }
}
