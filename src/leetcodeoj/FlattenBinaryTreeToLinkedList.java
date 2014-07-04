package leetcodeoj;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 *Given a binary tree, flatten it to a linked list in-place.
 *
 *For example,
 *Given
 *
 *       1
 *      / \
 *     2   5
 *    / \   \
 *   3   4   6
 *The flattened tree should look like:
 *     1
 *      \
 *       2
 *        \
 *         3
 *          \
 *           4
 *            \
 *             5
 *              \
 *               6
 *
 *Hints:
 *If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
 */
public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                nodes.add(node);
                node = node.left;
            } else {
                node = stack.pop();
                node = node.right;
            }
        }

        Iterator<TreeNode> itr = nodes.iterator();
        node = itr.next();

        while (itr.hasNext()) {
            TreeNode next = itr.next();
            node.right = next;
            node.left = null;
            node = next;
        }
        node.left = null;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        root.right = n2;
        n2.left = n3;
        flatten(root);
        System.out.println(root);
    }
}
