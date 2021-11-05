
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

public class Sorting_Lab06 {

	public static void main(String[] args) {
		System.out.println("Selection sort:");
		int [] ara = {2,5,6,3,8};
		selectionOuter(ara,ara.length-1);
		for (int i : ara) {
			System.out.println(i);
		}
		System.out.println("Insertion sort");
		//2
		int [] ara1 = {2,5,6,3,8};
		insertionOuter(ara1,0);
		for (int i : ara1) {
			System.out.println(i);
		}
		System.out.println("Singly Linked list bubble sort:");
		//3
		int [] ara2 = {2,5,6,3,8};
		Node head = createList(ara2);
		int count = 0;
		for (Node n = head; n!=null; n=n.next) {
			count++;
		}
		bubbleOuter(head, count, 0);
		for (Node n = head; n!=null; n=n.next) {
			System.out.println(n.element);
		}
		System.out.println("Singly Linked list selection sort:");

		//4
		int [] ara3 = {2,5,6,3,8};
		Node head1 = createList(ara3);
		listSelectionSort(head1);
		for (Node n = head1; n!=null; n=n.next) {
			System.out.println(n.element);
		}
		

		//6
		int [] ara5 = {10,15,20,25,30};
		int searchingFor = 30;
		int search = binaryRecursion(ara5, 0, ara5.length-1, searchingFor);
		if (search == -1) System.out.println("No number like this");
		else System.out.println("Binary search "+searchingFor+" found at "+search);

		//7
		System.out.println("Fibonacci:");
		int findFib = 39;
		int [] memo = new int[findFib+1];
		int fibAns = nthFibRecursion(findFib, memo);
		System.out.println(fibAns);

	}
	//1
	public static void selectionOuter(int[] ara, int idx) {
		if (idx==0) {
			return;
		}
		int maxIdx = idx;
		recursionSelection(ara,maxIdx,idx,0);
		selectionOuter(ara, idx-1);
	}

	public static void recursionSelection(int[] ara, int maxIdx, int idx, int startIdx) {

		if (startIdx > idx) {
			int temp = ara[idx];
			ara[idx] = ara[maxIdx];
			ara[maxIdx] = temp;
			return;  
		}
		else if (ara[startIdx] > ara[maxIdx]) {
			maxIdx = startIdx;
		}
		recursionSelection(ara,maxIdx,idx,startIdx+1);
	}

	//2
	public static void insertionOuter(int[] ara, int idx) {
		if (idx == ara.length) return;
		recursionInsertion(ara, idx-1);
		insertionOuter(ara, idx+1);
	}

	public static void recursionInsertion(int[] ara, int idx) {
		if (idx < 0) return;
		else if(ara[idx] > ara[idx+1]) {
			int temp = ara[idx+1];
			ara[idx+1] = ara[idx];
			ara[idx] = temp;
		}

		recursionInsertion(ara, idx-1);
	}


	//3
	public static Node createList(int[] ara) {
		Node head = null,tail = null;
		for (int i=0; i<ara.length;i++) {
			Node temp = new Node(ara[i],null,null);
			if (head == null) {
				head = temp;
				tail = temp;
			}
			else {
				tail.next = temp;
				tail = temp;
			}
		}
		return head;
	}

	public static void bubbleOuter(Node head, int count, int start) {
		if (start == count-1) return;
		recursionBubble(head);
		bubbleOuter(head, count, start+1);   

	}
	public static void recursionBubble(Node head) {

		if (head.next == null) return;
		else if (head.element > head.next.element) {
			int temp = head.next.element;
			head.next.element = head.element;
			head.element = temp;
		}

		recursionBubble(head.next);
	}

	//4
	public static void listSelectionSort(Node head) {
		if (head == null) return;

		else {
			Node minNode = head;
			for (Node n = head.next; n!=null; n=n.next) {
				if (minNode.element > n.element) minNode = n;
			}
			int tempEle = minNode.element;
			minNode.element = head.element;
			head.element = tempEle;
		}

		listSelectionSort(head.next);
	}

	//5


	//6
	public static int binaryRecursion(int[] ara, int firstIdx, int lastIdx, int search ) {

		int middle = (firstIdx+lastIdx)/2;
		if(firstIdx>lastIdx) return -1;
		else {

			if ( ara[middle] < search ) firstIdx = middle + 1;     
			else if( ara[middle] > search) lastIdx = middle - 1;  
			else if ( ara[middle] == search ) return middle;
		}

		return binaryRecursion(ara,firstIdx,lastIdx,search);
	}

	//7
	public static int nthFibRecursion(int nth, int[] memoiz) {
		if (memoiz[nth] > 0 )return memoiz[nth];
		if (nth == 2 || nth == 1) return memoiz[nth] = 1;
		else {
			memoiz[nth] = nthFibRecursion(nth-1, memoiz) + nthFibRecursion(nth-2,memoiz);
			return memoiz[nth];
		}
	}

}



