package org.wit.edu.pl.elements;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wit.edu.pl.WebDriverFactory;
import org.wit.edu.pl.elements.BrokenLinksImages.BrokenLinksImages;
import org.wit.edu.pl.support.ConfigReader;
import static org.junit.jupiter.api.Assertions.*;

public class BrokenLinksImagesIT {
    ThreadLocal<BrokenLinksImages> theBrokenLinks = new ThreadLocal<>();
    static String browser = ConfigReader.get("browser");

    @BeforeEach
    public void setup(){
        theBrokenLinks.set(
                new BrokenLinksImages(WebDriverFactory.getWebDriver(browser)));
        BrokenLinksImages brokenLinks = theBrokenLinks.get();
        brokenLinks.openPage();
        brokenLinks.deleteAdds();
    }

    @AfterEach
    public void closePage(){
        theBrokenLinks.get().closePage();
    }

    @Test
    public void isAccessedImages(){
        BrokenLinksImages page = theBrokenLinks.get();

        assertFalse(page.brokenImageHeaderContentType().contains("image"));
        assertTrue(page.validImageHeaderContentType().contains("image"));

        assertEquals(200, page.isValidLinkStatusCode());
        assertEquals(500, page.isBrokenLinkStatusCode());
    }


}
