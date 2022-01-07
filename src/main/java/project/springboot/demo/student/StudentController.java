package project.springboot.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/first")
    public Student firstStudents(){
        return studentService.getStudents().get(0);
    }

    @GetMapping("/list")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping("/db/{id}")
    public void getStudent(@PathVariable Long id) {
        studentService.getStudent(id);
    }

    @PostMapping("/add")
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @PutMapping("/update/{id}")
    public void updateStudentRegister(@RequestBody Student student, @PathVariable Long id){
        studentService.updateStudent(student,id);
    }

}


