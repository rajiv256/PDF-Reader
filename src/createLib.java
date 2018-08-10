import java.io.*;

import javax.swing.*;
public class createLib extends Window{

	private static String home = "/home/rajiv/Desktop/Calibre" ;
	
	
	public static void makeLibrary() {
		
		File lib = new File(home);
		if (!lib.exists()){
			lib.mkdir();
		}
	}
	
	public static void makeBook(String s) throws IOException {
		
		String[] tokens = s.split("\\.|/");
		String[] file   = s.split("/");
		String fileName = file[file.length-1];
		String bookName = tokens[tokens.length-2];
		
		File book = new File(home+"/"+bookName);
		
		if (!book.exists()){
		
			book.mkdir();
		}
		
		String dest = new String();
		dest = dest.concat(home);
		dest = dest.concat("/");
		dest = dest.concat(bookName);
		dest = dest.concat("/");
		dest = dest.concat(fileName);
	
		System.out.println(dest);
		System.out.println("came");
		copyFile(s,dest);
		
		return;
	}
	
	public static void copyFile(String source,String dest) throws IOException{
		
		FileReader fr = new FileReader(source);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter(dest);
		BufferedWriter bw = new BufferedWriter(fw);
		String line = new String();
		line = br.readLine();
		while (line != null){
			bw.write(line);
			line = br.readLine();
		}
		
		
	}
	
	public static void main(String[] args) {
		makeLibrary();
		String a = "/home/rajiv/Desktop/Harry Potter and the Sorcerer's Stone.pdf";
		try {
			makeBook(a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}
