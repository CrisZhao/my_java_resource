package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class TextFile {
	private Collection<String> lineData = new ArrayList<String>();
	
	public static String read(String fileName) throws FileNotFoundException {
		StringBuilder sb =new StringBuilder();
		BufferedReader in = new BufferedReader(new FileReader(new File(fileName)));
		String s;
		try {
			while((s = in.readLine())!=null) {
				sb.append(s).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in !=null) {
					in.close();
				}
			} catch (IOException e) {
			}
		}
		return sb.toString();
	}
	
	public static void write(String fileName, String text) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(new File(fileName));
		try {
			out.print(text);
		} finally {
			out.close();
		}
	}
	
	

}
