package Sample;

import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', id=" + id + "}";
    }
}