package project.springboot.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
        Optional<Student> studentByEmail = studentRepository.findByEmail(student.getEmail());
        Optional<Student> studentByName = studentRepository.findByName(student.getName());

        if(studentByName.isPresent()&&studentByEmail.isPresent()){
            throw new IllegalStateException("Student already in DB");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void updateStudent(Student student) {
        Optional<Student> studentByName = studentRepository.findByName(student.getName());

        if(studentByName.isPresent()){
            studentRepository.save(student);
        }
        System.out.println(student);
    }
}
