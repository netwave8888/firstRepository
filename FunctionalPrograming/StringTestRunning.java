package com.netwave;

public class StringTestRunning {
	
	// inside JShell, define a string then do: "str. tab" will display all the methods 
	// java doc String java 8 to find out all the available methods. 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "Test";
		String str1 = new String("Test");
		String biggerString = "This is a lot of text";
		
		str.charAt(0);
		biggerString.substring(5); // inclusive and exclusive
		System.out.println(
				biggerString.indexOf("lot"));
		
		System.out.println(
				biggerString.lastIndexOf("t"));
		System.out.println(
				biggerString.startsWith("This") 
				+ " Ends with: " 
						+ biggerString.endsWith("text"));
		System.out.println(
				str.equalsIgnoreCase("test"));
		str.concat(biggerString);
		System.out.println(str1 == str);
		
		String str2 = String.join(",", "T", "A", "O");
		
		str2 = str2.replace("T", "Tao");
		
		System.out.println(str2);

	}

}
