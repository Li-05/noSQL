
/*
    使用方法:
    TreeNode root=new TreeNode (..);
    ...初始化树...
    MinSubNum tool = MinSubNum(root);
    TreeNode MinNode = tool.getMinSubNode();
 */

public class MinSubNum {
    //根节点
    private TreeNode root;

    //记录最小子节点和的节点ret以及其数字和的大小min
    private TreeNode ret;
    private int min;

    //构造函数，需要传入根节点
    public MinSubNum(TreeNode root){
        this.root=root;
        min=Integer.MAX_VALUE;
    }

    //获取最小和的子节点
    public TreeNode getMinSubNode(){
        getSum(root);
        return ret;
    }

    private int getSum(TreeNode node){
        if(node==null){
            return 0;
        }
        //分治算法
        int sum=getSum(node.getLeft())+getSum(node.getRight())+node.getVal();
        if(sum<min){
            min=sum;
            ret=node;
        }
        return sum;
    }

}
