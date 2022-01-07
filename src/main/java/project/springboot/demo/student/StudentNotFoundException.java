package project.springboot.demo.student;

public class StudentNotFoundException extends RuntimeException {

    StudentNotFoundException(Long id) {
        super("Could not find student " + id);
    }
}
