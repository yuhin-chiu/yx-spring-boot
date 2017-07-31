package cn.yx.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import cn.yx.constant.FileConstant;
import cn.yx.enums.ApiResponseEnum;
import cn.yx.model.ApiResponse;

/**
 * @author yuxuanjiao
 * @date 2017年7月30日 下午9:02:29
 * @version 1.0
 */

public class FileUtil {

    /**
     * 如果没传入fileName参数，就生成随机值（不包含前面的斜杠）
     * 
     * @param file
     * @return
     */
    public static ApiResponse uploadFile(MultipartFile file) {
        return FileUtil.uploadFile(file, FileUtil.randomName(file.getOriginalFilename()));
    }

    /**
     * 如果传入了fileName参数，就按参数保存（不包含前面的斜杠）
     * 
     * @param file
     * @param fileName
     * @return
     */
    public static ApiResponse uploadFile(MultipartFile file, String fileName) {
        ApiResponse resp = new ApiResponse();
        BufferedOutputStream stream = null;
        try {
            byte[] bytes = file.getBytes();
            // 解决中文问题，linux下中文路径，图片显示问题
            stream = new BufferedOutputStream(
                    new FileOutputStream(FileUtil.createFileSafe(FileConstant.UPLOAD_FOLDER, fileName)));
            stream.write(bytes);
        } catch (Exception e) {
            resp.setCode(ApiResponseEnum.FILE_SAVE_FAILED.getCode());
            resp.setMsg(ApiResponseEnum.FILE_SAVE_FAILED.getContent());
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                resp.setCode(ApiResponseEnum.INTERNAL_ERROR.getCode());
                e.printStackTrace();
            }
        }

        resp.setData(fileName);
        return resp;
    }

    public static void downloadFile(String fileName, BufferedOutputStream out) throws IOException {

        File file = new File(FileConstant.UPLOAD_FOLDER, fileName);
        if (file.exists()) {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

            out.flush();
            in.close();
        }
    }

    /**
     * 生成一个相同后缀的随机名字
     * 
     * @param fileName
     * @return
     */
    public static String randomName(String fileName) {
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;
        return fileName;
    }

    private static File createFileSafe(File file, String fileName) {
        File rFile = new File(file, fileName);

        if (!rFile.getParentFile().exists()) {
            rFile.getParentFile().mkdirs();
        }

        return rFile;
    }
}
