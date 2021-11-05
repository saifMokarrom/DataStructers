import java.util.Scanner;

public class StackImplementation_Lab04 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String s = input.nextLine();
		
		//task1 ---------by using array 
		arrayBasedStack arrayBasedStack = new arrayBasedStack();
		arrayBasedStack.checkParenthesisBalance(s);
		
		System.out.println("------------------------------------------------------");
		
		//task2----------by using linked list
		listBasedStack listBasedStack = new listBasedStack();
		listBasedStack.checkParenthesisBalance(s);

	}

}

class Node {
	public char element;
	public Node next;
	Node(char element, Node next){
		this.element = element;
		this.next = next;
	}
}

//Overflow exception class
class overflow extends Exception{
	public overflow() {
		System.out.println("Can't add. Stack Overflow");
	}
}

//Underflow exception class
class underflow  extends Exception{
	public underflow () {
		System.out.println("Stack Underflow");
	}
}
//-----------------------------------------------Task 1------------------------------------------------------------------------------------------
class arrayBasedStack{
	
	char[] ara;
	int size;
	int[] bracketIdxAra;
	int bracketIdx;
	
	public arrayBasedStack() {
		ara = new char[10];
		size = -1;
		
		bracketIdxAra = new int[10];//it is for keeping an update for which position brackets are not opened or closed.
		bracketIdx = 0;
	}
	
	void push(char ch) {
		try {
			if (size == ara.length) {
				throw new overflow();
			}
			else {
				size++;
				ara[size] = ch;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	char peek() {
		char ch = '0';
		try {
			if (size == -1) {
				throw new underflow();
			}
			else {
				ch = ara[size];
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ch;
		
	}
	
	char pop() {
		char ch= '0';
		try {
			if (size == -1) {
				throw new underflow();
			}
			else {
				ch = ara[size];
				ara[size] = '0';
				size--;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ch;
	}
	
	boolean isEmpty() {
		if (size == -1) return true;
		else return false;
	}
	
	void checkParenthesisBalance(String s ) { //1+2*[3*3+{4–5(6(7/8/9)+10)–11+(12*8)]+14
		char tempObject = '0';
		for (int i=0; i<s.length();i++) {
			if (s.charAt(i) == '(' ||s.charAt(i) == '{' ||s.charAt(i) == '[') {
				push(s.charAt(i));
				bracketIdxAra[bracketIdx] = i+1;
				bracketIdx++;
			}
			else if ((s.charAt(i) == ')' || s.charAt(i) == '}' || s.charAt(i) == ']') && isEmpty()) {
				System.out.println(s);
				System.out.println("This expression is NOT correct.");
				
				System.out.println("Error at character #"+(i+1)+".'"+s.charAt(i)+"'- not opened.");
				return;
			}
			else if(s.charAt(i) == ')' || s.charAt(i) == '}' || s.charAt(i) == ']') {
				tempObject = peek();
				if (s.charAt(i) == ')') {
					if (tempObject == '(') {
						pop();
						bracketIdx--;
					}
					else {
						System.out.println(s);
						System.out.println("This expression is NOT correct.");
						
						if(bracketIdx == 0) System.out.println("Error at character #"+(i+1)+".'"+s.charAt(i)+"'- not closed.");
						else System.out.println("Error at character #"+bracketIdxAra[bracketIdx-1]+".'"+tempObject+"'- not closed.");
						return;
					}
				}
				else if (s.charAt(i) == '}') {
					if (tempObject == '{') {
						pop();
						bracketIdx--;
					}else {
						System.out.println(s);
						System.out.println("This expression is NOT correct.");
						
						if(bracketIdx == 0) System.out.println("Error at character #"+(i+1)+".'"+s.charAt(i)+"'- not closed.");
						else System.out.println("Error at character #"+bracketIdxAra[bracketIdx-1]+".'"+tempObject+"'- not closed.");
						return;
					}
				}
				else if (s.charAt(i) == ']') {
					if (tempObject == '[') {
						pop();
						bracketIdx--;
					}
					else {
						System.out.println(s);
						System.out.println("This expression is NOT correct.");
						
						if(bracketIdx == 0) System.out.println("Error at character #"+(i+1)+".'"+s.charAt(i)+"'- not closed.");
						else System.out.println("Error at character #"+bracketIdxAra[bracketIdx-1]+".'"+tempObject+"'- not closed.");
						return;
					}
				}
			}
		}
		if (isEmpty()) {
			System.out.println(s);
			System.out.println("This expression is correct.");
			return;
			
		}

		else {
			System.out.println(s);
			System.out.println("This expression is NOT correct.");
			
			System.out.println("Error at character #"+bracketIdxAra[bracketIdx-1]+".'"+tempObject+"'- not closed.");
			return;
			
		}
	}
}



//----------------------------------------------------task 2---------------------------------------------------------

class listBasedStack{
	Node head;
	int[] bracketIdxAra;
	int bracketIdx;
	public listBasedStack() {
		head = null;
	}
	
	void push(char ch) {
		if (head == null)
			head = new Node(ch, null);
		else {
			Node newNode = new Node(ch, head);
			head = newNode;
		}
	}
	
	char peek() {
		char ch = '0';
		try {
			if (head == null) {
				throw new underflow();
			}
			else {
				ch = head.element;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ch;
		
	}
	
	char pop() {
		Node removeNode;
		char ch = '0';
		try {
			if (head == null) {
				throw new underflow();
			}
			else {
				removeNode = head;
				head = head.next;
				ch = removeNode.element;
				removeNode.next = null;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ch;
	}
	boolean isEmpty() {
		if (head==null) return true;
		else return false;
	}
	
	void checkParenthesisBalance(String s ) { //1+2*[3*3+{4–5(6(7/8/9)+10)–11+(12*8)]+14
		bracketIdxAra = new int[s.length()];//it is for keeping an update for which position brackets are not opened or closed.
		bracketIdx = 0;
		
		char tempObject = '0';
		for (int i=0; i<s.length();i++) {
			if (s.charAt(i) == '(' ||s.charAt(i) == '{' ||s.charAt(i) == '[') {
				push(s.charAt(i));
				bracketIdxAra[bracketIdx] = i+1;
				bracketIdx++;
			}
			else if ((s.charAt(i) == ')' || s.charAt(i) == '}' || s.charAt(i) == ']') && isEmpty()) {
				System.out.println(s);
				System.out.println("This expression is NOT correct.");
				
				System.out.println("Error at character #"+(i+1)+".'"+s.charAt(i)+"'- not opened.");
				
				return;
			}
			else if(s.charAt(i) == ')' || s.charAt(i) == '}' || s.charAt(i) == ']') {
				tempObject = peek();
				//System.out.println(tempObject);
				if (s.charAt(i) == ')') {
					if (tempObject == '(') {
						pop();
						bracketIdx--;
					}
					else {
						System.out.println(s);
						System.out.println("This expression is NOT correct.");
						
						if(bracketIdx == 0) System.out.println("Error at character #"+(i+1)+".'"+s.charAt(i)+"'- not closed.");
						else System.out.println("Error at character #"+bracketIdxAra[bracketIdx-1]+".'"+tempObject+"'- not closed.");
						return;
					}
				}
				else if (s.charAt(i) == '}') {
					if (tempObject == '{') {
						pop();
						bracketIdx--;
					}else {
						System.out.println(s);
						System.out.println("This expression is NOT correct.");
						
						if(bracketIdx == 0) System.out.println("Error at character #"+(i+1)+".'"+s.charAt(i)+"'- not closed.");
						else System.out.println("Error at character #"+bracketIdxAra[bracketIdx-1]+".'"+tempObject+"'- not closed.");
						return;
					}
				}
				else if (s.charAt(i) == ']') {
					if (tempObject == '[') {
						pop();
						bracketIdx--;
					}
					else {
						System.out.println(s);
						System.out.println("This expression is NOT correct.");
						
						if(bracketIdx == 0) System.out.println("Error at character #"+(i+1)+".'"+s.charAt(i)+"'- not closed.");
						else System.out.println("Error at character #"+bracketIdxAra[bracketIdx-1]+".'"+tempObject+"'- not closed.");
						return;
					}
				}
			}
		}
		if (isEmpty()) {
			System.out.println(s);
			System.out.println("This expression is correct.");
			return;
			
		}

		else {
			System.out.println(s);
			System.out.println("This expression is NOT correct.");
			
			System.out.println("Error at character #"+bracketIdxAra[bracketIdx-1]+".'"+tempObject+"'- not closed.");
			return;
			
		}
	}
}