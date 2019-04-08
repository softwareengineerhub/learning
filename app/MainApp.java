

public class MainApp {
	
	public static void main(String[] args){
		String name = args[0];
		System.out.println("Hello, "+name);
		String value=System.getProperty("key");
		System.out.println("key= "+value);
	}
	
}
