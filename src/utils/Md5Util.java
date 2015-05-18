package utils;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Created by zhaoqc on 2015/05/18.
 */
public class Md5Util {
    private static final int BUFFER_SIZE = 8 * 1024;
    public static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 获取文件的md5码
     *
     * @param messageDigest MessageDigest.getInstance("MD5")
     * @param file
     * @return
     */
    public static String getCode(MessageDigest messageDigest, String file) {
        InputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[BUFFER_SIZE];
            int count;
            while ((count = fis.read(buffer)) > 0) {
                messageDigest.update(buffer, 0, count);
            }
            return toHexString(messageDigest.digest());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fis);
        }
        return "";

    }

    public static String toHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder(bytes.length * 2);
        for (byte c : bytes) {
            stringBuilder.append(HEX_CHAR[(c & 0xf0) >> 4]);
            stringBuilder.append(HEX_CHAR[c & 0x0f]);
        }
        return stringBuilder.toString();
    }
}
