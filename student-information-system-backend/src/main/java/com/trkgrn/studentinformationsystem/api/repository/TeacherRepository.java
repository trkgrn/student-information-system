package com.trkgrn.studentinformationsystem.api.repository;

import com.trkgrn.studentinformationsystem.api.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
