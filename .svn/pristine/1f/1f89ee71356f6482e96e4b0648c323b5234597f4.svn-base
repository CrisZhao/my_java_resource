package persist.xmlconfig;

import javax.xml.bind.annotation.XmlElement;
public class Entry {
	public enum FileType {
		XML, PROPERTY, CSV
	}

	public Entry() {
	}

	public Entry(String fileName, FileType fileType) {
		this.fileName = fileName;
		this.fileType = fileType;
	}

	private String fileName;
	private FileType fileType;

	@XmlElement(name = "filename")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = FileType.valueOf(fileType.toUpperCase());
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;
		}
		if (obj instanceof Entry) {
			if (this.fileName.equals(((Entry) obj).fileName)
					&& this.fileType.equals(((Entry) obj).fileType))
				return true;
		}
		return false;
	}
}
