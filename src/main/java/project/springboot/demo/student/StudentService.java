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

    public void updateStudent(Student student, Long id) {
        Student studentByName = studentRepository.findByName(student.getName()).map(
                updatedStudent -> {
                    updatedStudent.setName(student.getName());
                    updatedStudent.setEmail(student.getEmail());
                    updatedStudent.setDateOfBirth((student.getDateOfBirth()));
                    return studentRepository.save(updatedStudent);
                }).orElseGet(() -> {
            student.setId(id);
            return studentRepository.save(student);
        });
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }
}
