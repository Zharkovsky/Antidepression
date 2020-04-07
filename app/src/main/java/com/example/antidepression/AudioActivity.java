package com.example.antidepression;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

class AudioActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES_THEME = "theme";
    public static final String IS_DARK_THEME = "isDarkTheme";
    private SharedPreferences settings;

    Integer[] source = new Integer[] {R.raw.sound1, R.raw.sound2, R.raw.sound3};
    MediaPlayer mPlayer;
    Button startButton, pauseButton, stopButton;
    SeekBar volumeControl;
    AudioManager audioManager;
    Random random = new Random();
    int currentAudioId = 0;
    ArrayList<Integer> audioHistory = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadSettings();
        loadTheme();

        setContentView(R.layout.activity_audio);

        next(null);
        startButton = (Button) findViewById(R.id.start);
        pauseButton = (Button) findViewById(R.id.pause);
        stopButton = (Button) findViewById(R.id.stop);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curValue = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        volumeControl = (SeekBar) findViewById(R.id.volumeControl);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curValue);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
    }

    private void initPlayer(int soundId) {
        mPlayer=MediaPlayer.create(this, source[soundId]);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                next(null);
            }
        });
    }

    private void stopPlay(){
        mPlayer.stop();
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
            startButton.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void play(View view){

        mPlayer.start();
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
    }
    public void pause(View view){

        mPlayer.pause();
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(true);
    }
    public void stop(View view){
        stopPlay();
    }
    public void next(View view){
        Boolean needToPlay = false;
        if (mPlayer != null) {
            stopPlay();
            needToPlay = true;
        }

        if (currentAudioId + 1> audioHistory.size() ) {
            audioHistory.add(random.nextInt(source.length));
        }
        currentAudioId++;
        initPlayer(audioHistory.get(currentAudioId - 1));
        if (needToPlay) {
            play(null);
        }
    }
    public void previous(View view){
        Boolean needToPlay = false;
        if (mPlayer != null) {
            stopPlay();
            needToPlay = true;
        }

        if (currentAudioId > 0) {
            currentAudioId--;
        }
        initPlayer(audioHistory.get(currentAudioId));
        if (needToPlay) {
            play(null);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()) {
            stopPlay();
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
