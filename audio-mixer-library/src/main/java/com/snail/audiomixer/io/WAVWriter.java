package com.snail.audiomixer.io;

import com.snail.audiomixer.data.PCMData;
import com.snail.audiomixer.utlil.ResourceUtil;

import java.io.File;

/**
 * Created by Leonid on 13.04.2015.
 */
public class WAVWriter {

    public static void writeToByteBuffer(PCMData data) {
        //todo
    }

    public static void writeToPath(PCMData data, String path) {
        if (!ResourceUtil.isPathExist(path)) {
            new File(path).mkdirs();
        }
        addHeader(data);
    }

    private static void addHeader(PCMData data) {
        //todo
    }
}
