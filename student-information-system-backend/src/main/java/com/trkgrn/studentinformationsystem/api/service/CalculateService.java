package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.LetterGrade;
import com.trkgrn.studentinformationsystem.api.model.entity.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateService {
    private final LetterGradeService letterGradeService;

    public CalculateService(LetterGradeService letterGradeService) {
        this.letterGradeService = letterGradeService;
    }

    public Note calculateNote(Note note) {
        Double mid = note.getLiveLesson().getMidtermPercent() / 100.0;
        Double fnl = note.getLiveLesson().getFinalPercent() / 100.0;
        if (note.getMidtermExam() != null && note.getFinalExam() != null) {
            Double average =  (note.getMidtermExam() * mid + note.getFinalExam() * fnl);
            note.setAverage(average);
            note.setLetterGrade(getLetterGradeByAverage(average));
        } else {
            note.setAverage(null);
            note.setLetterGrade(null);
        }
        return note;
    }

    private LetterGrade getLetterGradeByAverage(Double average) {
        List<LetterGrade> letterGrades = (List<LetterGrade>) letterGradeService.getAllLetterGrades();
        for (LetterGrade letterGrade : letterGrades) {
            if (average >= letterGrade.getMin() && average <= letterGrade.getMax()) {
                return letterGrade;
            }
        }
        return null;
    }
}
