package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.repository.LetterGradeRepository;
import com.trkgrn.studentinformationsystem.api.model.entity.LetterGrade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LetterGradeService {

    private final LetterGradeRepository letterGradeRepository;

    public LetterGradeService(LetterGradeRepository letterGradeRepository) {
        this.letterGradeRepository = letterGradeRepository;
    }

    public LetterGrade saveLetterGrade(LetterGrade letterGrade) {
        return letterGradeRepository.save(letterGrade);
    }

    public LetterGrade getLetterGradeById(Long id) {
        return letterGradeRepository.findById(id).orElse(null);
    }

    public void deleteLetterGradeById(Long id) {
        letterGradeRepository.deleteById(id);
    }

    public LetterGrade updateLetterGrade(LetterGrade letterGrade,Long id){
        Optional<LetterGrade> letterGradeOptional = letterGradeRepository.findById(id);
        if (letterGradeOptional.isPresent()) {
            LetterGrade updatedLetterGrade = letterGradeOptional.get();
            updatedLetterGrade.setLetterGradeId(id);
            updatedLetterGrade.setCoefficient(letterGrade.getCoefficient());
            updatedLetterGrade.setIsPass(letterGrade.getIsPass());
            updatedLetterGrade.setGrade(letterGrade.getGrade());
            updatedLetterGrade.setMin(letterGrade.getMin());
            updatedLetterGrade.setMax(letterGrade.getMax());
            return letterGradeRepository.save(updatedLetterGrade);
        }
        return null;
    }

    public Iterable<LetterGrade> getAllLetterGrades() {
        return letterGradeRepository.findAll();
    }

}
