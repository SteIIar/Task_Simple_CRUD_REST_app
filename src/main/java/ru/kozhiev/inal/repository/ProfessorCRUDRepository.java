package ru.kozhiev.inal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kozhiev.inal.entity.Professor;

@Repository
public interface ProfessorCRUDRepository extends CrudRepository<Professor, Long> {

    void deleteById(Long id);
}
