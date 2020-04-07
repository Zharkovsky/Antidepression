package com.example.antidepression;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES_THEME = "theme";
    public static final String IS_DARK_THEME = "isDarkTheme";
    private SharedPreferences settings;

    public int finalSum = 0;

    private Question[] _questions;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadSettings();
        loadTheme();
        loadTest();

        setContentView(R.layout.activity_test);

        LinearLayout basicLayout = (LinearLayout) findViewById(R.id.basicLayout);
        for (Question question : _questions) {
            LinearLayout questionLayout = CreateQuestion(question);
            basicLayout.addView(questionLayout);
        }

        Button button = createCompleteButton();
        basicLayout.addView(button);

    }

    private Button createCompleteButton() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;

        Button button = new Button(this);

        button.setLayoutParams(params);
        button.setGravity(Gravity.CENTER);
        button.setText("Done");
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String title;
                String text;

                if (finalSum < 13) {
                    title = "Answer all questions";
                    text = "Click OK to continue.";
                } else {
                    title = "Test result";
                    if (finalSum < 26) {
                        text = "You are in good condition";
                    } else if (finalSum < 32) {
                        text = "You have mild depression";
                    }  else if (finalSum < 41) {
                        text = "You have moderate depression";
                    } else if (finalSum > 41) {
                        text = "You have severe depression. Contact a specialist.";
                    }
                    else {
                        text = "";
                    }
                }
                TestResultDialogFragment dialog = new TestResultDialogFragment(title, text);
                dialog.show(getSupportFragmentManager(), "custom");
            }
        });

        return button;
    }

    private LinearLayout CreateQuestion(Question question) {
        LinearLayout parent = new LinearLayout(this);
        parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        parent.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams parentParams = (LinearLayout.LayoutParams) parent.getLayoutParams();
        parentParams.setMargins(30, 30, 30, 30);

        TextView questionText = new TextView(this);
        questionText.setText(question.Question);

        LinearLayout answers = new LinearLayout(this);
        answers.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        answers.setOrientation(LinearLayout.VERTICAL);

        final CheckBox checkBox1 = new CheckBox(this);
        checkBox1.setText(question.Answer1);
        answers.addView(checkBox1);

        final CheckBox checkBox2 = new CheckBox(this);
        checkBox2.setText(question.Answer2);
        answers.addView(checkBox2);

        final CheckBox checkBox3 = new CheckBox(this);
        checkBox3.setText(question.Answer3);
        answers.addView(checkBox3);

        final CheckBox checkBox4 = new CheckBox(this);
        checkBox4.setText(question.Answer4);
        answers.addView(checkBox4);


        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);

                    finalSum += 1;
                } else {
                    finalSum -= 1;
                }
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox1.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);

                    finalSum += 2;
                } else {
                    finalSum -= 2;
                }
            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox4.setChecked(false);

                    finalSum += 3;
                } else {
                    finalSum -= 3;
                }
            }
        });

        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);

                    finalSum += 4;
                } else {
                    finalSum -= 4;
                }
            }
        });

        parent.addView(questionText);
        parent.addView(answers);

        return parent;
    }

    private void loadTest() {
        this._questions = new Question[]{
                new Question ("How did you feel this week and today?",
                        "I don’t feel upset, sad.",
                        "I'm sad.",
                        "I’m upset all the time and I can’t disconnect from this.",
                        "I'm so upset and unhappy that I can't stand it."),
                new Question ("How did you feel this week and today?",
                        "I have not lost interest in other people",
                        "I'm less interested in other people than I used to be",
                        "I have lost almost all my interest in other people and have almost no feelings for them.",
                        "I have lost all interest in other people, and they do not bother me at all"),
                new Question ("How did you feel this week and today?",
                        "I look to the future without much frustration",
                        "I am disappointed in the future",
                        "I feel like I have nothing to wait in front of",
                        "I feel that the future is hopeless and there can be no turning for the better \" "),
                new Question ("How did you feel this week and today?",
                        "I make decisions about as easily as ever.",
                        "I'm trying to delay decision making.",
                        "Making decisions is a huge challenge for me.",
                        "I can’t make decisions anymore."),
                new Question ("How did you feel this week and today?",
                        "I don't feel like a failure.",
                        "I feel that failures happened to me more often than other people.",
                        "When I look back at my life, I see only a chain of failures.",
                        "I feel that I have failed as a person (parent, husband, wife)."),
                new Question ("How did you feel this week and today?",
                        "I don’t feel like I look any worse than usual.",
                        "It bothers me that I look old and unattractive.",
                        "I feel that there are constant changes in my appearance that make me unattractive.",
                        "I feel like I look nasty or repulsive."),
                new Question ("How did you feel this week and today?",
                        "I do not feel any particular dissatisfaction.",
                        "Nothing pleases me like before.",
                        "Nothing gives me satisfaction anymore.",
                        "I am not satisfied with everything."),
                new Question ("How did you feel this week and today?",
                        "I can work just about as well as before.",
                        "I need to make extra efforts to do something.",
                        "I can do no work.",
                        "I can not do any work."),
                new Question ("How did you feel this week and today?",
                        "I feel no particular guilt.",
                        "Most of the time I feel nasty and worthless.",
                        "I have a pretty strong sense of guilt.",
                        "I feel very nasty and worthless."),
                new Question ("How did you feel this week and today?",
                        "I get tired no more than usual.",
                        "I get tired faster than before.",
                        "I get tired of any activity.",
                        "I'm tired of doing whatever."),
                new Question ("How did you feel this week and today?",
                        "I am not disappointed in myself.",
                        "I'm disappointed in myself.",
                        "I have an aversion to myself.",
                        "I hate myself."),
                new Question ("How did you feel this week and today?",
                        "My appetite is no worse than usual.",
                        "My appetite is not as good as it used to be",
                        "My appetite is now much worse.",
                        "I have no appetite at all."),
                new Question ("How did you feel this week and today?",
                        "I have no thoughts about self-harm.",
                        "I feel that I would be better off dying.",
                        "I have certain plans for committing suicide.",
                        "I will kill myself as soon as possible."),
        };
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

    private class Question {
        public String Question;
        public String Answer1;
        public String Answer2;
        public String Answer3;
        public String Answer4;

        public Question(String question, String answer1, String answer2, String answer3, String answer4) {
            this.Question = question;
            this.Answer1 = answer1;
            this.Answer2 = answer2;
            this.Answer3 = answer3;
            this.Answer4 = answer4;
        }
    }
}
