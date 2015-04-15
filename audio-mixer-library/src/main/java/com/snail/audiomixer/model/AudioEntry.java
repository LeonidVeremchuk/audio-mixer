package com.snail.audiomixer.model;

import android.content.Context;

import com.snail.audiomixer.utlil.ResourceUtil;

import java.io.FileNotFoundException;

/**
 * Created by leonid on 4/10/15.
 */
public class AudioEntry {
    private int audioRes = -1;
    private String audioPath = "";
    private int startTime;

    public AudioEntry(int startTime, int audioResource) {
        init(startTime, audioResource);
    }

    public AudioEntry(int startTime, String audioPath) {
        init(startTime, audioPath);
    }


    private void init(int startTime, int audioResource) {
        this.startTime = startTime;
        this.audioRes = audioResource;
    }

    private void init(int startTime, String audioPath) {
        this.startTime = startTime;
        this.audioPath = audioPath;
    }

    public int getAudioResource() {
        return audioRes;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public int getStartTime() {
        return startTime;
    }

    public void checkAudioFile(Context context) throws FileNotFoundException {
        if (audioRes != -1) {
            if (!ResourceUtil.isResourceExist(context, audioRes)) {
                throw new FileNotFoundException("File with  id = " + audioRes + " is not exist");
            }
        } else {
            if (!ResourceUtil.isResourceExist(audioPath)) {
                throw new FileNotFoundException("File with pathName = " + audioPath + " is not exist");
            }
        }
    }
}
