package persist.csvloader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.Reader;

public class UnicodeReader extends Reader {

	private InputStreamReader internalIn = null;

	private String encoding;

	private static final int BOM_SIZE = 4;

	public UnicodeReader(InputStream in) throws IOException {
		init(in);
	}

	public void close() throws IOException {
		internalIn.close();
	}

	public String getEncoding() {
		return encoding;
	}

	protected void init(InputStream in) throws IOException {

		PushbackInputStream tempIn = new PushbackInputStream(in, BOM_SIZE);

		byte bom[] = new byte[BOM_SIZE];
		int n;
		int unread;
		n = tempIn.read(bom, 0, bom.length);

		if ((bom[0] == (byte) 0x00) && (bom[1] == (byte) 0x00)
				&& (bom[2] == (byte) 0xFE) && (bom[3] == (byte) 0xFF)) {
			encoding = "UTF-32BE";
			unread = n - 4;
		}

		else if (n == BOM_SIZE
				// Last 2 bytes are 0; could be an empty UTF-16
				&& (bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE)
				&& (bom[2] == (byte) 0x00) && (bom[3] == (byte) 0x00)) {
			encoding = "UTF-32LE";
			unread = n - 4;
		}

		else if ((bom[0] == (byte) 0xEF) && (bom[1] == (byte) 0xBB)
				&& (bom[2] == (byte) 0xBF)) {
			encoding = "UTF-8";
			unread = n - 3;
		}

		else if ((bom[0] == (byte) 0xFE) && (bom[1] == (byte) 0xFF)) {
			encoding = "UTF-16BE";
			unread = n - 2;
		}

		else if ((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE)) {
			encoding = "UTF-16LE";
			unread = n - 2;
		}

		else {
			// Unicode BOM mark not found, unread all bytes
			encoding = "UTF-8";
			unread = n;
		}

		if (unread > 0) {
			tempIn.unread(bom, (n - unread), unread);
		} else if (unread < -1) {
			tempIn.unread(bom, 0, 0);
		}

		internalIn = new InputStreamReader(tempIn, encoding);

	}

	public int read(char[] cbuf, int off, int len) throws IOException {
		return internalIn.read(cbuf, off, len);
	}

}
