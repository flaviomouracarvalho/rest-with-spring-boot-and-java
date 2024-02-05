package flavio.projeto.restwithspringbootandjava.model;

import java.io.Serializable;
import java.util.Objects;

public class Person {
    public static final long serialVersionUID = 1L;
    private Long id;
    private String firstName;
    private String lastName;
    private String anddress;
    private String gender;

    public Person(Long id, String firstName, String lastName, String anddress, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.anddress = anddress;
        this.gender = gender;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAnddress() {
        return anddress;
    }

    public void setAnddress(String anddress) {
        this.anddress = anddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(anddress, person.anddress) && Objects.equals(gender, person.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, anddress, gender);
    }
}
