package org.wit.edu.pl.elements.WebTable.skeleton;

import java.util.List;
import java.util.function.Function;

public interface Table {
    /**
    * Classes implementing this interface are required
    * to properly override equals() and hashCode()
    **/

    public void clickFirstName();
    public void clickLastName();
    public void clickAge();
    public void clickEmail();
    public void clickSalary();
    public void clickDepartment();
    public void clickEdit();
    public void clickDelete();
    public List<Employee> sortBy(Function<Employee, Integer> keyExtractor, List<Employee> table);

    /**
    * That method pars a current table, which is website to List<Employee>.
    * Employee is record class, which represents table's row.
    * List<Employee> represents the table in Java.
    **/
    public List<Employee> getTable();
}
