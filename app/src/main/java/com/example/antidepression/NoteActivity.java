package com.example.antidepression;

import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.antidepression.db.DatabaseAdapter;
import com.example.antidepression.helpers.InputFilterMinMax;

public class NoteActivity extends AppCompatActivity {

    private EditText textBox;
    private EditText stateBox;

    private DatabaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note);

        textBox = (EditText) findViewById(R.id.text);
        stateBox = (EditText) findViewById(R.id.state);
        adapter = new DatabaseAdapter(this);

        stateBox.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "5")});
    }
}
