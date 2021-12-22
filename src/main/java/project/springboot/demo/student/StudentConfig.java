package project.springboot.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
            Student jose = new Student(
                    "Jose Osorio",
                    "joseosorio@mail.com",
                    //32,
                    LocalDate.of(1989,9,8)
            );

            Student tim = new Student(
                    "Tim buchalka",
                    "buchalka@mail.com",
                    //45,
                    LocalDate.of(1976,5,13)
            );

            repository.saveAll(
                    List.of(jose,tim)
            );
        };
    }
}
