package utils;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by zhaoqc on 2015/05/18.
 */
public class ZipUtil {
    public static final int BUFFER_SIZE = 8 * 1024;

    public static void zip(String destFile, File srcFile) throws IOException{
        CheckedOutputStream checkedOutputStream = new CheckedOutputStream(new FileOutputStream(destFile), new CRC32());
        ZipOutputStream zipOutputStream = new ZipOutputStream(checkedOutputStream);

        compressFile(srcFile, zipOutputStream, srcFile.getName());
        zipOutputStream.flush();
        zipOutputStream.close();

    }

    private static void compressFile(File file, ZipOutputStream zipOutputStream, String base) {
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            try {
                zipOutputStream.putNextEntry(new ZipEntry(base + "/"));
                base = base.isEmpty() ? "" : base + "/";
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (File f : subFiles) {
                compressFile(f, zipOutputStream, base + f.getName());
            }
        } else {
            FileInputStream fileInputStream = null;
            try {
                ZipEntry entry = new ZipEntry(base);
                zipOutputStream.putNextEntry(entry);
                fileInputStream = new FileInputStream(file);
                int count;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((count = fileInputStream.read(buffer)) != -1) {
                    zipOutputStream.write(buffer, 0, count);
                }
                fileInputStream.close();
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(fileInputStream);
            }
        }
    }

}
