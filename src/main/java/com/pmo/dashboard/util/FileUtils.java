package com.pmo.dashboard.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pmo.dashboard.controller.PerformanceController;

public class FileUtils {
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 文件批量压缩
     * @author: xuexuan
     * 2018年10月15日 下午8:12:50
     * @param fileList 压缩文件列表
     * @param byteOutPutStream 
     * @throws IOException 
     * void
     * 
     */
    public static void batchFileToZIP(List<File> fileList, ByteArrayOutputStream byteOutPutStream) {
        if (fileList == null || fileList.size() == 0) {
            logger.warn("压缩文件列表为空，请检查！");
            return;
        }
        BufferedInputStream br = null;//输入流
        ZipOutputStream out = null; // 压缩文件输出流
        int size = -1;
        byte[] buffer = new byte[2048];// 定义缓冲区

        try {
            out = new ZipOutputStream(byteOutPutStream);
            for (File f : fileList) {
                if (!f.exists()) {
                    logger.error("==========要压缩的文件不存在,请检查！文件路径：" + f.getAbsolutePath());
                    continue;
                }
                // 使用指定名称创建新的 ZIP 条目 （通俗点就是文件名）,写入新的 ZIP 文件条目并将流定位到条目数据的开始处
                out.putNextEntry(new ZipEntry(f.getName()));
                br = new BufferedInputStream(new FileInputStream(f));
                while ((size = br.read(buffer)) != -1) {
                    out.write(buffer, 0, size);
                    out.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
