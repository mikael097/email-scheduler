package com.rogue.schedule.mail.service;

import com.rogue.schedule.mail.dto.BasicDto;
import com.rogue.schedule.mail.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService {
  Page<Student> getAllStudents(Pageable pageable);

  BasicDto createStudent(Student student);
}
