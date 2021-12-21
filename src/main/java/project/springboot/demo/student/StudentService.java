package project.springboot.demo.student;

import java.time.LocalDate;
import java.util.List;

public class StudentService {

    public List<Student> getStudents(){
        return List.of(new Student(
                        1L,
                        "Jose Osorio",
                        "joseosorio@mail.com",
                        32,
                        LocalDate.of(1989,9,8)
                ));
    }
}
