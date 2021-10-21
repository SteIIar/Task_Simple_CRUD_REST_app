package ru.kozhiev.inal.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kozhiev.inal.entity.Student;
import ru.kozhiev.inal.repository.StudentCrudRepository;

import java.util.Collection;

@RestController("/students")
public class StudentController {

    @Autowired
    private StudentCrudRepository studRepo;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @PutMapping("/create")
    public Student create(@RequestBody Student student) {
        amqpTemplate.convertAndSend("operations", "student create: " + student.getId());
        return studRepo.save(student);
    }

    @PatchMapping("/update")
    public Student update(@RequestBody Student student) {
        amqpTemplate.convertAndSend("operations", "student update: " + student.getId());
        return studRepo.save(student);
    }

    @DeleteMapping("/delete/{@id}")
    public void delete(@PathVariable("id") Long id) {
        amqpTemplate.convertAndSend("operations", "student delete by id: " + id);
        studRepo.deleteById(id);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Student student) {
        amqpTemplate.convertAndSend("operations", "student delete: " + student.getId());
        studRepo.delete(student);
    }

}
