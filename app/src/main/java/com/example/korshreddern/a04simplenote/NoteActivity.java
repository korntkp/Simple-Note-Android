package com.example.korshreddern.a04simplenote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {

    public EditText editText;
    public String noteStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        editText = (EditText) findViewById(R.id.note_edittext);
    }

    public void saveNote(View view) {
        noteStr = editText.getText().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String mDate = (dateFormat.format(date));

        Note note = new Note();
        note.addNote(getApplicationContext(), mDate, noteStr);
        finish();
    }
}
