package project.springboot.demo.student;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    @NotEmpty(message = "name must not be empty")
    @Size(min = 2, message = "User name should have at least 2 characters")
    private String name;

    @NotEmpty(message = "email must not be empty")
    @Email(message = "email should be a valid email")
    private String email;

    @Transient
    private Integer age;

    @Past(message="date of birth must be less than today")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;

    public Student() {
    }

    public Student(String name,
                   String email,
                   LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

//    public Student(Long id,
//                   String name,
//                   String email,
//                   LocalDate dateOfBirth) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.dateOfBirth = dateOfBirth;
//    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return Period.between(dateOfBirth,LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Student))
            return false;
        Student student = (Student) o;
        return Objects.equals(this.id, student.id) && Objects.equals(this.name, student.name)
                && Objects.equals(this.email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.email);
    }


    @Override
    public String toString() {
        return "Student{"+
                "id="+id+
                ", name="+name+
                ", email="+email+
                ", dateOfBirth="+dateOfBirth+
                ", age="+age+
                "}";
    }
}
