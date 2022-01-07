package project.springboot.demo.student;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@Validated
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

//    @GetMapping("/db/{id}")
//    public Student getStudent(@PathVariable @Min(1) Long id) {
//        return studentService.getStudent(id);
//    }

    @GetMapping("/db/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable @Min(1) Long id) {
        Student readStudent = studentService.getStudent(id);
        return new ResponseEntity<>(readStudent,HttpStatus.FOUND);
    }

//    @PostMapping("/add")
//    public void registerNewStudent(@Valid @RequestBody Student student){
//        studentService.addNewStudent(student);
//    }

    @PostMapping("/add")
    public ResponseEntity<Student> registerNewStudent(@Valid @RequestBody Student student){
        studentService.addNewStudent(student);
        return new ResponseEntity<>(student,HttpStatus.FOUND);
    }

//    @PutMapping("/update/{id}")
//    public void updateStudentRegister(@Valid @RequestBody Student student, @PathVariable @Min(1) Long id){
//        studentService.updateStudent(student,id);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudentRegister(@Valid @RequestBody Student student, @PathVariable @Min(1) Long id){
        studentService.updateStudent(student,id);
        return new ResponseEntity<>(student,HttpStatus.FOUND);
    }

    @PutMapping("/update")
    public void updateStudentRegister(@Valid @RequestBody Student student){
        studentService.updateStudent(student);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>("not valid field/argument in request: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}


