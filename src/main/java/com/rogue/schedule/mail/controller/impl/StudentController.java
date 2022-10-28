package com.rogue.schedule.mail.controller.impl;

import com.rogue.schedule.mail.controller.IStudentController;
import com.rogue.schedule.mail.dto.BasicDto;
import com.rogue.schedule.mail.model.Student;
import com.rogue.schedule.mail.service.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class StudentController implements IStudentController {
  private final IStudentService studentService;

  @Override
  public ResponseEntity<Page<Student>> getAll(Pageable pageable) {
    return ResponseEntity.ok(studentService.getAllStudents(pageable));
  }

  @Override
  public ResponseEntity<BasicDto> saveStudent(Student student) {
    return ResponseEntity.ok(studentService.createStudent(student));
  }
}
