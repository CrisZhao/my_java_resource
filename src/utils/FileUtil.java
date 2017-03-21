package utils;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Pattern;

public class FileUtil {

    public static final String SEPARATOR = ",";
    public static final String NEWLINE = "\n";

    /**
     * @param fileName file name
     * @return file content
     * @throws IOException file not exist
     */
    public static String readTextFile(String fileName) throws IOException {
        return readInputStreamToString(new FileInputStream(new File(fileName)));
    }

    /**
     * @param inputStream inputStream
     * @return file content
     * @throws IOException IOException
     */
    public static String readInputStreamToString(InputStream inputStream)
            throws IOException {
        StringWriter writer = new StringWriter();
        streamCopy(inputStream, writer);
        return writer.toString();
    }

    /**
     * @param in  InputStream
     * @param out OutputStream
     * @throws IOException IOException
     */
    public static void streamCopy(InputStream in, OutputStream out)
            throws IOException {
        try {
            streamCopy(in,
                    new OutputStreamWriter(out, Charset.defaultCharset()));
        } finally {
            in = null;
            out = null;
        }
    }

    /**
     * @param in     InputStream
     * @param writer Writer
     * @throws IOException IOException
     */
    public static void streamCopy(InputStream in, Writer writer)
            throws IOException {
        try {
            BufferedWriter bufferredWriter = new BufferedWriter(writer);
            IOUtils.copy(in, bufferredWriter);
            bufferredWriter.flush();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(writer);
            in = null;
            writer = null;
        }
    }

    /**
     * @param path ,
     * @return ,
     */
    public static String fixDirPath(String path) {
        if (path == null) {
            return "";
        }
        path = path.replaceAll("\\\\", "/");
        if (!path.endsWith("/")) {
            path += "/";
        }
        return path;
    }

    /**
     * @param path ,
     * @return ,
     */
    public static boolean createDirIfNeeded(String path) {
        path = fixDirPath(path);
        File dirFile = new File(path);
        return createDirIfNeeded(dirFile);
    }

    /**
     * @param dirFile ,
     * @return ,
     */
    public static boolean createDirIfNeeded(File dirFile) {
        return dirFile.exists() ? dirFile.isDirectory() : dirFile.mkdirs();
    }

    public static String getBomHeader() {
        byte[] bomheader = new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
        return new String(bomheader);
    }

    public static List<String[]> getCSVContent(String sourceFileName) {
        BufferedReader reader = null;
        List<String[]> lists = Lists.newArrayList();
        try {
            reader = new BufferedReader(
                    new FileReader(new File(sourceFileName)));
            lists = getCSVContent(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }
        return lists;
    }

    public static List<String[]> getCSVContent(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        return getCSVContent(reader);

    }

    public static List<String[]> getCSVContent(BufferedReader reader) {
        List<String[]> lists = Lists.newArrayList();
        try {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String trimedLine = line.trim();
                if (trimedLine.isEmpty() || trimedLine.startsWith("#")) {
                    continue;
                }
                String[] items = trimedLine.split(",");
                lists.add(items);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }
        return lists;
    }

    public static void delDir(String filepath) {
        File f = new File(filepath);
        if (f.exists() && f.isDirectory()) {
            for (File subFile : f.listFiles()) {
                if (subFile.isDirectory()) {
                    delDir(subFile.getAbsolutePath());
                }
                subFile.delete();
            }
            f.delete();
        }

    }

    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path, final String regex) {

        return local(new File(path), regex);
    }

    public static void appendWrite(String path, String content) throws IOException {
        Files.append(content, new File(path), Charsets.UTF_8);
    }

}
