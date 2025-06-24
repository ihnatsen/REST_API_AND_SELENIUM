package org.wit.edu.pl.elements.WebTable.skeleton;

import org.openqa.selenium.WebElement;

public interface Form {
    public void fillFirstName(String firstName);
    public void fillLastName(String  lastName);
    public void fillEmail(String email);
    public void fillAge(String age);
    public void fillSalary(String salary);
    public void fillDepartment(String department);
    public void clickSubmit();
    public Boolean isHasMaxLen(WebElement element, int len);
    public Boolean hasPattern(WebElement element, String pattern);
    public void clearFirstName();

}
