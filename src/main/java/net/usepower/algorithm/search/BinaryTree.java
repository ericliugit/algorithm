package net.usepower.algorithm.search;

/**
 * 二叉树
 * created by eric on 18-2-14
 */
class BinaryTree {

    private BinaryTreeNode root;

    BinaryTree(BinaryTreeNode root) {
        this.root = root;
    }

    /**
     * 查询二叉树元素
     *
     * @param key 查询主键值
     * @return 查询结果
     */
    Integer search(int key) {
        return search(this.root, key);
    }

    private Integer search(BinaryTreeNode node, int key) {
        if (node == null) {
            return null;
        }
        if (node.getValue() - key == 0) {
            return node.getValue();
        }
        if (node.getValue() - key < 0) {
            return search(node.getRight(), key);
        }
        if (node.getValue() - key > 0) {
            return search(node.getLeft(), key);
        }
        return null;
    }

    /**
     * 新增二叉树元素
     *
     * @param t 元素类型
     */
    void insert(int t) {
        this.root.insert(new BinaryTreeNode(t));
    }

    /**
     * 删除二叉树元素
     */
    void delete(int key) {
        delete(null, this.root, key);
    }

    /**
     * 在node节点中查找key值的节点
     *
     * @param node 所查找的节点
     * @param key  key值
     */
    private void delete(BinaryTreeNode parent, BinaryTreeNode node, int key) {
        if (node == null) {
            return;
        }
        if (node.getValue() == key) {
            delete(parent, node);
        } else if (node.getValue() > key) {
            delete(node, node.getLeft(), key);
        } else {
            delete(node, node.getRight(), key);
        }
    }

    /**
     * 删除node节点
     *
     * @param node
     */
    private void delete(BinaryTreeNode parent, BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        // 叶子节点
        if (node.getLeft() == null && node.getRight() == null) {
            if (parent != null && parent.getLeft() == node) {
                parent.setLeft(null);
            }
            if (parent != null && parent.getRight() == node) {
                parent.setRight(null);
            }
            node.setValue(null);
        }
        // 仅左子节点
        if (node.getLeft() == null && node.getRight() != null) {
            reLink(parent, node, node.getLeft());
        }
        // 仅右子节点
        if (node.getRight() == null && node.getLeft() != null) {
            reLink(parent, node, node.getRight());
        }
        // 左右子节点
        if (node.getLeft() != null && node.getRight() != null) {
            // 选择左子节点,最右无有右节点的子节点
            BinaryTreeNode selectParentNode = node;
            BinaryTreeNode selectNode = node.getLeft();
            while (selectNode.getRight() != null) {
                selectParentNode = selectNode;
                selectNode = selectNode.getRight();
            }
            node.setValue(selectNode.getValue());
            // 左子节点没有子节点了
            if (selectParentNode == node) {
                node.setLeft(selectNode.getLeft());
            } else {
                selectParentNode.setRight(selectNode.getLeft());
            }
        }
    }

    private void reLink(BinaryTreeNode parent, BinaryTreeNode node, BinaryTreeNode child) {
        if (parent != null && parent.getLeft() == node) {
            parent.setLeft(child);
        }
        if (parent != null && parent.getLeft() == node) {
            parent.setRight(child);
        }
        if (parent == null) {
            node.setValue(child.getValue());
            node.setLeft(child.getLeft());
            node.setRight(child.getRight());
        }
    }


    /**
     * 创建排序的二叉树
     * @param source
     * @return
     */
    static  BinaryTree instance(int[] source) {
        BinaryTreeNode[] binaryTreeNode = convert(source);
        BinaryTree binaryTree = new BinaryTree(binaryTreeNode[0]);
        for (BinaryTreeNode item : binaryTreeNode) {
            binaryTree.root.insert(item);
        }
        return binaryTree;
    }

    private static BinaryTreeNode[] convert(int[] source) {
        BinaryTreeNode[] result = new BinaryTreeNode[source.length];
        for (int i = 0; i < source.length; i++) {
            result[i] = new BinaryTreeNode(source[i]);
        }
        return result;
    }

}
