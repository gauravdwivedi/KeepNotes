package com.focustowardsfuture.gaurav.keepnotes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Gaurav Dwivedi on 14-05-2019.
 */
public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;


    public NoteRepository(Application application){
        NoteDatabase database =NoteDatabase.getInstance(application);
        noteDao =database.noteDao();

        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);

    }
    public void update(Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);

    }
    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);

    }
    public void deleteAllNotes(){
        new DeleteAllNoteAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>{
    private NoteDao noteDao; //bcz we need noteDao class to make database operations


    //since the class static we cannot access notedao of our repository directory so we pass through constructor
        private InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.insert(notes[0]);
            return null;
        }
    }



    private static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao; //bcz we need noteDao class to make database operations


        //since the class static we cannot access notedao of our repository directory so we pass through constructor
        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao; //bcz we need noteDao class to make database operations


        //since the class static we cannot access notedao of our repository directory so we pass through constructor
        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao; //bcz we need noteDao class to make database operations


        //since the class static we cannot access notedao of our repository directory so we pass through constructor
        private DeleteAllNoteAsyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDao.deleteAllNotes();
            return null;
        }
    }

}
