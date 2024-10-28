package Sample;

import java.io.*;
import java.util.ArrayList;

public class SerializeDeserializeExample {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John Doe", 1));
        employees.add(new Employee("Jane Smith", 2));

        // Serialize the ArrayList
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("employees.ser"))) {
            oos.writeObject(employees);
            System.out.println("Serialization successful");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the ArrayList
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("employees.ser"))) {
            ArrayList<Employee> deserializedEmployees = (ArrayList<Employee>) ois.readObject();
            System.out.println("Deserialization successful");
            deserializedEmployees.forEach(System.out::println);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}