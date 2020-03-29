package com.example.antidepression;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends ListActivity {
    String[] activities = {"About depression", "Test", "Advices", "Thought catalog", "Music relaxion", "Pleasure therapy"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, activities);
        setListAdapter(adapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Intent intent;
                switch(selectedItem){
//                    case "About depression":
//                        intent = new Intent(getApplicationContext(), AboutDepressionActivity.class);
//                        break;
//                    case "Test":
//                        intent = new Intent(getApplicationContext(), TestActivity.class);
//                        break;
//                    case "Advices":
//                        intent = new Intent(getApplicationContext(), AdviceScreenSlidePagerActivity.class);
//                        break;
//                    case "Thought catalog":
//                        intent = new Intent(getApplicationContext(), NotesActivity.class);
//                        break;
//                    case "Music relaxion":
//                        intent = new Intent(getApplicationContext(), AudioActivity.class);
//                        break;
//                    case "Pleasure therapy":
//                        intent = new Intent(getApplicationContext(), PleasureActivity.class);
//                        break;
                    default:
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        break;
                }
                if(intent != null){
                    startActivity(intent);
                }
                Toast.makeText(getApplicationContext(), "Был выбран пункт " +
                        selectedItem, Toast.LENGTH_SHORT).show();
            }
        };
        getListView().setOnItemClickListener(itemListener);

        ImageView img= findViewById(R.id.imageView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.main_image);
        img.setImageURI(uri);
    }
}
