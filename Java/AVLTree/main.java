
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVLTree<Integer> tree = new AVLTree<Integer>();
		Integer a = Integer.valueOf(3);
		System.out.println(tree.isEmpty());
		try {
			tree.getRoot1() = tree.insert(a);
			System.out.println(tree.getRoot1());
			System.out.println(tree.search(a));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
