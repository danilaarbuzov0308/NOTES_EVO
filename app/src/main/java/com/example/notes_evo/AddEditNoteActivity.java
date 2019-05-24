package com.example.notes_evo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_DESCRIPTION =
            "com.example.notes_evo.EXTRA_DESCRIPTION";
    public static final String EXTRA_ID =
            "com.example.notes_evo.EXTRA_ID";
   public static final String EXTRA_DATE =
           "com.example.notes_evo.EXTRA_DATE";
   public static final String EXTRA_TIME =
           "com.example.notes_evo.EXTRA_TIME";


    private EditText noteContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);
        noteContent = findViewById(R.id.note_content);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            noteContent.setText(intent.getStringExtra(EXTRA_DESCRIPTION));

        } else {
            setTitle("Add Note");
        }
    }

    @Override
    public void onBackPressed() {
        saveNote();
    }


    private void saveNote() {
        String content = noteContent.getText().toString();
        Calendar dateCalendar = Calendar.getInstance();
        String date = DateFormat.getDateInstance().format(dateCalendar.getTime());
        Calendar timeCalendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time = format.format(timeCalendar.getTime());

        if (content.trim().isEmpty()) {
            Toast.makeText(this, "Нельзя создать пустую заметку", Toast.LENGTH_LONG).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_DESCRIPTION, content);
        data.putExtra(EXTRA_DATE, date);
        data.putExtra(EXTRA_TIME, time);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    public void shareNote(){
        String content = noteContent.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return  true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                saveNote();
                return true;
            case R.id.shareNote:
                shareNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


