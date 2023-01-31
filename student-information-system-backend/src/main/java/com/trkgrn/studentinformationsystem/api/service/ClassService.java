package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.Class;
import com.trkgrn.studentinformationsystem.api.repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public Class saveClass(Class newClass) {
        return classRepository.save(newClass);
    }

    public Class getClassById(Long id) {
        return classRepository.findById(id).orElse(null);
    }

    public Iterable<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public void deleteClassById(Long id) {
        classRepository.deleteById(id);
    }

    public Class updateClass(Class newClass, Long id){
        Optional<Class> classOptional = classRepository.findById(id);

        if (classOptional.isPresent()) {
            Class classFromDb = classOptional.get();
            classFromDb.setClassId(id);
            classFromDb.setBranch(newClass.getBranch());
            classFromDb.setGradeYear(newClass.getGradeYear());
            return classRepository.save(classFromDb);
        }
        return null;
    }


}
