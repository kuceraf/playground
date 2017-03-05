package cz.fku.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Filip on 05.03.2017.
 */
public class TreeNode<T> {

    private String level;
    private String text;
    private T leafData;
    private TreeNode<T> parent;
    private List<TreeNode<T>> children;

    //node constructor
    public TreeNode(String level, String text) {
        this.level = level;
        this.text = text;
    }

    //leaf constructor
    public TreeNode(String level, String text, T leafData) {
        this.level = level;
        this.text = text;
        this.leafData = leafData;
    }

    public TreeNode<T> addChildNode(String level, String text) {
        TreeNode<T> childNode = new TreeNode<T>(level, text);
        childNode.parent = this;
        if (children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(childNode);
        return childNode;
    }

    public TreeNode<T> addChildLeaf(String level, String text, T leafData) {
        TreeNode<T> childNode = new TreeNode<T>(level, text, leafData);
        childNode.parent = this;
        if (children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(childNode);
        return childNode;
    }

    // other features ...

}
