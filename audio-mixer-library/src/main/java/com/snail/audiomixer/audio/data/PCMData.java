package com.snail.audiomixer.audio.data;

import com.snail.audiomixer.model.Config;

/**
 * Created by leonid on 4/10/15.
 */
public class PCMData {
    private byte[] data;
    private Config config;

    public PCMData(byte[] data, Config config) {
        this.data = data;
        this.config = config;
    }

    public PCMData(int duration, Config config) {
        this.config = config;
        this.data = new byte[offsetForTime(duration)];
    }

    /**
     * Mix another PCM data into current.
     *
     * @param pcmData PCM data to mix into current
     * @param startAt time to start mixing (milliseconds)
     */
    public void mix(PCMData pcmData, int startAt) {
        int delta1 = this.config.getSizeInBytes();
        int delta2 = pcmData.config.getSizeInBytes();
        int length1 = this.data.length;
        int length2 = pcmData.data.length;
        for (int offset1 = offsetForTime(startAt), offset2 = 0;
             offset1 < length1 - 1 && offset2 < length2 - 1;
             offset1 += delta1, offset2 += delta2) {

            short shortA = (short) ((this.data[offset1] & 0xFF) | ((this.data[offset1 + 1] & 0xFF) << 8));
            short shortB = (short) ((pcmData.data[offset2] & 0xFF) | ((pcmData.data[offset2 + 1] & 0xFF) << 8));

            float A = (((float) shortA / Short.MAX_VALUE) * 0.5f) + 0.5f;
            float B = (((float) shortB / Short.MAX_VALUE) * 0.5f) + 0.5f;

            float Z = (A < 0.5f && B < 0.5f) ? (2 * A * B) : (2 * (A + B) - 2 * A * B - 1);

            float Z2 = (Z - 0.5f) * 2;
            short shortZ = (short) (Z2 * Short.MAX_VALUE);
            this.data[offset1] = (byte) (shortZ & 0xff);
            this.data[offset1 + 1] = (byte) ((shortZ >> 8) & 0xff);
        }
    }


    public int offsetForTime(long time) {
        return (int) (this.config.getSizeInBytes() * time * this.config.getSampleRate().getValue()) / 1000;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getDuration() {
        return (this.data.length / this.config.getSizeInBytes() * 1000) / this.config.getSampleRate().getValue();
    }
}
