package org.wit.edu.pl.elements;

import org.junit.jupiter.api.*;
import org.wit.edu.pl.WebDriverFactory;
import org.wit.edu.pl.elements.Links.Links;
import org.wit.edu.pl.support.ConfigReader;
import static org.junit.jupiter.api.Assertions.*;


public class LinksIT {
    ThreadLocal<Links> theLinks = new ThreadLocal<>();
    static String browser = ConfigReader.get("browser");

    @BeforeEach
    public void createDriver(){
        theLinks.set(new Links(WebDriverFactory.getWebDriver(browser)));
        Links links = theLinks.get();
        links.openPage();
        links.deleteAdds();
    }

    @AfterEach
    public void quiteBrowser(){
        theLinks.get().quitDriver();
    }

    @Test
    public void clickLinks(){;
        Links links = theLinks.get();

        links.clickHomeButton();
        links.goFollowTab();
        assertEquals("https://demoqa.com/", links.getURL());
        links.closePage();
        links.goFollowTab();

        links.clickDynamicLink();
        links.goFollowTab();
        assertEquals("https://demoqa.com/", links.getURL());
        links.closePage();

    }

}
