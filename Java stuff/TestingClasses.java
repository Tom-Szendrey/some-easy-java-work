public class TestingClasses {
	public static void main(String[] args){
		Pizza defaultZa = null;
		Pizza fine = null;
		
		try {
			defaultZa = new Pizza("small",3,1,0);
		} catch (IllegalPizza e) {
			System.out.println("?");
			e.printStackTrace();
		}
		
		try {
			fine = new Pizza("small", 3,4,-1);
		} catch (IllegalPizza e) {
			System.out.println("HELLO TOM");
			e.printStackTrace();
		}
		String y = fine.toString();
		System.out.println(y);
	
		String x = defaultZa.toString();
		System.out.println(x);
		
		boolean z = defaultZa.equals(fine);
		System.out.println(z);
		
	}
	
}
