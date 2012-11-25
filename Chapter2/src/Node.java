
public class Node {

	public Object element;
	public Node next;
	Node(Object e) {
		this.element = e;
		this.next = null;
	}
	Node() {
		this.element = null;
		this.next    = null;
	}
}

