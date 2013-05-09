package persist.csvloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class NavigableReader {

    private final BufferedReader reader;
    private final String ignores = "#";

    private int lineNum; // default to 0

    private String currentLine;
    private List<String> currentLineList;

    private boolean reset; // default to false

    public NavigableReader(BufferedReader reader) {
        this.reader = reader;
    }

    public NavigableReader(InputStream in) throws IOException {
        this(new BufferedReader(new UnicodeReader(in)));
    }

    public String readLine() throws IOException {
        lineNum++;
        if (reset) {
            reset = false;
            return currentLine;
        }
        currentLineList = null;
        currentLine = reader.readLine();
        return currentLine;
    }

    public void reset() {
        if (!reset) {
            if (lineNum <= 0) {
                throw new RuntimeException("Can't resetting line.");
            }
            lineNum--;
            reset = true;
        }
    }

    public String currentLine() {
        return currentLine;
    }

    public List<String> currentLineList() {
        if (currentLineList == null) {
            currentLineList = CSVUtils.parseLine(currentLine);
        }
        return currentLineList;
    }

    public boolean isCurrentLineEmpty() {
        return currentLine == null || currentLineList().isEmpty() || currentLine.startsWith(ignores);
    }

    public String indexCurrentLine(int index) {
        if (index < currentLineList().size()) {
            return currentLineList().get(index);
        }
        return null;
    }

    public int getLineNum() {
        return lineNum;
    }

    public boolean close() {
        try {
            this.reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
