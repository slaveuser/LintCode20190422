M
1519663383
tags: Array, Tree, DFS, Divide and Conquer, Hash Table

如题

#### DFS
- 和Construct from Inorder && Postorder 想法一样。
- 写出Preorder和Inorder的字母例子，发现Preorder的开头总是这Level的root。依此写helper,注意处理index。
- 跟Convert Sorted Array to Binary Tree类似, 找到对应的index, 然后:
- node.left = dfs(...), node.right = dfs(...)
- Divide and Conquer
- optimize on finding `mid node`: given value, find mid of inorder:
- Instead of searching linearly, just store inorder sequence in `map <value -> index>`, O(1)
- IMPORATANT: the mid from inorder sequence will become the main baseline to tell range: 
- `range of subTree = (mid - inStart)`
- sapce: O(n), time: O(n) access

```
/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note
You may assume that duplicates do not exist in the tree.

Example
Given inorder [1,2,3] and preorder [2,1,3]

return a tree

  2

 /  \

1    3

Tags Expand 
Binary Tree
*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

 /*
Thougths:
DFS with tracking of preorder/inorder sequence indexes.
preorder: start from root, traverse all left children, and then all right children.
inorder: if found the root node in the sequence, all indexes less than the root is left sub tree; same applies to right indexes.

1. Use preorder head index as root
2. Find the root node index in inorder sequence.
3. split into subproblems: track by indexes
*//**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return dfs(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    
    public TreeNode dfs(int[] preorder, int preStart, int preEnd,
                        int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int mid = map.get(preorder[preStart]);
            
        if (mid < 0) {
            return null;
        }
        //root.left
        root.left = dfs(preorder, preStart + 1, preStart + (mid - inStart),
                       inorder, inStart, mid - 1);
        //root.right
        root.right = dfs(preorder, preStart + (mid - inStart) + 1, preEnd,
                        inorder, mid + 1, inEnd);
        
        return root;
    }
}

 
/**
Previous notes
Thinking process:
See 'Construct tree from inorder + postorder' as example.
This problem uses divide and conquer idea as well.
For preorder: the front node is the root of the tree.
For inorder: find the root in the middle of the array, then the left-side is left-tree, and the right-side is the right-tree.
 */
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length) {
            return null;
        }
        return buildTreeHelper(inorder, 0, inorder.length - 1, 
            preorder, 0, preorder.length - 1);
    }
    
    public TreeNode buildTreeHelper(int[] inorder, int inStart, int inEnd,
        int[] preorder, int preStart, int preEnd) {
        if (inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int mid = findMid(inorder, inStart, inEnd, preorder[preStart]);
        
        root.left = buildTreeHelper(inorder, inStart, mid - 1,
            preorder, preStart + 1, preStart + (mid - inStart));
        root.right = buildTreeHelper(inorder, mid + 1, inEnd,
            preorder, preStart + (mid - inStart) + 1, preEnd);
        return root;
    }
    
    public int findMid(int[] arr, int start, int end, int key) {
        for (int i = start; i <= end; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }
}


```
