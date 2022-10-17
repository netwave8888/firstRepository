package com.netwave;

public class StringBufferTestRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("Tao"); // multi threading. A bit slow
		sb.append(" Xu");
		System.out.println(sb);
		
		StringBuilder sbld = new StringBuilder("Alice");
		sbld.append(", Michelle");
		System.out.println(sbld);

	}

}
