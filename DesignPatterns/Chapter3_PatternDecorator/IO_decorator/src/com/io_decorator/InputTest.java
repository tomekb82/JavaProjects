package com.io_decorator;

import java.io.*;


public class InputTest {

	public static void main(String args[]){

		int c;
		
		try{
			
			InputStream in = new FileInputStream("/tmp/test.txt");
			
			while ((c = in.read()) >=0){
				System.out.println((char)c);
			}
			in.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
