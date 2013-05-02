package persist.xmlconfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

public class FileUtils {

	/**
	 * 
	 * @param fileName
	 *            file name
	 * @return file content
	 * @throws IOException
	 *             file not exist
	 */
	public static String readTextFile(String fileName) throws IOException {
		return readInputStreamToString(new FileInputStream(new File(fileName)));
	}

	/**
	 * 
	 * @param inputStream
	 *            inputStream
	 * @return file content
	 * @throws IOException
	 *             IOException
	 */
	public static String readInputStreamToString(InputStream inputStream)
			throws IOException {
		StringWriter writer = new StringWriter();
		streamCopy(inputStream, writer);
		return writer.toString();
	}

	/**
	 * 
	 * @param in
	 *            InputStream
	 * @param out
	 *            OutputStream
	 * @throws IOException
	 *             IOException
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
	 * 
	 * @param in
	 *            InputStream
	 * @param writer
	 *            Writer
	 * @throws IOException
	 *             IOException
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
	 * 
	 * @param path
	 *            ,
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
	 * 
	 * @param path
	 *            ,
	 * @return ,
	 */
	public static boolean createDirIfNeeded(String path) {
		path = fixDirPath(path);
		File dirFile = new File(path);
		return createDirIfNeeded(dirFile);
	}

	/**
	 * 
	 * @param dirFile
	 *            ,
	 * @return ,
	 */
	public static boolean createDirIfNeeded(File dirFile) {
		return dirFile.exists() ? dirFile.isDirectory() : dirFile.mkdirs();
	}

}
