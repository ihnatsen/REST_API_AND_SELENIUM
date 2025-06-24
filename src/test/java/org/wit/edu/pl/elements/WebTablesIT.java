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
import org.openqa.selenium.WebDriver;
import org.wit.edu.pl.WebDriverFactory;
import org.wit.edu.pl.elements.WebTable.*;
import org.wit.edu.pl.elements.WebTable.skeleton.Employee;
import org.wit.edu.pl.elements.WebTable.skeleton.Table;
import org.wit.edu.pl.support.ConfigReader;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Stream;

public class WebTablesIT {
    static String browser = ConfigReader.get("browser");
    static Faker getData = new Faker(Locale.ENGLISH, new Random(24));
    static FakeValuesService write = new FakeValuesService(Locale.ENGLISH, new RandomService(new Random(24)));
    ThreadLocal<WebTables> theWebTablest = new ThreadLocal<>();

    @BeforeEach
    public void createDriver(){
        WebDriver driver = WebDriverFactory.getWebDriver(browser);
        RegistrationForm form = new RegistrationForm(driver);
        Table table = new EmployeeTable(driver);
        theWebTablest.set(new WebTables(driver, form, table));

        WebTables webTables = theWebTablest.get();
        webTables.openPage();
        webTables.deleteAdds();
    }

    @AfterEach
    public void closePage(){
        theWebTablest.get().closePage();
    }
    @ParameterizedTest
    @MethodSource
    public void fillField(String firstName, String lastName, String email, String age, String salary, String department){
        WebTables webTables = theWebTablest.get();

        webTables.clickAdd();
        webTables.fillFirstName(firstName);
        webTables.fillLastName(lastName);
        webTables.fillEmail(email);
        webTables.fillAge(age);
        webTables.fillSalary(salary);
        webTables.fillDepartment(department);
        webTables.clickSubmit();
    }


    @Test
    public void sortTable(){
        List<Employee> was, is, expected;
        WebTables webTables = theWebTablest.get();

        // sorted by first name.
        was = webTables.getTable();
        webTables.clickFirstName();
        is = webTables.getTable();
        expected = webTables.sortBy(e -> (int) e.firstName().charAt(0), was);

        assertEquals(expected, is);

        // sorted by last name.
        was = is;
        webTables.clickLastName();
        is = webTables.getTable();
        expected = webTables.sortBy(e -> (int) e.lastName().charAt(0), was);

        assertEquals(expected, is);

        // sorted by age.
        was = is;
        webTables.clickAge();
        is = webTables.getTable();
        expected = webTables.sortBy(e -> Integer.parseInt(e.age()), was);
        assertEquals(expected, is);

        // sorted by email
        was = is;
        webTables.clickEmail();
        is = webTables.getTable();
        expected = webTables.sortBy(e -> (int) e.email().charAt(0), was);

        assertEquals(expected, is);

        // sorted by salary
        was = is;
        webTables.clickSalary();
        is = webTables.getTable();
        expected = webTables.sortBy(e -> Integer.parseInt(e.salary()), was);

        assertEquals(expected, is);

        // sorted by department
        was = is;
        webTables.clickDepartment();
        is = webTables.getTable();
        expected = webTables.sortBy(e -> (int) e.department().charAt(0), was);
        assertEquals(expected, is);
    }

    @Test
    public void deleteRow(){
        List<Employee> was, is, expected;
        WebTables webTables = theWebTablest.get();

        was = webTables.getTable();
        webTables.clickDelete();
        is = webTables.getTable();
        expected = was.subList(1, was.size());
        assertEquals(expected.size(), is.size());
    }

    @Test
    public void editRow(){
        Employee was, is, expected, row;
        WebTables webTables = theWebTablest.get();

        was = webTables.getTable().getFirst();
        webTables.clickEdit();
        webTables.clearFirstName();


    }

    public static Stream<Arguments> fillField(){
        return Stream.of(Arguments.of(
                getData.name().lastName(),
                getData.name().firstName(),
                write.bothify("###??@example.com"), // email
                write.bothify("##"),                // age
                write.bothify("##"),                // salary
                getData.company().name()));           // department
    }


}
