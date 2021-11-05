
import java.util.Scanner;

public class Hashing_Lab07 {

	public static void main(String[] args) {
		
		
		//-------------------------------------1--------------------------------
		int ara[] = {-9,2,-4,3,8,6,4,3};
		KeyIndex keyInd = new KeyIndex(ara);
		System.out.println(keyInd.search(-4));
		int[] a = keyInd.sort();
		for (int i: a) {
			if(i == 0) break;
			System.out.print(i+" ");	
		}
		
		System.out.println();
		//------------------------------------2------------------------------------
		String[] strAra = new String[9];
		Scanner scan=new Scanner(System.in); 
		System.out.println("Enter strings:");
		for (int i=0;i<9;i++) {
			String s;
			s = scan.nextLine();
			strAra[i] = s;
		}
		//String[] strAra = {"ST1E89B8A32", "UV101", "HJA21", "HUO34", "786", "KO4E", "UU", "IHG7645F", "LKJ"};
		HashTable hash = new HashTable();
		String[] hashAra = hash.hashingValueGenerator(strAra);
		for (String i: hashAra) System.out.println(i);

	}

}

//  ----------------------------------------------------1 no---------------------------------------------------------------
class KeyIndex{
	
	int [] k;
	int maxNumber;
	
	public KeyIndex(int[] a){
		
		if (a.length>0) {
			maxNumber = Math.abs(a[0]);
			for (int i=1; i<a.length; i++) {
				if (Math.abs(a[i]) > maxNumber) maxNumber = Math.abs(a[i]);
			}
		}
		
		k = new int[(2*maxNumber)+1];
		
		for (int i=0; i<a.length; i++) {
			k[a[i]+maxNumber]+=1;
		}
		
		
	}
	
	public boolean search(int val) {
		if(k[val+maxNumber] > 0) return true;
		else return false;
	}
	
	public int[] sort() {
		int a[] = new int[k.length];
		int idx = 0;
		for (int i=0; i<k.length; i++) {
			while(k[i] > 0) {
				a[idx] = i-maxNumber; 
				idx++;
				k[i]--;
			}
		}
		return a;
	}
}


//   -------------------------------------2 no-----------------------------------------------------------------
class HashTable{
	
	String[] hashAra;	
	public HashTable() {
		hashAra = new String[9];
	}
	
	public String[] hashingValueGenerator(String[] strAra) {
		int cons = 0, sumDigit = 0;
		for (String s : strAra) {
			for (int ch=0; ch<s.length(); ch++) {
				if (s.charAt(ch) >= '0' && s.charAt(ch)<= '9') {
					int temp = Integer.parseInt(String.valueOf(s.charAt(ch)));  
					sumDigit+=temp;
				}
				else if (s.charAt(ch) != 'A' && s.charAt(ch)!= 'E' && s.charAt(ch)!= 'I' && s.charAt(ch)!= 'O' && s.charAt(ch)!= 'U') cons++;
			}
			int value = ((cons*24)+sumDigit)%9;
	
			linearProbFunc(value,s);	
			cons =0; sumDigit = 0;
		}
		
		return hashAra;
	}
	
	public void linearProbFunc(int value, String s) {
		

		if (hashAra[value] == null) {
			hashAra[value] = s;
			
		}
		else if (hashAra[value] != null){
			int nextPlace = value;
			while (hashAra[nextPlace] != null) {
				nextPlace = (nextPlace+1)%9;
			}
			hashAra[nextPlace] = s;
		}
		
		
		
	}

public int[] id(int[] ara, int mod) {
		int[] hash = new int[18];
		int h;
		for (int i=0;i<ara.length;i++) {
			int value = ara[i]%mod;
			if(value <10) {
			    h = value+8;
			}
			else {
				 h = value-2;
			}
			create(hash, ara[i], h);
		}
		return hash;
		
	}
	public void create(int[] hash, int value, int h) {

		if (hash[h] == 0) {
			hash[h] = value;
			
		}
		else if (hash[h] != 0){
			int nextPlace = h;
			while (hash[nextPlace] != 0) {
				nextPlace = (nextPlace+1)%18;
			}
			hash[nextPlace] = value;
		}
	}
	
}


