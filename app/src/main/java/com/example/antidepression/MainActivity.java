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
    public static final String APP_PREFERENCES_THEME = "theme";
    public static final String IS_DARK_THEME = "isDarkTheme";
    private SharedPreferences settings;

    String[] activities = {"About depression", "Test", "Advices", "Thought catalog", "Music relaxion", "Pleasure therapy"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadSettings();
        loadTheme();

        setContentView(R.layout.activity_main);

        Switch switchTheme = findViewById(R.id.switchTheme);
        switchTheme.setChecked(settings.getBoolean(IS_DARK_THEME, false));

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
                    case "About depression":
                        intent = new Intent(getApplicationContext(), AboutDepressionActivity.class);
                        break;
                    case "Test":
                        intent = new Intent(getApplicationContext(), TestActivity.class);
                        break;
                    case "Advices":
                        intent = new Intent(getApplicationContext(), AdviceScreenSlidePagerActivity.class);
                        break;
                    case "Thought catalog":
                        intent = new Intent(getApplicationContext(), NotesActivity.class);
                        break;
                    case "Music relaxion":
                        intent = new Intent(getApplicationContext(), AudioActivity.class);
                        break;
                    case "Pleasure therapy":
                        intent = new Intent(getApplicationContext(), PleasureActivity.class);
                        break;
                    default:
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        break;
                }
                if(intent != null){
                    startActivity(intent);
                }
            }
        };
        getListView().setOnItemClickListener(itemListener);

        ImageView img= findViewById(R.id.imageView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.main_image);
        img.setImageURI(uri);
    }

    public void switchTheme(View view) {
        boolean isChecked = ((Switch)findViewById(R.id.switchTheme)).isChecked();

        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(IS_DARK_THEME, isChecked);
        editor.apply();
        editor.commit();

        reloadTheme();
    }

    private void loadSettings() {
        settings = this.getSharedPreferences(APP_PREFERENCES_THEME, Context.MODE_PRIVATE);
    }

    private void loadTheme() {
        int theme = getThemeFromPreferences();
        setTheme(theme);
    }

    private void reloadTheme() {
        int theme = getThemeFromPreferences();
        setTheme(theme);
        MainActivity.this.recreate();
    }

    private int getThemeFromPreferences() {
        Boolean darkTheme = settings.getBoolean(IS_DARK_THEME, false);
        return darkTheme ? R.style.DarkTheme : R.style.LightTheme;
    }
}
