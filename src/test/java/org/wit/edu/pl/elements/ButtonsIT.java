package org.wit.edu.pl.elements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wit.edu.pl.WebDriverFactory;
import org.wit.edu.pl.elements.Buttons.Buttons;
import org.wit.edu.pl.support.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ButtonsIT {
    ThreadLocal<Buttons> buttons = new ThreadLocal<>();
    static String browser = ConfigReader.get("browser");

    @BeforeEach
    public void createDriver(){
        buttons.set(new Buttons(WebDriverFactory.getWebDriver(browser)));
        buttons.get().openPage();
        buttons.get().deleteAdds();
    }

    @AfterEach
    public void closePage(){
        buttons.get().closePage();
    }

    @Test
    public void clickDoubleClick(){
        buttons.get().clickDoubleClick();
        assertEquals("You have done a double click", buttons.get().getDoubleClickMessage());
    }

    @Test
    public void clickRightClick(){
        buttons.get().clickRightClick();
        assertEquals("You have done a right click", buttons.get().getRightClickMessage());
    }

    @Test
    public void clickClickMe(){
        buttons.get().clickClickMe();
        assertEquals("You have done a dynamic click", buttons.get().getClickMeClickMessage());
    }
}
