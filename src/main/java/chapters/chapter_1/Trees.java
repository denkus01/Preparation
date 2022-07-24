package chapters.chapter_1;

import chapters.structures.Node;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode<T> {
    private T value;
    private List<TreeNode<T>> children;

    private TreeNode(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public static <T> TreeNode<T> of(T value) {
        return new TreeNode<>(value);
    }

    public TreeNode<T> addChild(T value) {
        TreeNode<T> newChild = new TreeNode<>(value);
        children.add(newChild);
        return newChild;
    }
}


public class Trees {

    public ArrayList<Integer> BFS(TreeNode root) {
        ArrayList<Integer> lists = new ArrayList();
        if (root == null) return lists;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode tree = queue.poll();

        }

    }
}
