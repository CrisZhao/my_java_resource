package cpdetector;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.ByteOrderMarkDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;

public class CPDetector {
	CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
	private static CPDetector instance = new CPDetector();
	
	public static CPDetector getInstance() {
		return instance;
	}
	
	public CodepageDetectorProxy getDetectorProxy() {
		return detector;
	}

	private CPDetector() {
		// Add the implementations of
		// info.monitorenter.cpdetector.io.ICodepageDetector:
		// This one is quick if we deal with unicode codepages:
		detector.add(new ByteOrderMarkDetector());
		// The first instance delegated to tries to detect the meta charset
		// attribut in html pages.
		detector.add(new ParsingDetector(true)); // be verbose about parsing.
		// This one does the tricks of exclusion and frequency detection, if
		// first implementation is
		// unsuccessful:
		detector.add(JChardetFacade.getInstance()); // Another singleton.
		detector.add(ASCIIDetector.getInstance()); // Fallback, see javadoc.
	}

	public Charset getCharset(File document) {
		try {
			return detector.detectCodepage(document.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}



