class TreeNode2 {
        int val;
        TreeNode2 left;
        TreeNode2 right;
        TreeNode2(int x) {
            val = x;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    TreeNode dummyHead;
    public TreeNode increasingBST(TreeNode root) {

        dummyHead = new TreeNode(Integer.MIN_VALUE,null,null);

        TreeNode pre = dummyHead;

        middleOrder(root);

        return pre.right;
    }

    public  static  int findString(String[] words, String s) {

        for (int i = 0; i < words.length; i++) {
            if(words[i].equals(s)){
                return i;
            }
        }
        return -1;
    }

    TreeNode2 f;

    public TreeNode2 convertBiNode(TreeNode2 root) {

        f = new TreeNode2(Integer.MIN_VALUE);

        TreeNode2 pre = f;

        middleOrder(root);

        return pre.right;
    }

    private void middleOrder(TreeNode2 root) {
        if(null == root) return;
        middleOrder(root.left);
        change(root);
        middleOrder(root.right);
    }

    private void change(TreeNode2 root) {
        f.right = root;
        root.left = null;
        f = f.right;
    }

    private void middleOrder(TreeNode root) {
        if(null == root) return;
        middleOrder(root.left);
        change(root);
        middleOrder(root.right);
    }

    private void change(TreeNode root) {
        dummyHead.right = root;
        root.left = null;
        dummyHead = dummyHead.right;
    }


    public static void main(String[] args) {


        String a = "aaa";
        String b = new String("aaa");
        System.err.println(a==b);

        Integer c = 6;
        Integer d = new Integer(6);

        System.err.println(c==d);


        Integer e = 6;
        Integer f = 6;

        System.err.println(e==f);

        Integer g = 128;
        Integer h = 128;

        System.err.println(g==h);

        String[] words = new String[]{"at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""};
        String s = "ball";

        int string = findString(words, s);

        System.err.println(string);


        //观察者模式--回调，通知，事件响应！，发布订阅。。。

        
    }
