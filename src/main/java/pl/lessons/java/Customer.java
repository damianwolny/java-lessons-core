package pl.lessons.java;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

public class Customer implements Serializable {

    public Customer(String title, Integer age) {

        this.title = title;
        this.age = age;
    }

    /**
     *
     */
    private String title;
    private Integer age;




    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(title, customer.title) &&
                Objects.equals(age, customer.age);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, age);
    }
}
