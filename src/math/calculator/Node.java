package math.calculator;

public class Node {
	private int entropy;
	private String value;
	private Node leftNode;
	private Node rightNode;
	private Node parentNode;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public Node getRightNode() {
		return rightNode;
	}

	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}

	public int getEntropy() {
		return entropy;
	}

	public void setEntropy(int entropy) {
		this.entropy = entropy;
	}
}
