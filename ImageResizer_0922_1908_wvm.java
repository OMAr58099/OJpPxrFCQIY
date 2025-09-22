// 代码生成时间: 2025-09-22 19:08:44
package com.example.imageresizer;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片尺寸批量调整器
 */
public class ImageResizer {

    /**
     * 调整单个图片尺寸
     *
     * @param srcPath 源图片路径
     * @param destPath 目标图片路径
     * @param targetWidth 目标宽度
     * @param targetHeight 目标高度
     * @throws IOException
     */
    private static void resizeImage(String srcPath, String destPath, int targetWidth, int targetHeight) throws IOException {
        BufferedImage srcImage = ImageIO.read(new File(srcPath));

        // 计算缩放比例
        double scaleX = (double) targetWidth / srcImage.getWidth();
        double scaleY = (double) targetHeight / srcImage.getHeight();

        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, srcImage.getType());
        resizedImage.getGraphics().drawImage(srcImage, 0, 0, targetWidth, targetHeight, null);

        ImageIO.write(resizedImage, "jpg", new File(destPath));
    }

    /**
     * 批量调整图片尺寸
     *
     * @param sourceDir 源文件夹路径
     * @param targetDir 目标文件夹路径
     * @param targetWidth 目标宽度
     * @param targetHeight 目标高度
     * @throws IOException
     */
    public static void resizeImagesInBatch(String sourceDir, String targetDir, int targetWidth, int targetHeight) throws IOException {
        File sourceFolder = new File(sourceDir);
        File[] files = sourceFolder.listFiles();

        if (files == null) {
            throw new IOException("Source directory is not valid");
        }

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".jpg")) {
                String srcPath = file.getAbsolutePath();
                String destPath = targetDir + File.separator + file.getName();

                resizeImage(srcPath, destPath, targetWidth, targetHeight);
            }
        }
    }

    /**
     * 主方法
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String sourceDir = "path/to/source/directory";
        String targetDir = "path/to/target/directory";
        int targetWidth = 800;
        int targetHeight = 600;

        resizeImagesInBatch(sourceDir, targetDir, targetWidth, targetHeight);
    }
}
