package com.trkgrn.studentinformationsystem.api.repository;

import com.trkgrn.studentinformationsystem.api.model.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {
}
