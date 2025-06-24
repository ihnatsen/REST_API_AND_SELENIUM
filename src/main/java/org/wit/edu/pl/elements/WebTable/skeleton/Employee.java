package org.wit.edu.pl.elements.WebTable.skeleton;

import java.util.List;

public record Employee(
        String firstName,
        String lastName,
        String age,
        String email,
        String salary,
        String department) {

    public Employee(List<String> data){
        this(
                data.get(0),
                data.get(1),
                data.get(2),
                data.get(3),
                data.get(4),
                data.get(5));
    }
}
