import java.util.ArrayList;

/*
    使用方法:
       TreeNode root = new TreeNode(..);
       ...初始化树结构...
       ArrayList<Integer> result = PreOrder.preorder(root);
 */
public class PreOrder {
    private static ArrayList<Integer> result = new ArrayList<Integer> ();

    public static ArrayList<Integer> preorder(TreeNode root){
        helper(root);
        return result;
    }

    private static void helper(TreeNode root){
        if(root==null){
            return;
        }
        result.add(root.getVal());
        helper(root.getLeft());
        helper(root.getRight());
    }

}
