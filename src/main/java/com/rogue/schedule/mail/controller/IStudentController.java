package com.rogue.schedule.mail.controller;

import com.rogue.schedule.mail.dto.BasicDto;
import com.rogue.schedule.mail.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/student")
public interface IStudentController {
  @GetMapping
  ResponseEntity<Page<Student>> getAll(Pageable pageable);

  @PostMapping
  ResponseEntity<BasicDto> saveStudent(@RequestBody Student student);
}
