package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.Note;
import com.trkgrn.studentinformationsystem.api.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
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


}
