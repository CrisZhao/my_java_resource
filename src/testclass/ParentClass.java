package testclass;

import org.junit.Test;

public class ParentClass {

	private Tt tt = new Tt();

	public ParentClass() {
		System.out.println("pp");
	}

	public void method() {
		System.out.println("parent");
	}

	public class Tt {
		public Tt() {
			System.out.println("Tt");
		}
	}

	public void in(Tt t) {
		tt = t;
	}
	@Test
	public void dddd() {
		
	 String mainstr = "[[data link control], [communication, []], [computer, [applications     of computer, number of computer]], [world wide web], [lesson, [covered in lesson]], [access to remote], [marketing and sale], [electronic fund transfer], [network, [network of network, wide area network, communication network, computer network, [area network, [local area network, metropolitan area network]]]]]";

	 String search = "communication network";

	 if (mainstr.contains(search)) {
	             if (mainstr.charAt(mainstr.indexOf(search) + search.length()) == ']' && mainstr.charAt(mainstr.indexOf(search) - 2) == '[') {
	                 System.out.println("single term");
	             } else {
	                 int indexSearch = mainstr.indexOf(search) + search.length();
	                 String followers = mainstr.substring(indexSearch, mainstr.length());
	                 if (!followers.equals("")) {
	                     System.out.println("No FOLLOWERS");
	                 } else {
	                     System.out.println("followers = " + followers.substring(0, followers.indexOf("]")));
	                 }
	                 if (mainstr.charAt(mainstr.indexOf(search) - 4) == ']') {
	                     System.out.println("No pre found");
	                 } else {
	                     System.out.println("preups are present");
	                     String preup = mainstr.split(search)[0].substring(0, mainstr.split(search)[0].length() - 1);
	                     String finalPreup = preup.substring(preup.lastIndexOf("[") + 1, preup.lastIndexOf(","));
	                     System.out.println("final : " + finalPreup);
	                 }
	                 System.out.println("found...");
	             }
	         } else {
	             System.out.println("Not Found");
	         }}
}
