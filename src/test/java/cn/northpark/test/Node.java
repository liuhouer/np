package cn.northpark.test;



/**
 * @author yang zhang
 * 
 * 说明生活中遇到的二叉树，用java实现二叉树
 * 这是组合设计模式。
 * 我有很多个(假设10万个)数据要保存起来，
 * 以后还需要从保存的这些数据中检索是否存在某个数据，
 * （我想说出二叉树的好处，该怎么说呢？那就是说别人的缺点），
 * 假如存在数组中，那么，碰巧要找的数字位于99999那个地方，
 * 那查找的速度将很慢，因为要从第1个依次往后取，取出来后进行比较。
 * 平衡二叉树（构建平衡二叉树需要先排序，我们这里就不作考虑了）可以很好地解决这个问题，
 * 但二叉树的遍历（前序，中序，后序）效率要比数组低很多 
 *
 */
public class Node {

	public int value;
	public Node left;
	public Node right;

	
	
	/**
	 * 二叉树存储
	 * @param value
	 */
	public void store(int value) {
		if (value < this.value) {
			if (left == null) {
				left = new Node();
				left.value = value;
			} else {
				left.store(value);
			}
		} else if (value > this.value) {
			if (right == null) {
				right = new Node();
				right.value = value;
			} else {
				right.store(value);
			}
		}
	}

	/**
	 * 二叉树查找、遍历
	 * @param value
	 * @return
	 */
	public boolean find(int value) {
		System.out.println("happen " + this.value);
		if (value == this.value) {
			return true;
		} else if (value > this.value) {
			if (right == null)
				return false;
			return right.find(value);
		} else {
			if (left == null)
				return false;
			return left.find(value);
		}
	}

	public void preList() {
		System.out.print(this.value + ",");
		if (left != null)
			left.preList();
		if (right != null)
			right.preList();
	}

	public void middleList() {
		if (left != null)
			left.preList();
		System.out.print(this.value + ",");
		if (right != null)
			right.preList();
	}

	public void afterList() {
		if (left != null)
			left.preList();
		if (right != null)
			right.preList();
		System.out.print(this.value + ",");
	}
	
	
	

	public static void main(String[] args) {
		int[] data = new int[20];
		for (int i = 0; i < data.length; i++) {
			data[i] = (int) (Math.random() * 100) + 1;
			System.out.print(data[i] + ",");
		}
		System.out.println();

		Node root = new Node();
		root.value = data[0];
		for (int i = 1; i < data.length; i++) {
			root.store(data[i]);
		}

		root.find(data[19]);

		root.preList();
		System.out.println();
		root.middleList();
		System.out.println();
		root.afterList();
	}
}
