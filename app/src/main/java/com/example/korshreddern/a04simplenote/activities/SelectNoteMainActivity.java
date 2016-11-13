package com.example.korshreddern.a04simplenote.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.korshreddern.a04simplenote.R;
import com.example.korshreddern.a04simplenote.adapter.ListViewAdapter;
import com.example.korshreddern.a04simplenote.database.DBHelper;
import com.example.korshreddern.a04simplenote.model.Note;

import java.util.ArrayList;


public class SelectNoteMainActivity extends AppCompatActivity {

    private static final String TAG = "SelNoteAct";
    ArrayList<Note> noteArrayList;
    ListViewAdapter adapter;
    ListView listView;
    DBHelper db;

    Toolbar toolbar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_select_note);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.listView);

        setSupportActionBar(toolbar);
        setupFabButton();
        db = new DBHelper(getApplicationContext());
        refreshNote();

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setOnItemClickListener(onItemClickListener);
        listView.setMultiChoiceModeListener(multiChoiceModeListenerCallback);
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

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Intent intent = new Intent(getApplicationContext(), EditNoteActivity.class);
            intent.putExtra("idNote", adapter.noteArrayList.get(arg2).id);
            intent.putExtra("msgNote", adapter.noteArrayList.get(arg2).message);
            startActivity(intent);
        }
    };

    AbsListView.MultiChoiceModeListener multiChoiceModeListenerCallback = new AbsListView.MultiChoiceModeListener() {
        ArrayList<Integer> checkedIndex = new ArrayList<>();

        // TODO: 13-Nov-16 BUG Position and listview
        @Override
        public void onItemCheckedStateChanged(ActionMode actionMode, int position,
                                              long id, boolean checked) {

            if (checked) {
                checkedIndex.add(position);
                listView.getChildAt(position).setBackgroundColor(Color.CYAN);
//                listView.getAdapter().getView(position, listView.getRootView(), listView.get).setBackgroundColor(Color.CYAN);
            } else {
                checkedIndex.remove(checkedIndex.indexOf(position));
                listView.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
            }

            final int checkedCount = listView.getCheckedItemCount();
            switch (checkedCount) {
                case 0:
                    break;
                case 1:
                    actionMode.setTitle("1");
                    actionMode.setSubtitle("1 item selected");
                    break;
                default:
                    actionMode.setTitle(checkedCount + "");
                    actionMode.setSubtitle(checkedCount + " items selected");
                    break;
            }
        }

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.list_select_menu, menu);
            fab.hide();
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.share:
                    Toast.makeText(SelectNoteMainActivity.this, "Shared " + listView.getCheckedItemCount() +
                            " items", Toast.LENGTH_SHORT).show();
                    actionMode.finish();
                    break;
                default:
                    Toast.makeText(SelectNoteMainActivity.this, "Clicked " + menuItem.getTitle(),
                            Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            for (int i = 0; i < checkedIndex.size(); i++) {
                listView.getChildAt(checkedIndex.get(i)).setBackgroundColor(Color.TRANSPARENT);
            }
            checkedIndex.clear();
            fab.show();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        refreshNote();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }
}
