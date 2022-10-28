package com.rogue.schedule.mail.repository;

import com.rogue.schedule.mail.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student, String> {
}
