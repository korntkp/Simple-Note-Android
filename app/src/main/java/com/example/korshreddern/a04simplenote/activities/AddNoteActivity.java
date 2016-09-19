package com.example.korshreddern.a04simplenote.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.korshreddern.a04simplenote.database.DBHelper;
import com.example.korshreddern.a04simplenote.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    public EditText editText;
    public Button button;
    public String noteStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        editText = (EditText) findViewById(R.id.note_edittext);
        button = (Button) findViewById(R.id.note_save_note);

        setupButtonSaveNote();
    }

    public void setupButtonSaveNote() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteStr = editText.getText().toString();

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String mDate = (dateFormat.format(date));

                DBHelper.addNote(getApplicationContext(), mDate, noteStr);
                finish();
            }
        });
    }
}
