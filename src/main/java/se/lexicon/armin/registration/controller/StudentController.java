package se.lexicon.armin.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.armin.registration.entities.Student;
import se.lexicon.armin.registration.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {


    StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAll(){

        List<Student> students = new ArrayList<>();
        studentRepository.findAll().iterator().forEachRemaining(students::add);

        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable("id") String id){

        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent())
            return ResponseEntity.ok(optionalStudent.get());
        else
            return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("Id") String id){

        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            studentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/")
    public ResponseEntity<Student> save(@RequestBody Student student){

        Student saveStud = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveStud);
    }

    @PutMapping("/")
    public ResponseEntity<Student> update(@RequestBody Student student){
        Student updStud = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.OK).body(updStud);
    }

}
