package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.junit.Test;

public class Solution {

	private class ItemNode {
		public String item;
		public Collection<ItemNode> subNodes = new ArrayList<ItemNode>();
		public Vector<String> path;

		public ItemNode(String item, Collection<String> parentPath) {
			this.item = item;
			this.path = new Vector<String>(parentPath);
			this.path.add(item);
		}

		public void addSubNodes(Vector<String> subWords) {
			for (String string : subWords) {
				subNodes.add(new ItemNode(string, path));
			}
		}
	}

	private Map<String, Vector<String>> resultCache = new HashMap<String, Vector<String>>();

	@Test
	public void test() {
		String start = "hit";
		String aim = "cog";
		Set<String> dict = new HashSet<String>(Arrays.asList("hot", "dot",
				"dog", "lot", "log"));
		Vector<Vector<String>> output = findLadders(start, aim, dict);
		System.out.println(output);
	}

	public Vector<Vector<String>> findLadders(String start, String end,
			Set<String> dict) {
		Vector<Vector<String>> paths = new Vector<Vector<String>>();
		if (start.equals(end)) {
			return paths;
		}
		ItemNode tree = initStartNode(dict, start, end);
		while ((paths = getValidatedPaths(tree, end)).isEmpty()) {
			buildNextLevel(tree);
		}
		return paths;

	}

	private Vector<String> searchAvailableWord(Collection<String> dict,
			String word) {
		Vector<String> availabe = new Vector<String>(dict);
		availabe.remove(word);
		for (Iterator<String> iterator = availabe.iterator(); iterator
				.hasNext();) {
			if (isMatch(iterator.next(), searchPatterns(word))) {
				continue;
			}
			iterator.remove();

		}
		return availabe;
	}

	private boolean isMatch(String word, Collection<String> patterns) {
		for (String regex : patterns) {
			if (word.matches(regex)) {
				return true;
			}
		}
		return false;
	}

	private void cacheWords(Collection<String> dict) {
		for (String word : dict) {
			resultCache.put(word, searchAvailableWord(dict, word));
		}
	}

	private Collection<String> searchPatterns(String word) {
		Collection<String> patterns = new ArrayList<String>();
		for (int i = 0; i < word.toCharArray().length; i++) {
			patterns.add(replaceCharAt(word, "[a-z]", i));
		}
		return patterns;
	}

	private String replaceCharAt(String word, String insertWord,
			int replacePosition) {
		return word.substring(0, replacePosition) + insertWord
				+ word.substring(replacePosition + 1, word.length());
	}

	private void buildNextLevel(ItemNode tree) {
		if (!tree.subNodes.isEmpty()) {
			for (ItemNode subNode : tree.subNodes) {
				buildNextLevel(subNode);
			}
		} else {
			tree.addSubNodes(resultCache.get(tree.item));
		}

	}

	private Vector<Vector<String>> getValidatedPaths(ItemNode tree, String aim) {
		Vector<Vector<String>> paths = new Vector<Vector<String>>();
		if (tree.path.contains(aim)) {
			paths.add(tree.path);
			return paths;
		}
		Vector<Vector<String>> subpath;
		for (ItemNode subNode : tree.subNodes) {
			subpath = getValidatedPaths(subNode, aim);
			if (!subpath.isEmpty()) {
				paths.addAll(subpath);
			}
		}
		return paths;
	}

	private ItemNode initStartNode(Collection<String> dict, String start,
			String aim) {
		dict.add(start);
		dict.add(aim);
		cacheWords(dict);
		Vector<String> subWords = searchAvailableWord(dict, start);
		ItemNode tree = new ItemNode(start, new ArrayList<String>());
		tree.addSubNodes(subWords);
		return tree;
	}

}
