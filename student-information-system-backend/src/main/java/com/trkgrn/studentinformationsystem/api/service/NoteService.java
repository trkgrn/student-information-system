package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.dto.SemesterDto;
import com.trkgrn.studentinformationsystem.api.model.entity.Note;
import com.trkgrn.studentinformationsystem.api.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final ModelMapper modelMapper;

    public NoteService(NoteRepository noteRepository, ModelMapper modelMapper) {
        this.noteRepository = noteRepository;
        this.modelMapper = modelMapper;
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    public Note updateNote(Note note, Long id) {
        Optional<Note> noteOptional = noteRepository.findById(id);

        if (noteOptional.isPresent()) {
            Note updatedNote = noteOptional.get();
            updatedNote.setNoteId(id);
            updatedNote.setAverage(note.getAverage());
            updatedNote.setState(note.getState());
            updatedNote.setStudent(note.getStudent());
            updatedNote.setMidtermExam(note.getMidtermExam());
            updatedNote.setFinalExam(note.getFinalExam());
            updatedNote.setLiveLesson(note.getLiveLesson());
            updatedNote.setLetterGrade(note.getLetterGrade());
            return noteRepository.save(updatedNote);
        }

        return null;
    }

    public Iterable<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }

    public Optional<List<Note>> getNotesByStudent_StudentIdAndLiveLesson_IsActiveIsTrue(Long studentId) {
        return noteRepository.getNotesByStudent_StudentIdAndLiveLesson_IsActiveIsTrue(studentId);
    }

    public Optional<List<Note>> getNotesByStudent_StudentIdAndLiveLesson_EducationSeason_Name(Long studentId, Long educationSeasonId) {
        return noteRepository.getNotesByStudent_StudentIdAndLiveLesson_EducationSeason_EducationSeasonId(studentId, educationSeasonId);
    }

    public Optional<List<SemesterDto>> getSemestersByStudent_StudentId(Long studentId) {
        return noteRepository.getSemestersByStudent_StudentId(studentId);
    }

    public Optional<List<Note>> getNotesByStudentIdAndSemester(Long studentId, Long educationSeasonId, Long periodId) {
        return noteRepository.getNotesByStudent_StudentIdAndLiveLesson_EducationSeason_EducationSeasonIdAndLiveLesson_Period_PeriodId(studentId, educationSeasonId, periodId);
    }




}
