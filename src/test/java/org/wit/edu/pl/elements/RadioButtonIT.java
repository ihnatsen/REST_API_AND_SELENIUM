package org.wit.edu.pl.elements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class RadioButtonIT {
    ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    ThreadLocal<RadioButton> radioButton = new ThreadLocal<>();

    @BeforeEach
    public void createDriver(){
        driver.set(new ChromeDriver());
        radioButton.set(new RadioButton(driver.get()));
        driver.get().manage().window().maximize();
        radioButton.get().openPage();
        JavascriptExecutor js = (JavascriptExecutor) driver.get();
        new Actions(driver.get()).pause(Duration.ofSeconds(5)).perform();
        js.executeScript(
                "let adds = document.querySelectorAll('iframe, .advertisement');" +
                        "adds.forEach(a => a.remove());");
        js.executeScript("document.querySelector('footer').remove();");
    }

    @AfterEach
    public void closePage(){
        driver.get().quit();
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
