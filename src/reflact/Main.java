package reflact;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s;
		while ((s = scanner.nextLine()).length() > 0) {
			String[] values = s.split("-");
			int year = Integer.parseInt(values[0]);
			int month = Integer.parseInt(values[1]);
			Calendar c = new GregorianCalendar(year, month, 28);
			SimpleDateFormat f = new SimpleDateFormat("EE", Locale.US);
			System.out.println(f.format(c.getTime()));
			String week = String.format(Locale.US,"%tA", c.getTime()).toUpperCase();
			System.out.println(week);
		}
	}

}
