public class LinkedList {
	private Node first;
	private int size;
	LinkedList() {
		first = null;
		size = 0;
	}
	LinkedList(Node newnode) {
		first = newnode;
	}
	public boolean IsEmpty() {
		return first==null;
	}
	
	public void Append(Object newelem) {
		Node newnode = new Node();
		newnode.setElement(newelem);
		newnode.setNext(null);
		if (first == null) {
			first = newnode;
		}
		else {
			Node tmp = first;
			while(tmp.GetNext() != null) {
				tmp = tmp.GetNext();
			}
			tmp.setNext(newnode);
		}
		size++;
	}

	public void PrintList() {
		Node tmp = first;
		while (tmp != null) {
			System.out.print(tmp.GetElement()+"-->");
			tmp = tmp.GetNext();
		}
		System.out.println("null");		
	}
	
	public static void main(String[] args) {
		LinkedList link = new LinkedList();
		link.Append(5);
		link.Append(6);
		link.PrintList();
	}

}
