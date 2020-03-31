package com.example.antidepression;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.antidepression.db.DatabasePleasureAdapter;
import com.example.antidepression.db.Pleasure;

import java.util.List;

public class PleasureActivity extends AppCompatActivity {
    private ListView pleasureList;
    private Pleasure selectedPleasure;
    private View selectedView;
    ArrayAdapter<Pleasure> arrayAdapter;
    List<Pleasure> currentItems;

    private Button deleteButton;
    private Button laterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pleasure);

        pleasureList = (ListView)findViewById(R.id.list);
        deleteButton = (Button)findViewById(R.id.delete);
        laterButton = (Button)findViewById(R.id.later);

        pleasureList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPleasure = arrayAdapter.getItem(position);

                if(selectedPleasure!=null) {
                    showButtons();
                    if (selectedView != null) {
                        selectedView.setBackgroundColor(Color.TRANSPARENT);
                    }
                    view.setBackgroundColor(Color.parseColor("#d0d1d1"));

//                    Intent intent = new Intent(getApplicationContext(), PleasureActivity.class);
//                    intent.putExtra("id", pleasure.getId());
//                    intent.putExtra("click", 25);
//                    startActivity(intent);
                } else {
                    hideButtons();
                    if (selectedView != null) {
                        selectedView.setBackgroundColor(Color.TRANSPARENT);
                    }
                }

                selectedView = view;
            }
        });

        hideButtons();
    }

    private void showButtons() {
        laterButton.setVisibility(View.VISIBLE);
        deleteButton.setVisibility(View.VISIBLE);
    }

    private void hideButtons() {
        laterButton.setVisibility(View.INVISIBLE);
        deleteButton.setVisibility(View.INVISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void refreshList() {
        DatabasePleasureAdapter adapter = new DatabasePleasureAdapter(this);
        adapter.open();

        List<Pleasure> list = adapter.getAllPleasure();
        currentItems = list.subList(0, Integer.min(4, list.size()));
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currentItems);
        pleasureList.setAdapter(arrayAdapter);
        adapter.close();
    }
}
