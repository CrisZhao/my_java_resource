package math.calculator;

import java.util.Stack;

public class MyOperate {
	private Stack<String> opeStack = new Stack<String>();
	private Stack<Double> numStack = new Stack<Double>();

	public static void main(String[] arg) {
		double value = new MyOperate().calculate("2.5+3*(4+5*6.5)-6*8+3");
		System.out.println(value);
	}

	public double calculate(String exp) {
		StringBuilder tempnum = new StringBuilder();
		for (int i = 0; i < exp.length(); i++) {
			String c = exp.substring(i, i + 1);
			if (c.equals(" ")) {
				continue;
			}
			if (isDigit(c) || c.equals(".")) {
				tempnum.append(c);
			} else {
				if (tempnum.length() > 0) {
					numStack.push(Double.parseDouble(tempnum.toString()));
					tempnum.delete(0, tempnum.length());
				}
				processOperator(c);
			}
		}
		if (tempnum.length() > 0) {
			numStack.push(Double.parseDouble(tempnum.toString()));
		}
		processOperator("end");
		return numStack.pop();
	}

	private void processOperator(String c) {
		while (!opeStack.isEmpty() && compare(c)) {
			// 优先级低，栈顶出栈进行计算
			double a = numStack.pop();
			double b = numStack.pop();
			double result = calculate(b, a, opeStack.pop());
			numStack.push(result);
		}
		// 优先级高入栈
		opeStack.push(c);
		if (c.equals(")")) {
			opeStack.pop();
			opeStack.pop();
		}
	}

	private boolean compare(String c) {
		if (opeStack.peek().equals("(")) {
			return false;
		}
		return getValue(c) <= getValue(opeStack.peek());
	}

	private double calculate(double a, double b, String operator) {
		if (operator.equals("+")) {
			return a + b;
		}
		if (operator.equals("-")) {
			return a - b;
		}
		if (operator.equals("*")) {
			return a * b;
		}
		if (operator.equals("/")) {
			return a / b;
		}
		return Double.NaN;

	}

	private boolean isDigit(String c) {
		return c.matches("[0-9]");
	}

	private int getValue(String operator) {
		if (operator.equals("+") || operator.equals("-")) {
			return 20;
		} else if (operator.equals("*") || operator.equals("/")) {
			return 30;
		} else if (operator.equals("(")) {
			return 100;
		}
		return 0;
	}

}
