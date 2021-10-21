package ru.kozhiev.inal.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kozhiev.inal.entity.Professor;
import ru.kozhiev.inal.repository.ProfessorCRUDRepository;

@RestController("/professors")
public class ProfessorController {

    @Autowired
    private ProfessorCRUDRepository profRepo;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @PutMapping("/create")
    public Professor create(@RequestBody Professor professor) {
        amqpTemplate.convertAndSend("operations", "professor create: " + professor.getId());
        return profRepo.save(professor);
    }

    @PatchMapping("/update")
    public Professor update(@RequestBody Professor professor) {
        amqpTemplate.convertAndSend("operations", "professor update: " + professor.getId());
        return profRepo.save(professor);
    }

    @DeleteMapping("/delete/{@id}")
    public void delete(@PathVariable("id") Long id) {
        amqpTemplate.convertAndSend("operations", "professor delete by id: " + id);
        profRepo.deleteById(id);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Professor professor) {
        amqpTemplate.convertAndSend("operations", "professor delete: " + professor.getId());
        profRepo.delete(professor);
    }

}