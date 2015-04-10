package com.snail.audiomixer.io;

import android.content.Context;

import com.google.common.io.ByteStreams;
import com.snail.audiomixer.audio.data.PCMData;
import com.snail.audiomixer.model.Config;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by leonid on 4/10/15.
 */
public class WAWReader {

    public static PCMData read(Context context, Config config, int resourceId) {
        DataInputStream stream = prepareInputStream(context, resourceId);
        return read(stream, config);
    }

    public static PCMData read(Context context, Config config, String path) {
        //todo
//        return read(stream, config);
        return null;
    }

    private static PCMData read(DataInputStream stream, Config config) {
        try {
            stream.skip(44);
            byte[] bytes = null;
            bytes = ByteStreams.toByteArray(stream);
            stream.close();
            return new PCMData(bytes, config);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static DataInputStream prepareInputStream(Context context, int resourceId) {
        if (resourceId == -1) {
            return new DataInputStream(new InputStream() {
                @Override
                public int read() throws IOException {
                    return 0;
                }
            });
        }
        return new DataInputStream(context.getResources().openRawResource(resourceId));
    }
}
