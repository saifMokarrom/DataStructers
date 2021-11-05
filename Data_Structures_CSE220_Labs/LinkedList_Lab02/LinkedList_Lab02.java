

public class LinkedList_Lab02 {

	public static void main(String[] args) {
		
		
		//----------------------------TASK 2------------------------------------------------------------
		System.out.println("-----------------TASK 2--------------");
		//1
		int[] ara = {20,30,50,40,10,60,60};
		MyList myList = new MyList(ara);
		Node head = myList.head;
		
		//2
		System.out.println("Showing List: ");
		myList.showList();
		
		//3
		System.out.println("\n"+ myList.isEmpty());
		
		
		//4
		myList.clear();
		
		
		
		//5
		System.out.println("\nAfter Inserting at last:");
		head = myList.insert(new Node(70, null)); 	
		for(Node n = head; n!=null; n=n.next) {
			System.out.println(n.element);
		}
		
		//6
		head = myList.insert(5,0);
		System.out.println("\nAfter Inserting at given index:");
		for(Node n = head; n!=null; n=n.next) {
			System.out.println(n.element);
		}
		
		//7
		System.out.println("\nAfter Removing an element:");
		head = myList.remove(60);
		for(Node n = head; n!=null; n=n.next) {
			System.out.println(n.element);
		}
		
		
		
		
		
		//--------------------------------TASK 3-----------------------------------------------------------
		System.out.println("\n\n-----------------TASK 3--------------");
		
		//1
		myList.evenNumber(head);
		
		//2
		System.out.print("is element available in list: ");
	    System.out.println(myList.isElementinList(myList.head, 40));
	    
		//3
		head = myList.reverse(head);
		System.out.println("\nReversing:");
		for(Node n=head; n!=null; n=n.next) {
			System.out.println(n.element);
		}
		
		//4
		head = myList.sort(head);
		System.out.println("\nAfter Sorting:");
		for(Node n = head; n!=null; n=n.next) {
			System.out.println(n.element);
		}
		
		//5
		int sum = myList.summation(head);
		System.out.println("\nSummation:");
		System.out.println(sum);
		
		//6
		head = myList.rotate(head, "right", 2);
		System.out.println("\nAfter Rotating:");
		for (Node n = head; n!=null; n=n.next) {
			System.out.println(n.element);
		}
		
	}
	

}

//Task1 (i)
class Node {
	public int element;
	public Node next;
	Node(int element, Node next){
		this.element = element;
		this.next = next;
	}
}


//Task 1 (II)
class MyList {
	
	Node head = null;
	//---------------------------------------------------------Task 2---------------------------------------------------
	//1
	MyList(int [] a) {
		Node tail = null;
		if (a.length == 0)
			System.out.println("Can not add to list");
		else {
		    for (int i=0; i<a.length;i++) {
		    	boolean checker = true;
	    		for (Node n=head; n!=null; n=n.next) {
	    			if (n.element == a[i]) {
	    				checker = false;
	    				break;
	    			}
	    		}
	    		if(checker) {
	    			Node temp = new Node(a[i],null);
			    	if (head == null) {
			    		head = temp;
			    		tail = temp;
			    	}
			    	else {
			    		tail.next = temp;
			    		tail = temp;
			    	}
	    		}
	    		
		    }
		}
	}
	
	
	//2
	void showList() {
		int count = 0;
		for(Node n = head; n!=null; n=n.next) {
			count++;
		}
		
		if (count == 0) System.out.println("Empty list"); 
		else {
			for(Node n = head; n!=null; n=n.next) {
				System.out.println(n.element);
			}
		}
	}
	
	//3
	boolean isEmpty() {
		int count = 0;
		for(Node n = head; n!=null; n=n.next) {
			count++;
			if (count == 1)
				return false;
		}
		return true;
	}
	
	
	//4
	void clear() {
		int count = 0;
		for(Node n = head; n!=null; n=n.next) {
			count++;
		}
		
		if (count > 0) {
			Node tempNode = head;
			while (tempNode!=null) {
				Node remNode = tempNode;
				tempNode = tempNode.next;
				remNode.element = 0;
				remNode.next = null;
			}
		}
		System.out.println("\nClearing Done!!!!!");
	}
	
	//5
	Node insert(Node newElement) {
		Node lastNode = null;
		boolean keyExist = false;
		
		for(Node n=head; n!=null; n=n.next) {
			if (newElement.element == n.element) {
				System.out.println("the key already exists");
				keyExist = true;
				break;
			}
			else if (n.next == null){
				lastNode = n;
			}
		}
		if (!keyExist) {
			Node newNode = newElement;
			lastNode.next = newNode;
		}
		return head;
	}
	
	
	//6
	Node insert(int element, int index) {
		int count = 0;
		for(Node n=head; n!=null; n=n.next) {
			count++;
		}
		if(count > 0) {
			if (index == 0) {
				Node newNode = new Node(element, null);
				newNode.next = head;
				head = newNode;
				
			}
			else if (index > 0 && index < count ) {
				int c = 0;
				for(Node n=head; n!=null; n=n.next) {
					if (c == index - 1) {
						Node newNode = new Node(element, null);
						newNode.next = n.next;
						n.next = newNode;
					}
					c++;
				}
			}
		}
		
		return head;
	}
	
	//7
	Node remove(int deleteKey) {
		Node deleteNode=null;
		if (!isEmpty()) {
			int index = 0;
			for(Node n=head; n!=null; n=n.next) {
				if (n.element == deleteKey) {
					break;
				}
				else {
					index++;
				}
		
			}
			if(index == 0) {
				deleteNode = head;
				head = head.next;
			}else {
				Node predNode = nodeAt(index-1);
				deleteNode = predNode.next;
				predNode.next = deleteNode.next;
			}
			
		}
		return head;
	}
	//created nodeAt to use above
	Node nodeAt(int index) {
		int count = 0;
		for(Node n=head; n!=null; n=n.next) {
			if (count == index) {
				return n;
			}
			count++;
		}
		return null;
	}
	
	
	//------------------------------------------------------------TASK3----------------------------------------------
	//1
	void evenNumber(Node head) {
		Node startNode = null, tailNode = null;
		for(Node n=head; n!=null; n=n.next) {
			if (n.element % 2 == 0) {
				Node newNode = new Node(n.element, null);
				if (startNode == null) {
					startNode = newNode;
					tailNode = newNode;
				}else {
					tailNode.next = newNode;
					tailNode = newNode;
				}
			}
		}

		System.out.println("\nEven Numbers from linked list:");
		for(Node n=startNode; n!=null; n=n.next) {
			System.out.println(n.element);
		}
	}
	
	//2
	boolean isElementinList(Node head, int element) {
		for(Node n=head; n!=null; n=n.next) {
			if (n.element == element) {
				return true;
			}
		}
		return false;
	}
	
	//3
	Node reverse(Node head) {
		Node curNode = head, prevNode = null, nextNode = null;
		while (curNode != null) {
			nextNode = curNode.next;
			curNode.next = prevNode;
			prevNode = curNode;
			curNode = nextNode;
		}
		head = prevNode;
		return head;
	}
	
	//4
	Node sort(Node head) {
		int count = 0;
		for (Node n = head; n!=null; n=n.next) {
			count++;
		}
		for(int i=0; i<count;i++) {
			for (Node n = head; n.next!=null; n=n.next) {
				if (n.element > n.next.element) {
					int temp = n.next.element;
					n.next.element = n.element;
					n.element = temp;
				}	
			}
		}
		return head;
	}
	
	//5
	int summation(Node head) {
		int sum = 0;
		for (Node n = head; n!=null; n=n.next) {
			sum+=n.element;
		}
		return sum;
	}
	
	//6
	Node rotate(Node head, String dir, int k) {
		
		if (dir == "left") {
			for(int i=0; i<k; i++) {
				Node tempHeadNode = head;
				head = head.next;
				tempHeadNode.next = null;
				for (Node n = head; n!=null; n=n.next) {
					if (n.next == null) {
						n.next = tempHeadNode;
						break;
					}
				}
			}
		}
		else if (dir == "right") {
			for(int i=0; i<k; i++) {
				Node tempHeadNode = head;
				for (Node n = head; n!=null; n=n.next) {
					if (n.next.next == null) {
						Node tobefirst = n.next;
						tobefirst.next = tempHeadNode;
						n.next = null;
						head = tobefirst;
					}
				}
			}
		}
		
		return head;
	}
}