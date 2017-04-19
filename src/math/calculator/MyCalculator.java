package math.calculator;
/**
 * 将四则运算表达式解析为二叉树结构，然后递归计算结果
 * 
 * @author criszhao
 *
 */

public class MyCalculator {
	private int offset = 0;
	private int number = 30;
	private int add_sub = 10;
	private int mul_div = 20;
	private int off = 100;
	private Node present = null;
	private int index = 0;

	public static void main(String[] args) {
		String exp = "(10.5 + 15) * 3.2 - 20 * 6 /5 - (8 + 14*(2.5- 1))*2 + 11*(12 - 11)*5";
		MyCalculator cal = new MyCalculator();

		System.out.println(cal.cal(exp));
	}

	public double cal(String exp) {
		Node root = buildTree(exp);
		return calculate(root);
	}

	private double calculate(Node node) {
		if (node.getLeftNode() == null && node.getRightNode() == null)
			return Double.parseDouble(node.getValue());

		if (node.getValue().equals("+")) {
			return calculate(node.getLeftNode())
					+ calculate(node.getRightNode());
		} else if (node.getValue().equals("-")) {
			return calculate(node.getLeftNode())
					- calculate(node.getRightNode());
		} else if (node.getValue().equals("*")) {
			return calculate(node.getLeftNode())
					* calculate(node.getRightNode());
		} else {
			return calculate(node.getLeftNode())
					/ calculate(node.getRightNode());
		}
	}

	private Node buildTree(String exp) {
		char[] exps = exp.toCharArray();
		Node root = null;
		Node node = null;
		while (true) {
			node = readNextNode(exps);
			if (node == null) {
				break;
			}
			if (present == null) {
				root = node;
				present = node;
			} else {
				if (node.getEntropy() < present.getEntropy()) {
					Node parent = getSuitableParentNode(node);
					if (parent == null) {
						present.setParentNode(node);
						node.setLeftNode(present);
						root = node;
					} else {
						Node tmp = parent.getRightNode();
						parent.setRightNode(node);
						node.setParentNode(parent);
						tmp.setParentNode(node);
						node.setLeftNode(tmp);
					}

				} else {
					present.setRightNode(node);
					node.setParentNode(present);
				}
				present = node;
			}
		}
		return root;

	}

	private Node getSuitableParentNode(Node node) {
		Node parent = present.getParentNode();
		while (parent != null && node.getEntropy() <= parent.getEntropy()) {
			present = parent;
			parent = parent.getParentNode();
		}
		return parent;
	}

	private Node readNextNode(char[] exps) {
		if (index >= exps.length)
			return null;

		Node node = new Node();
		char c = exps[index++];
		if (c == ' ' || c == '\n') {
			return readNextNode(exps);
		}
		if (Character.isDigit(c)) {
			node.setEntropy(number + offset);
			node.setValue(getNumberValue(exps, c));
		}
		if (c == '(') {
			offset += off;
			return readNextNode(exps);
		}
		if (c == ')') {
			offset -= off;
			return readNextNode(exps);
		}
		if (c == '*' || c == '/') {
			node.setEntropy(mul_div + offset);
			node.setValue(Character.toString(c));
		}
		if (c == '+' || c == '-') {
			node.setEntropy(add_sub + offset);
			node.setValue(Character.toString(c));
		}
		return node;
	}

	private String getNumberValue(char[] exps, char c) {
		StringBuilder sb = new StringBuilder();
		sb.append(c);
		for (; index < exps.length; index++) {
			char next = exps[index];
			if (!(Character.isDigit(next) || next == '.')) {
				break;
			}
			sb.append(next);
		}
		return sb.toString();
	}

}
