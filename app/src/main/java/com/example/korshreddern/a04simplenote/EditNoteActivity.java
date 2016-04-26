package com.example.korshreddern.a04simplenote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditNoteActivity extends AppCompatActivity {

    EditText editText;
    Button editSaveButton;
    String noteStr;
    int idNote;
    String msgNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        Bundle bundle = getIntent().getExtras();
        idNote = bundle.getInt("idNote");
        msgNote = bundle.getString("msgNote");

        editText = (EditText) findViewById(R.id.edit_note_edittext);
        editText.setText(msgNote);
        editSaveButton = (Button) findViewById(R.id.edit_note_button);
    }

    public void saveEditNote(View view) {
        noteStr = editText.getText().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String mDate = (dateFormat.format(date));

        Note note = new Note();
        note.editNote(getApplicationContext(), idNote , mDate, noteStr);
        finish();
    }
}
