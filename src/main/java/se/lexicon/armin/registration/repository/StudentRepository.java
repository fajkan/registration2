package se.lexicon.armin.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.armin.registration.entities.Student;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
    List<Student> findByEmail(String email);
    List<Student> findByFirstNameAndLastName(String firstName, String lastName);
}
