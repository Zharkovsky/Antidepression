package com.example.antidepression;

import android.app.Dialog;
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
    public int finalSum = 0;

    private Question[] _questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadTest();

        setContentView(R.layout.activity_test);

        LinearLayout basicLayout = (LinearLayout) findViewById(R.id.basicLayout);
        for (Question question : _questions) {
            LinearLayout questionLayout = CreateQuestion(question);
            basicLayout.addView(questionLayout);
        }
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
                new Question("Как вы чувствовали себя на этой неделе и сегодня?",
                        "Я не чувствую себя расстроенным, печальным.",
                        "Я расстроен.",
                        "Я все время расстроен и не могу от этого отключиться.",
                        "Я настолько расстроен и несчастлив, что не могу это выдержать."),
                new Question("Как вы себя чувствовали на этой неделе и сегодня?",
                        "У меня не потерян интерес к другим людям",
                        "Я меньше, чем бывало, интересуюсь другими людьми",
                        "У меня потерян почти весь интерес к другим людям, и почти нет никаких чувств к ним.",
                        "У меня потерян всякий интерес к другим людям, и они меня совершенно не заботят"),
                new Question("Как вы себя чувствовали на этой неделе и сегодня?",
                        "Я смотрю в будущее без особого разочарования",
                        "Я испытываю разочарование в будущем",
                        "Я чувствую, что мне нечего ждать впереди",
                        "Я чувствую, что будущее безнадёжно и поворота к лучшему быть не может\""),
                new Question("Как вы себя чувствовали на этой неделе и сегодня?",
                        "Я принимаю решения примерно также легко, как всегда.",
                        "Я пытаюсь отсрочить принятие решений.",
                        "Принятие решений представляет для меня огромную трудность.",
                        "Я больше совсем не могу принимать решения."),
                new Question("Как вы себя чувствовали на этой неделе и сегодня?",
                        "Я не чувствую себя неудачником.",
                        "Я чувствую, что неудачи случались у меня чаще, чем у других людей.",
                        "Когда оглядываюсь на свою жизнь, я вижу лишь цепь неудач.",
                        "Я чувствую, что потерпел неудачу как личность (родитель, муж, жена)."),
                new Question("Как вы себя чувствовали на этой неделе и сегодня?",
                        "Я не чувствую, что выгляжу сколько-нибудь хуже, чем обычно.",
                        "Меня беспокоит то, что выгляжу старо и непривлекательно.",
                        "Я чувствую, что в моём внешнем виде происходят постоянные изменения, делающие меня непривлекательными.",
                        "Я чувствую, что выгляжу гадко или отталкивающе."),
                new Question("Как вы себя чувствовали на этой неделе и сегодня?",
                        "Я не испытываю никакой особенной неудовлетворённости.",
                        "Ничто не радует меня так, как раньше.",
                        "Ничто больше не даёт мне удовлетворения.",
                        "Меня не удовлетворяет всё."),
                new Question("Как вы себя чувствовали на этой неделе и сегодня?",
                        "Я могу работать примерно также хорошо, как и раньше.",
                        "Мне нужно делать дополнительные усилия, чтобы что-то сделать.",
                        "Я не могу выполнять никакую работу.",
                        "Я не могу выполнять никакую работу."),
                new Question("Как вы себя чувствовали на этой неделе и сегодня?",
                        "Я не чувствую никакой особенной вины.",
                        "Большую часть времени я чувствую себя скверным и ничтожным.",
                        "У меня довольно сильное чувство вины.",
                        "Я чувствую себя очень скверным и никчемным."),
                new Question("Как вы себя чувствовали на этой неделе и сегодня?",
                        "Я устаю ничуть не больше, чем обычно.",
                        "Я устаю быстрее, чем раньше.",
                        "Я устаю от любого занятия.",
                        "Я устал чем бы то ни было заниматься."),
                new Question("Как вы себя чувствовали на этой неделе и сегодня?",
                        "Я не испытываю разочарования в себе.",
                        "Я разочарован в себе.",
                        "У меня отвращение к себе.",
                        "Я ненавижу себя."),
                new Question("Как вы себя чувствовали на этой неделе и сегодня?",
                        "Мой аппетит не хуже, чем обычно.",
                        "Мой аппетит не так хорош, как бывало",
                        "Мой аппетит теперь гораздо хуже.",
                        "У меня совсем нет аппетита."),
                new Question("Как вы себя чувствовали на этой неделе и сегодня?",
                        "У меня нет никаких мыслей о самоповреждении.",
                        "Я чувствую, что мне было бы лучше умереть.",
                        "У меня есть определённые планы совершения самоубийства.",
                        "Я покончу с собой при первой возможности."),
        };
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
