package javaTester;


public class Topic_02_And_Or {
	
	public static void main(String[] args) {
		boolean a = true;
		boolean b = false;
		boolean c = true;
		boolean d = false;
		
		//AND
		//TRUE & TRUE = TRUE		
		System.out.println(a & c);
		
		//TRUE & FALSE = FALSE
		System.out.println(a & b);	
		
		//FALSE & TRUE = FALSE
		System.out.println(b & c);
		
		//FALSE & FALSE =FALSE
		System.out.println(b & d);
		
		//OR
		//TRUE & TRUE = TRUE		
		System.out.println(a | c);
		
		//TRUE & FALSE = TRUE
		System.out.println(a | b);	
		
		//FALSE & TRUE = TRUE
		System.out.println(b | c);
		
		//FALSE & FALSE =FALSE
		System.out.println(b | d);
	}

}
