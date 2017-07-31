package cn.yx.constant;

import java.io.File;

/**
 * @author yuxuanjiao
 * @date 2017年7月30日 下午9:01:14 
 * @version 1.0
 */

public class FileConstant {

    public static final String UPLOAD_FOLDER_NAME = "files/";
    public static final File UPLOAD_FOLDER = new File(UPLOAD_FOLDER_NAME);

    static {
        if (!UPLOAD_FOLDER.exists()) {
            UPLOAD_FOLDER.mkdir();
        }
    }
}