import java.util.Hashtable;

public class LinkedList {
	private Node first;
	private int  size;
	LinkedList(){
		first = null;
		size = 0;
	}
	
	LinkedList(Node newnode) {
		first = newnode;
		size = 1;
	}
	
	LinkedList(Object e) {
		first = new Node(e);
		size  = 1;
	}
	
	boolean IsEmpty () {
		return size == 0;
	}
	
	public void Append (Node n) {
		if (this.IsEmpty()) {
			first = n;
			size ++;
			return;
		}
		else {
			Node tmp = first;
			while (tmp.next != null) {
				tmp = tmp.next;
			}			
			tmp.next = n;
			n.next = null;
			size ++;
		}	
	}
	
	public void Append (Object e) {
		Node n = new Node(e);
		Append(n);
	}
	
	
	public void PrintList() {
		Node tmp = first;
		while (tmp != null) {
			System.out.print(tmp.element+"->");
			tmp = tmp.next;
		}
		System.out.println("null");
	}
	
	// Assure the list is at least size 2
	// It uses hashtable as buffer
	public void Removedup1 () {
		Hashtable<Object, Integer> table = new Hashtable<Object, Integer>();
		Node prev = first;
		table.put(prev.element, 1);
		while (prev.next != null) {
			Node curr = prev.next;
			if (!table.containsKey(curr.element)) {
				table.put(curr.element, 1);
				prev = prev.next;
			}
			// a duplicate found
			else {
				prev.next = curr.next;
			}
		}
	}

	// Without hashtable as buffer
	public void Removedup2 () {
		Node curr = first;
		while (curr != null) {
			Node runner = curr;
			while(runner.next != null) {
				if (runner.next.element == curr.element) {
					// .next.next Paradigm, we don't need prev pointer
					runner.next = runner.next.next;
				}
				else {
					runner = runner.next;
				}
			}
			curr = curr.next;
		}
	}
	

}






















