package cz.fku.tree;

import cz.fku.numbers.AddNumbersExample;
import org.junit.Test;
import org.slf4j.LoggerFactory;

/**
 * Created by Filip on 05.03.2017.
 */
public class TreeTest {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(TreeTest.class);

    @Test
    public void createTreeTest() {
        TreeNode<Condition> rootTreeNode = new TreeNode<>("0", "root");

        TreeNode<Condition> l1 = rootTreeNode.addChildNode("1","Text pro level 1");
        TreeNode<Condition> l2 = rootTreeNode.addChildNode("2","Text pro level 2");
        TreeNode<Condition> l3 = rootTreeNode.addChildNode("3","Text pro level 3");


        TreeNode<Condition> l21 = l2.addChildNode("2.1","Text pro level 2.1");

        Condition condition211 = new Condition();
        condition211.setName("condition211");
        Variant variant211_1 = new Variant();
        variant211_1.addConstraint(ENProduct.ERL);
        condition211.addVariant(variant211_1);
        l21.addChildLeaf("2.1.1","Text pro level 2.1.1", condition211);

        logger.info(rootTreeNode.toString());
    }

}

