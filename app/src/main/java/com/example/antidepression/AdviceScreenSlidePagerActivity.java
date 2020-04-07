package com.example.antidepression;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class AdviceScreenSlidePagerActivity extends FragmentActivity {
    public static final String APP_PREFERENCES_THEME = "theme";
    public static final String IS_DARK_THEME = "isDarkTheme";
    private SharedPreferences settings;

    String[] advices = new String[] {
            "Keep a personal diary (write down events, related thoughts and feelings in it)",
            "Make a daily plan for the day with specific and realistic goals.",
            "Include positive emotions in your daily routine (meeting friends, hobbies, etc.)",
            "Take time to exercise",
            "Find a moment for creativity, creation",
            "Focus on the positive aspects of the day (in absolutely any situation there are advantages, find them)",
            "Every evening write 10 thanks for the past day (people, God, events)",
            "Remember that it takes time to achieve the result, be patient",
    };

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 8;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadSettings();
        loadTheme();

        setContentView(R.layout.advice_activity_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new AdviceScreenSlidePageFragment(advices[position], position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
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
        AdviceScreenSlidePagerActivity.this.recreate();
    }

    private int getThemeFromPreferences() {
        Boolean darkTheme = settings.getBoolean(IS_DARK_THEME, false);
        return darkTheme ? R.style.DarkTheme : R.style.LightTheme;
    }
}