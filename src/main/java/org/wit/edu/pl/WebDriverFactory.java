package org.wit.edu.pl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class WebDriverFactory {

    static final String PATH_TO_WIN_CHROMEDRIVER = "src/main/resources/webDrivers/win-chromedriver.exe";
    static final String PATH_TO_MAC_CHROMEDRIVER = "src/main/resources/webDrivers/mac-chromedriver.exe";
    static final String PATH_TO_WIN_GECKO = "src/main/resources/webDrivers/win-geckodriver.exe";

    private static WebDriver getChrome(){

        String pathToDriver = switch (System.getProperty("os.name")) {
            case "Windows 10" -> PATH_TO_WIN_CHROMEDRIVER;
            case "Mac OS X" -> PATH_TO_MAC_CHROMEDRIVER;
            default -> throw new RuntimeException("There's no driver.");
        };

        System.setProperty("webdriver.chrome.driver", pathToDriver);

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver getFirefoxDriver(){
        String pathToDriver = switch (System.getProperty("os.name")) {
            case "Windows 10" -> PATH_TO_WIN_GECKO;
            case "Mac OS X" -> "There's no mac gecko driver :c ";
            default -> throw new RuntimeException("There's no driver.");
        };

        System.setProperty("webdriver.gecko.driver", pathToDriver);

        WebDriver driver = new FirefoxDriver(new FirefoxOptions().setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe"));
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getWebDriver(String browser) {
        return switch (browser) {
            case "CHROME" -> getChrome();
            case "FIREFOX" -> getFirefoxDriver();
            default -> getChrome();
        };
    }

    public static void main(String[] args) {
        WebDriverFactory.getFirefoxDriver();
    }

}
