package com.example.korshreddern.a04simplenote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectNoteActivity extends AppCompatActivity {

    private ArrayList<Note> noteArrayList;
    private ListView listView;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
            startActivity(intent);
            }
        });

        this.listView = (ListView) findViewById(R.id.listView);
        db = new DBHelper(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshNote();
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void refreshNote() {
        noteArrayList = Note.searchNote(getApplicationContext());
        ListViewAdapter adapter = new ListViewAdapter(getApplicationContext(), noteArrayList);
        this.listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.setting_delete_all) {
            Note note = new Note();
            note.deleteNoteAll(getApplicationContext());
            refreshNote();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
