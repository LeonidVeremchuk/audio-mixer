package com.snail.audiomixer.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.snail.audiomixer.SnailAudioMixer;
import com.snail.audiomixer.data.PCMData;
import com.snail.audiomixer.io.WAVWriter;
import com.snail.audiomixer.model.AudioEntry;
import com.snail.audiomixer.model.Config;

/**
 * Created by Leonid Veremchuk.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private Button btnPlay;
    private Spinner spPicker;

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        spPicker = (Spinner) this.findViewById(R.id.spPicker);
        btnPlay = (Button) this.findViewById(R.id.btnPlay);
        bindViews();
    }

    private void bindViews() {
        btnPlay.setOnClickListener(this);
        spPicker.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, new String[]{"Drumps", "Piano", "Voice"}));
        spPicker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public void addAudioSample() {

        SnailAudioMixer audioMixer = new SnailAudioMixer();
        Config config = new Config();
        config.setEncoding(Config.Encoding.SIGNED_24);
        config.setSampleRate(Config.SampleRate.RATE_44100);

        AudioEntry audioEntry = new AudioEntry(0, "path");
        AudioEntry audioEntry2 = new AudioEntry(300, "path");
        AudioEntry audioEntry3 = new AudioEntry(500, "path");

        audioMixer.setAudio(audioEntry);
        audioMixer.setAudio(audioEntry2);
        audioMixer.setAudio(audioEntry3);
        audioMixer.setConfig(config);

        PCMData pcmData = audioMixer.mixAudio(this);
        WAVWriter.writeToPath(pcmData, "path");
    }

    @Override
    public void onClick(View v) {

    }
}
