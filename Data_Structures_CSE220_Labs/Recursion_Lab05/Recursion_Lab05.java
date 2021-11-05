

class Node {
	public int element;
	public Node next;
	Node(int element, Node next){
		this.element = element;
		this.next = next;
	}
}
public class Recursion_Lab05 {
	
	public static void main(String[] args) {
		//-------------------1-------------------------------------------------------
		//a
		System.out.println("Factorial of 7: "+factorial(7));
		//b
		System.out.println("Fibonacci of 5th: "+fibonacci(5));
		//c
		int[] ara = {10,20,30,40,50};
		System.out.println("array printing recursively:");
		printArray(ara, 0);
		//d
		System.out.println("Power of Number: "+powerN(2, 3));
		
		//--------------2-------------------------------------------------------------------
		//a
		System.out.println("Decimal to binary: ");
		String binary = decimalToBinaryConverter(7);
		for (int i = binary.length()-1; i>=0; i--)
			System.out.print(binary.charAt(i));
		System.out.println();
		
		
		Node head = null;
		Node tail = null;
		for (int i=0; i<ara.length;i++) {
			Node node = new Node(ara[i], null);
			if (head == null) {
				head = node;
				tail = node;
			}
			else {
				tail.next = node;
				tail = node;
			}
		}
		//b
		System.out.println("Total of list: "+addList(head));
		//c
		System.out.println("Reversing the list: ");
		printReverseList(head);
		
		
		//--------------------3----------------------------------------------------------------
		System.out.println("Hocbuilder: "+hocBuilder(3));
		
		//-------------------4---------------------------------------------------------------
		//a
		rowsPrinting(5);
		//b
		rowsPrinting2(5,5);
		System.out.println();
		//--------------------5--------------------------------------------------------------------
		int[]array={25000,100000,250000,350000};
		FinalQ.print(array, 0);
	}
	
	
	
	
	//---------------1(a)------------------------------------------------------------------------
	public static int factorial(int number) {
		if (number == 0) {
			return 1;
		}
		return number * factorial(number-1);
	}
	
	//---------------1(b)---------------------------------------------------------------------------
	static int fibonacci (int number) {
		if (number == 0) return 0;
		else if (number == 1) return 1;
		return fibonacci(number-1) + fibonacci(number-2);
	}
	
	//---------------1(c)--------------------------------------------------------------------------
	static void printArray(int[] ara,int size) {
		if (ara.length == size) return ;
		System.out.println(ara[size]);
		printArray(ara, size+1);
		
	}
	
	//-----------------1(d)--------------------------------------------------------------------------
	static int powerN(int base, int pow) {
		if (pow == 0) return 1;
		return base * powerN(base, pow-1);
	}
	
	//--------------------------------2(a)------------------------------------------------------------
	static String decimalToBinaryConverter(int number) {
		String binary = "";
		if (number == 0) return binary;
		binary+=Integer.toString(number % 2);
		return binary+ decimalToBinaryConverter(number/2);
	}
	
	//--------------------------------2(b)------------------------------------------------------------
	static int addList(Node head) {
		int sum=0;
		if (head == null) return sum;
		sum+=head.element;
		return sum+addList(head.next);
	}
	
	//--------------------------------2(c)------------------------------------------------------------
	static void printReverseList(Node head) {
		if (head == null) {
			return ;
		}
		printReverseList(head.next);
		System.out.println(head.element);
	}
	
	//--------------------------------3------------------------------------------------------------
	public static int hocBuilder (int height){ 
		int sum = 0;
		if (height == 0) {
			return 0;
		}
		else if (height == 1) {
			return 8;
		}
		sum+=5;
		return sum+hocBuilder(height-1);
	}
	//-----------------------------4(a)---------------------------------------------------------------
	public static void  rowsPrinting(int rows) {
		if (rows == 0) {
			return;
		}
		rowsPrinting(rows-1);
		System.out.println();
		coloumnPrinting(rows);
	}
	
	public static void coloumnPrinting(int row) {
		if (row == 0) {
			return;
		}
		coloumnPrinting(row-1);
		System.out.print(row+" ");
	}
	
	//-----------------------------4(b)---------------------------------------------------------------
	public static void  rowsPrinting2(int rows, int rows2) {
		if (rows == 0) {
			return;
		}
		rowsPrinting2(rows-1,rows2);
		System.out.println();
		spacePrint(rows2-rows);
		coloumnPrinting2(rows);
	}
	
	public static void spacePrint(int row) {
		if (row == 0) {
			return;
		}
		spacePrint(row-1);
		System.out.print("  ");
		
	}
	
	public static void coloumnPrinting2(int row) {
		if (row == 0) {
			return;
		}
		coloumnPrinting(row-1);
		System.out.print(row+" ");
	}
}


//-------------------------------------5----------------------------------------------------------------
class FinalQ{ 
	public static void print(int[]array,int idx){ 
		if(idx<array.length){ 
			double profit=calcProfit(array[idx]); 
			System.out.println("Investment: "+array[idx]+"; Profit: "+profit);
			print(array, idx+1);
		}
		
	} 
	public static double calcProfit(int investment){
		double profit = 0;
		if (investment==25000) {
			return 0;
		}
		else if (investment>100000) {
			profit = (investment-100000)/(100/8.0);
			investment = 100000;
		}
		else {
			profit = (investment-25000)/(100/4.5);
			investment = 25000;
		}
		
		return profit+calcProfit(investment);
	}
}

