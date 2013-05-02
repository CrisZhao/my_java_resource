package math;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

public class MyCalculator {
	
	public List<String> parse(String exp) {
		List<String> exps = Lists.newArrayList();
		Pattern pattern = Pattern.compile("\\+|-|\\*|/");
		Matcher match = pattern.matcher(exp);
		Pattern p = Pattern.compile("\\+|-|\\*|/|\\(|\\)");
	      Matcher  m = p.matcher(exp);
	        while(m.find()){
	            System.out.println(m.group());
	        }
	        p = Pattern.compile("\\d+(\\.\\d+)*");
	        m = p.matcher(exp);
	        while(m.find()){
	            System.out.println(m.group());
	        }
		
		
		return exps;
		
	}

}
