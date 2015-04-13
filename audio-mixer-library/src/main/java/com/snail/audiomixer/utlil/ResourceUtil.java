package com.snail.audiomixer.utlil;

import android.content.Context;

import java.io.File;
import java.io.IOException;

/**
 * Created by Leonid on 13.04.2015.
 */
public class ResourceUtil {

    public static boolean isResourceExist(Context context, int resId) {
        try {
            return context.getResources().openRawResource(resId).available() > 0;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isResourceExist(String resPath) {
        return new File(resPath).exists() && new File(resPath).isFile();
    }

    public static boolean isPathExist(String path){
        return new File(path).exists();
    }
}
