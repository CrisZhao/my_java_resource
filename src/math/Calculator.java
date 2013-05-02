package math;

public class Calculator {

	public static void main(String[] args) {
		String exp = "(10 + 15) * 3 - 20 * 6 /5 - (8 + 14*(2- 1))*2 + 11*(12 - 11)*5";
		// String exp = "12";
		Calculator cal = new Calculator();
		Calculator.index = 0;

		char[] input = cal.prepare(exp.toCharArray());

		System.out.println(cal.cal(input));
	}

	/**
	 * Actual calculate method.
	 * 
	 * @param exp
	 * @return
	 */
	public int cal(char[] exp) {
		Node root = buildTree(exp);

		return calculate(root);
	}

	/**
	 * Prepare the exp, remove empty space or \n. Also the method will add
	 * losing * in below cases: 10(3-1) ----> 10*(3-1) (3-1)10 ----> (3-1)*10
	 * 
	 * @param exp
	 * @return
	 */
	public char[] prepare(char[] exp) {
		char[] worklist = new char[exp.length];

		int j = 0;
		for (int i = 0; i < exp.length; i++) {
			char c = exp[i];

			if (c == ' ' || c == '\n') {
				continue;
			} else {
				if (c == '(') { // Handle the abbreviated * for (
					if (j == 0 || isCalculator(worklist[j - 1])) {
						// Do nothing.
					} else {
						worklist[j++] = '*';
					}

					worklist[j++] = c;
				} else if (c == ')') {// Handle the abbreviated * for )
					worklist[j++] = c;

					while ((i == exp.length - 1) || (exp[++i] == ' ')) {
						// Do nothing.
					}

					if (isCalculator(exp[i]) || exp[i] == ')') {
						// Do nothing.
					} else {
						worklist[j++] = '*';
					}

					i--;
				} else {
					worklist[j++] = c;
				}
			}
		}

		char[] result = new char[j];

		System.arraycopy(worklist, 0, result, 0, j);

		return result;
	}

	/**
	 * Check if c is a calculator or not.
	 * 
	 * @param c
	 * @return
	 */
	private boolean isCalculator(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/') {
			return true;
		}

		return false;
	}

	/**
	 * Calculate the tree.
	 * 
	 * @param node
	 * @return
	 */
	private int calculate(Node node) {
		if (node.isLeaf())
			return Integer.parseInt(node.value);

		if (node.value.equals("+")) {
			return calculate(node.leftChild) + calculate(node.rightChild);
		} else if (node.value.equals("-")) {
			return calculate(node.leftChild) - calculate(node.rightChild);
		} else if (node.value.equals("*")) {
			return calculate(node.leftChild) * calculate(node.rightChild);
		} else {
			return calculate(node.leftChild) / calculate(node.rightChild);
		}
	}

	/**
	 * Build a tree like this:
	 * 
	 * 10 * (3 + 5) * ------ 10 - ------ + - ------- 3 ------- 5
	 * 
	 * @param exp
	 * @return
	 */
	private Node buildTree(char[] exp) {
		Node root = null;
		Node working = null;

		while (true) {
			Node node = readNext(exp);

			if (node == null)
				break;

			if (root == null) {
				root = node;
				working = node;

				continue;
			}

			if (node.type > working.type) {

				Node parent = working.parent;
				boolean isLeft = false;
				while (parent != null && node.type >= parent.type) {
					isLeft = parent.isLeft;
					working = parent;
					parent = parent.parent;
				}

				if (parent == null) {
					working.parent = node;
					node.leftChild = working;
					working.isLeft = true;

					root = node;
				} else {
					Node tmp = isLeft ? parent.leftChild : parent.rightChild;

					if (isLeft) {
						parent.leftChild = node;
					} else {
						parent.rightChild = node;
					}
					node.isLeft = isLeft;
					node.parent = parent;
					tmp.parent = node;
					node.leftChild = tmp;
					tmp.isLeft = true;
				}
			} else {
				working.rightChild = node;
				node.isLeft = false;
				node.parent = working;
			}

			working = node;
		}

		return root;
	}

	private static int index = 0;

	// Read the next node, it possible to be a number, a calculator or just
	// space.
	private Node readNext(char[] exp) {

		if (index >= exp.length)
			return null;

		Node node = new Node();

		char c = exp[index++];

		if (Character.isDigit(c)) {
			node.type = Node.NUMBER + offset;
			StringBuffer sb = new StringBuffer();
			sb.append(c);
			for (; index < exp.length; index++) {
				char tmp = exp[index];
				if (Character.isDigit(tmp)) {
					sb.append(tmp);
				} else {
					break;
				}
			}

			node.value = sb.toString();
		} else if (c == '*' || c == '/') {
			node.type = Node.MUL_DEL + offset;
			node.value = Character.toString(c);
		} else if (c == '+' || c == '-') {
			node.type = Node.PLUS_MINUS + offset;
			node.value = Character.toString(c);
		} else if (c == '(') {
			increaseOffset();
			return readNext(exp);
		} else if (c == ')') {
			decreaseOffset();
			return readNext(exp);
		}

		return node;
	}

	// Every type in the embrace will have to add a offset as their type.
	private static int offset = 0;

	public static void increaseOffset() {
		offset = offset - 100;
	}

	public static void decreaseOffset() {
		offset = offset + 100;
	}

	/**
	 * Helping class.
	 * 
	 */
	class Node {
		private int type = 10;

		private Node parent = null;

		private Node leftChild = null;

		private Node rightChild = null;

		private boolean isLeft = false;

		private String value = null;

		public Node() {

		}

		public static final int NUMBER = 10;
		public static final int MUL_DEL = 20;
		public static final int PLUS_MINUS = 30;

		public static final int EMBRACE = -100;

		public boolean isLeaf() {
			if (leftChild == null && rightChild == null) {
				return true;
			}

			return false;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public Node getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}

		public Node getRightChild() {
			return rightChild;
		}

		public void setRightChild(Node rightChild) {
			this.rightChild = rightChild;
		}

		public boolean isLeft() {
			return isLeft;
		}

		public void setLeft(boolean isLeft) {
			this.isLeft = isLeft;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
