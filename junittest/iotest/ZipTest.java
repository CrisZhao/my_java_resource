package iotest;

import java.io.*;
import java.util.zip.*;

/**
 * User: Cris Zhao
 * Date: 13-8-7
 * Time: 下午4:33
 */
public class ZipTest {
    public static void zip(String inputfile, String outputfile) {
        BufferedOutputStream out = null;
        BufferedReader in = null;
        try {
            FileOutputStream f = new FileOutputStream(outputfile);
            CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
            ZipOutputStream zos = new ZipOutputStream(csum);
            out = new BufferedOutputStream(zos);
            zos.putNextEntry(new ZipEntry(inputfile));
            in = new BufferedReader(new FileReader(inputfile));
            int c;
            while ((c = in.read()) != -1)
                out.write(c);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void unZipFile(String input) {
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(input);
            CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
            ZipInputStream zin = new ZipInputStream(csumi);
            BufferedInputStream bis = new BufferedInputStream(zin);
            ZipEntry ze;
            while ((ze=zin.getNextEntry())!=null) {
                int x;
                while ((x=bis.read())!=-1) {
                    System.out.write(x);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String input = "c:/22.log";
        String output = "c:/dell/file.zip";
        zip(input, output);
        unZipFile(output);
    }
}
