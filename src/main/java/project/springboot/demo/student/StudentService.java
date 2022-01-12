package project.springboot.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findByEmail(student.getEmail());
        Optional<Student> studentByName = studentRepository.findByName(student.getName());

        if (studentByName.isPresent() && studentByEmail.isPresent()) {
            throw new IllegalStateException("Student already in DB");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    public void updateStudent(Student student, Long id) {
        studentRepository.findByName(student.getName()).map(
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

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exist"));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)) {

            Optional<Student> studentOptional = studentRepository.findByEmail(email);

            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalStateException(
                    "Student no present in DB");
        }else{
            studentRepository.deleteById(id);
        }
    }
}
