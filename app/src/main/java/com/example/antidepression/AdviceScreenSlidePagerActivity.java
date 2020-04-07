package com.example.antidepression;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

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
            "Заведите личный дневник (описывайте в нем события, сопутствующие мысли и чувства",
            "Ежедневно составляйте план на день с конкретными и реалистичными целями",
            "Включите в распорядок дня мероприятия, приносящие положительные эмоции (встречи с друзьями, хобби и т.п.)",
            "Уделите время физическим нагрузкам",
            "Найдите момент для творчества, созидания",
            "Фокусируйтесь на позитивных моментах дня (в абсолютно любой ситуации есть свои плюсы, находите их)",
            "Каждый вечер пишите 10 благодарностей за прошедший день (людям, Богу, событиям)",
            "Помните, что для достижения результата нужно время, будте терпеливы",
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
        // if (mPager.getCurrentItem() == 0) {
        //     // If the user is currently looking at the first step, allow the system to handle the
        //     // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        // } else {
        //     // Otherwise, select the previous step.
        //     mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        // }
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

    private void loadSettings() {
        settings = this.getSharedPreferences(APP_PREFERENCES_THEME, Context.MODE_PRIVATE);
    }

    private void loadTheme() {
        int theme = getThemeFromPreferences();
        setTheme(theme);
    }

    private int getThemeFromPreferences() {
        Boolean darkTheme = settings.getBoolean(IS_DARK_THEME, false);
        return darkTheme ? R.style.DarkTheme : R.style.LightTheme;
    }
}
