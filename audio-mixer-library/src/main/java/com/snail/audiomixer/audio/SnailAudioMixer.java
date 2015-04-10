package com.snail.audiomixer.audio;

import android.content.Context;

import com.snail.audiomixer.audio.data.PCMData;
import com.snail.audiomixer.io.WAWReader;
import com.snail.audiomixer.model.AudioEntry;
import com.snail.audiomixer.model.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by leonid on 4/10/15.
 */
public class SnailAudioMixer {
    private List<AudioEntry> audioEntries = new ArrayList<AudioEntry>();

    public Config getConfig() {
        if (config == null) {
            return new Config();
        }
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    private Config config;

    public void setAudio(AudioEntry audioEntry) {
        if (audioEntry != null) {
            audioEntries.add(audioEntry);
        }
    }

    public void setAudio(List<AudioEntry> audioEntries) {
        audioEntries.addAll(audioEntries);
    }


    public PCMData mixAudio(Context context, List<AudioEntry> audioEntries) {
        final HashMap<AudioEntry, PCMData> audioEntryPCMDataHashMap = new HashMap<AudioEntry, PCMData>();
        // calculate duration of final sound
        int totalDuration = 0;

        // load all required samples
        for (AudioEntry audioEntry : audioEntries) {
            PCMData pcmData = WAWReader.read(context, getConfig(), audioEntry.getAudio());
            audioEntryPCMDataHashMap.put(audioEntry, pcmData);
            int endTime = audioEntry.getStartTime() + pcmData.getDuration();
            if (endTime > totalDuration)
                totalDuration = endTime;
        }

        // create buffer for resulting audio
        PCMData result = new PCMData(totalDuration, getConfig());
        for (AudioEntry audioEntry : audioEntries) {
            PCMData current = audioEntryPCMDataHashMap.get(audioEntry);
            result.mix(current, audioEntry.getStartTime());
        }
        return result;
    }
}
