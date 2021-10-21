package ru.kozhiev.inal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kozhiev.inal.entity.Professor;
import ru.kozhiev.inal.entity.Student;

import java.util.Collection;

@Repository
public interface StudentCrudRepository extends CrudRepository<Student, Long>{


    @Query("SELECT * FROM students_table WHERE professor_name = :professor_name")
    Collection<Student> findStudentsByProfessor(@Param("professor_name") String professorName);

    void deleteById(Long id);

}
