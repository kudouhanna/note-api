package com.example.noteapi.service;

import com.example.noteapi.dto.Note;
import com.example.noteapi.exception.NoteArgumentNotValidException;
import com.example.noteapi.exception.NoteNotFoundException;
import com.example.noteapi.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Note saveNote(Note note){
//        タイトルが空の場合のバリデーション
        if(note.getTitle() == null || note.getTitle().isEmpty()){
            throw new NoteArgumentNotValidException("title is empty");
        }

//        contentが空の場合のバリデーション
        if(note.getContent() == null || note.getContent().isEmpty()){
            throw new NoteArgumentNotValidException("content is empty");
        }

        noteRepository.insertNote(note);
        return note;
    }

    public Note findById(long id){
        Note note = noteRepository.findById(id);

        if (note == null) {
            throw new NoteNotFoundException(id);
        }

        return note;
    }

    public boolean delete(long id){
        if(!noteRepository.delete(id)){
            throw new NoteNotFoundException(id);
        }
        return true;
    }

    @Transactional
    public Note update(Note note, long id){
//        titleが空の場合のバリデーション
        if(note.getTitle() == null || note.getTitle().isEmpty()){
            throw new NoteArgumentNotValidException("title is empty");
        }

//        contentが空の場合のバリデーション
        if(note.getContent() == null || note.getContent().isEmpty()){
            throw new NoteArgumentNotValidException("content is empty");
        }

//        指定されたIDが存在しない場合のバリデーション
        Note targetNote = noteRepository.findById(id);
        if(targetNote == null){
            throw new NoteNotFoundException(id);
        }

        note.setId(id);
        note.setCreatedAt(targetNote.getCreatedAt());
        return noteRepository.update(note);
    }
}
