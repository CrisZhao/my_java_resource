package persist.properties;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import utils.FileUtil;

import java.io.*;
import java.util.List;
import java.util.Properties;


/**
 * for FE process property file
 *
 * @author Cris Zhao
 */
public class PropertyFileManage {
    private static final String FOLDER = PropertyFileManage.class.getResource("sourceFile").getPath();
    private static String sourceFile = FOLDER + "sourceFile";
    private static String zhFileName = FOLDER
            + "AttributeDictionary_zh.properties";
    private static String enFileName = FOLDER
            + "AttributeDictionary.properties";

    @Test
    @Ignore
    public void appendContent() {

        Properties zh = readExistContent(zhFileName);
        Properties en = readExistContent(enFileName);
        append(zh, en);
        write2File(zh, zhFileName);
        write2File(en, enFileName);
    }

    private void write2File(Properties properties, String fileName) {
        OutputStream output = null;
        try {
            output = new FileOutputStream(fileName);
            properties.store(output, "attributeDictionary");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(output);
        }
    }

    private void append(Properties zh, Properties en) {
        List<String[]> result = FileUtil.getCSVContent(sourceFile);
        for (String[] values : result) {
            en.setProperty(values[0].trim(), values[1].trim());
            zh.setProperty(values[0].trim(), values[2].trim());
        }

    }

    private Properties readExistContent(String fileName) {
        Properties property = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(fileName);
            property.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
        return property;
    }

}
