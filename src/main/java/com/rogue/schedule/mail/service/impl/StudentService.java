package com.rogue.schedule.mail.service.impl;

import com.rogue.schedule.mail.dto.BasicDto;
import com.rogue.schedule.mail.model.Student;
import com.rogue.schedule.mail.repository.IStudentRepository;
import com.rogue.schedule.mail.service.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService implements IStudentService {
  private final IStudentRepository studentRepository;

  @Override
  public Page<Student> getAllStudents(Pageable pageable) {
    return studentRepository.findAll(pageable);
  }

  @Override
  public BasicDto createStudent(Student student) {
    var savedStudent = studentRepository.save(student);
    return BasicDto.builder()
            .id(savedStudent.getId())
            .message("Created")
            .build();
  }
}
