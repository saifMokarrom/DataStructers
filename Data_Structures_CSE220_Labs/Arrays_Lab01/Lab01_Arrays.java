

public class Lab01_Arrays {

	public static void main(String[] args) {
		//1
		int[] source = {10,20,30,40,50,60};
		shiftLeft(source,3);
		System.out.println("1 no:");
		for (int x:source) System.out.println(x);
		
		//2
		int[] source2 = {10,20,30,40,50,60};
		rotateLeft(source2,3);
		System.out.println("\n2 no:");
		for (int x:source2) System.out.println(x);
		
		//3
		int[] source3 = {10,20,30,40,50,0,0};
		remove(source3,5,2);
		System.out.println("\n3 no:");
		for (int x:source3) System.out.println(x);
		
		//4
		int[] source4 = {10,2,30,2,50,2,2,60,0,0};
		removeAll(source4,8,2);
		System.out.println("\n4 no:");
		for (int x:source4) System.out.println(x);
		
		//5
		int[] source5={2, 1, 1, 2, 1};
		System.out.println("\n5 no:");
		System.out.println(isBalanced(source5));
		
		//6
		int[] ara = pattern(4);
		System.out.println("\n6 no:");
		for (int x: ara) System.out.println(x);
		
		//7
		int[] source7 = {2,3,3,3,4,4,4,4,5,4,6};
		System.out.println("\n7 no:");
	        System.out.println(largestBunch(source7));
	    
	        //8
	        int[] source8 = {3,4,6,3,4,7,4,6,8,6,6};
	        System.out.println("\n8 no:");
	        System.out.println(isRepeated(source8));
	    
	       //CIRCULAR ARRAY
	       //1
	    
	       int[] circ1 = {10,20,0,0,0,10,20,30};
	       System.out.println("\nCircular array 1 no:");
	       System.out.println(isPalindrome(circ1,5,5));
	    
	       //2
	       int[] circFirst = {40,50,0,0,0,10,20,30};
	       int[] circSecond = {10,20,5,0,0,0,0,0,5,40,15,25};
	       int [] intersectionArray = intersection(circFirst, 5, 5, circSecond, 8, 7);
	       System.out.println("\nCircular array 2 no:");
	       for (int x:intersectionArray) if (x != 0) {
	           System.out.println(x);
	       }
	    
	       //3
	       System.out.println("\nCircular array 3 no:");
	       String[] chair = {"Saif", "Niloy", "Shouvik"};
	       System.out.println(musicalChair(chair,0,3));
	}

	//1
	public static void shiftLeft(int[] s, int k) {
		// [ 40, 50, 60, 0, 0, 0 ]
		for (int i=s.length-1;i>=k;i--) {
			s[i-k] = s[i];
			s[i]=0;
		}
	}
	
	//2
	public static void rotateLeft(int[] s,int k) {
		// [ 40, 50, 60, 10, 20, 30]
		for (int i=s.length-1;i>=k;i--) {
			int temp = s[i-k];
			s[i-k] = s[i];
			s[i] = temp;
		}
	}
	
	//3
	public static void remove(int[] s, int size, int idx) {
		// [ 10,20,40,50,0,0,0]
		if (idx>=0 &&  idx<size) {
			for (int i=idx;i<size;i++) {
				s[i] = s[i+1];
			}
		}
	}
	
	//4
	public static void removeAll(int[] s, int size, int elm) {
		// [10,2,30,2,50,2,2,60,0,0]
		// [ 10,30,50,60,0,0,0,0,0,0]
		int i =0;
		while(s[i] != 0) {
			if (s[i] == elm) {
				for (int j=i;j<size;j++) {
					s[j] = s[j+1];
				}
			}
			else i++;
		}
	}
	
	
	//5
	public static boolean isBalanced(int[] s){
	    int lsum=0, rsum=0;

	    for (int i=1;i<s.length;i++){
	      for (int j=0;j<i;j++){
	        lsum+=s[j];
	      }
	      for (int j=i;j<s.length;j++){
	        rsum+=s[j];
	      }
	      if (lsum == rsum) return true;
	      else{
	        lsum=0;
	        rsum=0;
	      }
	    }
	    return false;
	}
	
	//6
	public static int[] pattern(int n) {
		int[] s = new int[n*n];
		int idx = 0;
		int zero = n-1;
		int nonZero = n-zero;
		for (int i=0;i<n;i++) {
			int value = i+1;
			for (int j=0;j<zero;j++) {
				s[idx] = 0;
				idx++;
			}
			for (int j=0;j<nonZero;j++) {
				s[idx] = value;
				idx++;
				value--;
			}
			zero--;
			nonZero++;
		}
		return s;
	}
	
	//7
	public static int largestBunch(int[] s){
	    int prevValue = s[0];
	    int tempCount = 1;
	    int larCount = 1;
	    for (int i=1; i<s.length; i++){
	      if (prevValue == s[i]){
	    	  tempCount++;
	      }
	      else{
	        if (tempCount > larCount){
	          larCount = tempCount;
	        }
	        tempCount = 1;
	        prevValue = s[i];
	      }
	    }
	    return larCount;
	}
	
	//8
	public static boolean isRepeated(int s[]) {
		//4,5,6,6,4,3,6,4
		int[] temp = new int[s.length];
		int[] finCount = new int[s.length];
		int count = 0;
		for (int i=0;i<s.length;i++) {
			if (temp[i] == 0) {
				temp[i] = 1;
				count++;
				for (int j=i+1;j<s.length;j++) {
					if (s[i] == s[j]) {
						temp[j] = 1;
						count++;
					}
				}
				finCount[i] = count;
				count = 0;
			}
		}
		for (int i=0; i<finCount.length-1; i++) {
			for (int j=i+1; j<finCount.length; j++) {
				if (finCount[i] == finCount[j] && finCount[i] !=1 && finCount[j] != 1 && finCount[i] !=0 && finCount[j] !=0) {
					return true; //Checking that 0 and 1 should not count for repetation.  
				}
			}
		}
		return false;
	}
	
	
	//Circular array 1
	public static boolean isPalindrome(int[] circ, int start, int size) {
		//20,10,0,0,0,10,20,30
		int idxI = start;
		int idxJ = (start+size-1)%circ.length;
		
 		for (int i = 0; i<= size/2; i++) {
			if (circ[idxI] == circ[idxJ]) {
				idxI = (idxI+1) % circ.length;
				idxJ = idxJ -1;
				if (idxJ < 0) {
					idxJ = circ.length-1;
				}
			}
			else return false;
		}
 		return true;
	}
	
	//Circular array 2
	public static int[] intersection(int[] circ1, int start1, int size1, int[] circ2, int start2, int size2) {
		//40,50,0,0,0,10,20,30] (start_1 =5, size_1 =5)
	    //[10,20,5,0,0,0,0,0,5,40,15,25] (start_2=8, size_2 =7)
		System.out.println("\n");
		
		int smallSize, largeSize, smallIdx, largeIdx, finalLargeIdx, smallLen, largeLen;
		if (size1 >= size2) {
			smallSize = size2;
			largeSize = size1;
			smallIdx = start2;
			largeIdx = start1;
			finalLargeIdx = start1;
			smallLen = circ2.length;
			largeLen = circ1.length;
		}
		else {
			smallSize = size1;
			largeSize = size2;
			smallIdx = start1;
			largeIdx = start2;
			finalLargeIdx = start2;
			smallLen = circ1.length;
			largeLen = circ2.length;
		}
		int[] temp = new int[smallSize];
		int linIdx = 0;
		for (int i=0; i<smallSize; i++) {
			for (int j=0; j<largeSize; j++) {
				if (circ1[smallIdx] == circ2[largeIdx]) {
					temp[linIdx] = circ1[smallIdx];
					linIdx++;
					//System.out.println("circ1 : "+ smallIdx+ " "+ circ1[smallIdx]+ "\ncirc2: "+largeIdx+" "+ circ2[largeIdx]);
					break;
				}
				else {
					largeIdx = (largeIdx+1) % largeLen;
				}
			}
			smallIdx = (smallIdx+1) % smallLen;
			largeIdx = finalLargeIdx;
			
		}
		
		return temp;
		
	}
	
	
	//Circular Array 3
	public static String musicalChair(String[] s, int start, int size) {
		String[] copyS = new String[s.length];
		int totSize = size;
		while (size!=1) {
			int ran = (int)(Math.random()*(3-0+1)+0);
			//System.out.println(ran+"\n\n");
			int idx = start;
			if (ran == 0 || ran == 2 || ran == 3) {
				for (int i=0; i<totSize; i++) {
					copyS[(idx+1)%s.length] = s[idx];
					idx = (idx+1) % s.length;
					//System.out.println(idx);
				}
				int idxTemp = start;
				for (int i =0;i<totSize;i++) {
					s[idx] = copyS[idx];
					idx = (idx+1) % s.length;
				}
				
			}
			else {
				int rem = start + (size/2) % s.length;
				for (int i = 0; i<(totSize-rem-1); i++) {
					s[rem] = s[(rem+1)%s.length];
					//if (s[(rem+1)%s.length] != "0")
					s[(rem+1)%s.length] = "0";
					rem = (rem+1) % s.length;
				}
				for (String x:s) System.out.println(x);
				size--;
				//System.out.println(size+"\n");
			}
		}
		return s[0];
	}
}