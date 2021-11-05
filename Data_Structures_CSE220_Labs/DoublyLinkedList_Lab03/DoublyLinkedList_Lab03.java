
public class DoublyLinkedList_Lab03 {

	public static void main(String[] args) {
		//----------------------------TASK 2------------------------------------------------------------
		//1
		int[] ara = {10,20,30,40,50,40};
		DoublyList doublyList = new DoublyList(ara);
		Node head = doublyList.head;
		
		//2
		System.out.println("Showing List: ");
		doublyList.showList();

		//3
		doublyList.insert(new Node(60, null, null));
		System.out.println("After inserting a node at the tail:");
		doublyList.showList();
		
		//4
		doublyList.insert(25, 2);
		System.out.println("After inserting a node at a certain index:");
		doublyList.showList();
		
		//5
		doublyList.remove(0);
		System.out.println("After removing a node at a certain index:");
		doublyList.showList();
		
		//6
		int deletedValue = doublyList.removeKey(40);
		System.out.println("After removing "+deletedValue+" from the list:");
		doublyList.showList();
	}

}
//Task 1---(i)
class Node {
	public int element;
	public Node next;
	public Node prev;
	Node(int element, Node next, Node prev){
		this.element = element;
		this.next = next;
		this.prev = prev;
	}
}

//Task 1----(ii)
class DoublyList{
	Node head = new Node(0, null,null);
	
	//---------------------------------------------------------Task 2---------------------------------------------------
	//1
	public DoublyList(int[] a) {
		Node tail = null;
		head.next = head.prev = head;
		if (a.length == 0)
			System.out.println("Can not add to list");
		else {
		    for (int i=0; i<a.length;i++) {
		    	boolean checker = true;
	    		for (Node n=head.next; n!=head; n=n.next) {
	    			if (n.element == a[i]) {
	    				checker = false;
	    				break;
	    			}
	    		}
	    		if (checker) {
	    			Node temp = new Node(a[i],null,null);
			    	if (head.next == head) {
			    		head.next = temp;
		    			head.prev = temp;
		    			temp.next = head;
		    			temp.prev = head;
		    			tail = temp;
			    	}
			    	else {
			    		tail.next=temp;
		    			temp.prev = tail;
		    			temp.next = head;
		    			head.prev = temp;
		    			tail = temp;
		    		}
	    		}
		    	
		    }
		   
		}
		
	}
	
	//2
	void showList() {
		int cnt = 0;
		for(Node n = head.next; n!=head; n=n.next) {
			cnt++;
		}
		if(cnt == 0) {
			System.out.println("Empty list"); 
			return;
		}
		for(Node n = head.next; n!=head; n=n.next) {
			System.out.println(n.element);
		}
	}
	
	//3
	void insert(Node newElement) {
		Node lastNode = null;
		boolean exist = false;
		
		for(Node n=head.next; n!=head; n=n.next) {
			if (newElement.element == n.element) {
				System.out.println("the key already exists");
				exist = true;
				break;
			}
			else if (n.next == head){
				lastNode = n;
			}
		}
		if (!exist) {
			lastNode.next = newElement;
			newElement.prev = lastNode;
			newElement.next = head;
			head.prev = newElement;
		}
	}
	
	//4
	void insert(int element, int index) {
		int cnt = 0;
		for(Node n=head.next; n!=head; n=n.next) {
			cnt++;
		}
		if(cnt > 0) {
			Node newNode = new Node(element, null,null);
			if (index == 0) {
				newNode.next = head.next;
				newNode.prev = head;
				head.next.prev = newNode;
				head.next = newNode;
				
			}
			else if (index > 0 && index < cnt ) {
				int c = 0;
				for(Node n=head.next; n!=head; n=n.next) {
					if (c == index - 1) {
						newNode.next = n.next;
						newNode.prev = n;
						n.next.prev = newNode;
						n.next = newNode;
					}
					c++;
				}
			}
		}
	}
	
	//5
	void remove(int index) {
		Node deleteNode = null;
		int cnt = 0;
		for(Node n=head.next; n!=head; n=n.next) {
			cnt++;
		}
		if(cnt > 0) {
			if (index == 0) {
				deleteNode = head.next;
				head.next = head.next.next;
			}
			else {
				int c = 0;
				for(Node n=head.next; n!=head; n=n.next) {
					if (c == index - 1) {
						deleteNode = n.next;
						n.next = n.next.next;
						n.next.prev = n;
					}
					c++;
				}
			}
			deleteNode = null;
		}
	}
	
	//6
	int removeKey(int deleteKey) {
		Node deleteNode = null;
		int cnt = 0;
		for(Node n=head.next; n!=head; n=n.next) {
			cnt++;
		}
		if(cnt > 0) {
			int idx = 0;
			for(Node n=head.next; n!=head; n=n.next) {
				if (n.element == deleteKey) {
					break;
				}
				else {
					idx++;
				}
			}
			if(idx == 0) {
				deleteNode = head.next;
				head.next = head.next.next;
			}else {
				int count = 0;
				for(Node n=head.next; n!=head; n=n.next) {
					if (count == idx - 1) {
						deleteNode = n.next;
						n.next = n.next.next;
						n.next.prev = n;
					}
					count++;
				}
			}
		}
		return deleteNode.element;
	}
}