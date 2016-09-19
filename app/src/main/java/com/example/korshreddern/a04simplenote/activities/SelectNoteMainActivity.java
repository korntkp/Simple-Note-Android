package com.example.korshreddern.a04simplenote.activities;

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

import com.example.korshreddern.a04simplenote.database.DBHelper;
import com.example.korshreddern.a04simplenote.adapter.ListViewAdapter;
import com.example.korshreddern.a04simplenote.model.Note;
import com.example.korshreddern.a04simplenote.R;

import java.util.ArrayList;

public class SelectNoteMainActivity extends AppCompatActivity {

    ArrayList<Note> noteArrayList;
    ListViewAdapter adapter;
    ListView listView;
    DBHelper db;

    Toolbar toolbar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_note);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.listView);

        setSupportActionBar(toolbar);
        setupFabButton();
        db = new DBHelper(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshNote();
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(getApplicationContext(), EditNoteActivity.class);
                intent.putExtra("idNote", adapter.noteArrayList.get(arg2).id);
                intent.putExtra("msgNote", adapter.noteArrayList.get(arg2).message);
                startActivity(intent);
            }
        });
    }

    private void setupFabButton() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    private void refreshNote() {
        noteArrayList = DBHelper.searchNote(getApplicationContext());
        adapter = new ListViewAdapter(getApplicationContext(), noteArrayList);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.setting_delete_all) {
            deleteAllNote();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean deleteAllNote() {
        DBHelper.deleteNoteAll(getApplicationContext());
        refreshNote();
        return true;
    }
}
