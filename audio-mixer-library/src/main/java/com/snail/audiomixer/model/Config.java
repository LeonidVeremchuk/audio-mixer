package com.snail.audiomixer.model;

import com.snail.audiomixer.audio.SnailAudioMixer;

/**
 * Created by leonid on 4/10/15.
 */
public class Config {
    private Encoding encoding;
    private SampleRate sampleRate;


    public Config() {
        this.encoding = Encoding.SIGNED_24;
        this.sampleRate = SampleRate.RATE_44100;
    }

    public Config(Encoding encoding, SampleRate sampleRate) {
        this.encoding = encoding;
        this.sampleRate = sampleRate;
    }

    public Encoding getEncoding() {
        return encoding;
    }

    public void setEncoding(Encoding encoding) {
        this.encoding = encoding;
    }

    public SampleRate getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(SampleRate sampleRate) {
        this.sampleRate = sampleRate;
    }

    public int getSizeInBytes() {
        return 2;
    }


    public enum Encoding {
        SIGNED_24, SIGNED_16, SIGNED_8
    }

    public enum SampleRate {
        RATE_44100(44100);
        private int rate;

        SampleRate(int rate) {
            this.rate = rate;
        }

        public int getValue() {
            return rate;
        }
    }
}
