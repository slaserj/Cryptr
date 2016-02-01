import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Cryptr {
	public static void main(String[] args) throws IOException{
		System.out.println("Welcome to Cryptr");
		System.out.println("1) Encrypt a message");
		System.out.println("2) Decrypt a message");
		Scanner kb = new Scanner(System.in);
		int x = kb.nextInt();
		switch(x) {
			case 1: encryptUX();
			case 2: decryptUX();
		}
		kb.close();
	}
	public static void encryptUX() throws IOException{
		System.out.print("Enter an input file name: ");
		Scanner kb = new Scanner(System.in);
		String s = kb.nextLine();
		File in = new File(s);
		System.out.print("Enter an output file name: ");
		s = kb.nextLine();
		File out = new File(s);
		System.out.print("Enter a key file name: ");
		s = kb.nextLine();
		File key = new File(s);
		encryptToFile(encrypt(getMessage(in),getKey(key)),out);
		kb.close();
	}
	public static void decryptUX() throws IOException{
		System.out.print("Enter an input file name: ");
		Scanner kb = new Scanner(System.in);
		String s = kb.nextLine();
		File in = new File(s);
		System.out.print("Enter an output file name: ");
		s = kb.nextLine();
		File out = new File(s);
		System.out.print("Enter a key file name: ");
		s = kb.nextLine();
		File key = new File(s);
		decryptToFile(decrypt(getEncrypted(in),getKey(key)),out);
		kb.close();
	}
	public static ArrayList<Integer> getEncrypted(File x) throws IOException{
		ArrayList<Integer> output = new ArrayList<Integer>();
		Scanner in1 = new Scanner(x);
		while(in1.hasNextInt()){
			output.add(in1.nextInt());
		}
		in1.close();
		return output;
	}
	public static ArrayList<Character> getMessage(File x) throws IOException{
		ArrayList<Character> readIn = new ArrayList<Character>();
		Scanner in1 = new Scanner(x);
		while(in1.hasNext()){
			String text = in1.next();
			for(int i = 0; i < text.length(); i++){
				readIn.add(text.charAt(i));
			}
			if(in1.hasNext()){
				readIn.add(' ');
			}
		}
		in1.close();
		return readIn;
	}
	public static ArrayList<Character> getKey(File x) throws IOException{
		ArrayList<Character> readIn = new ArrayList<Character>();
		Scanner in1 = new Scanner(x);
		while(in1.hasNext()){
			String text = in1.next();
			for(int i = 0; i < text.length(); i++){
				readIn.add(text.charAt(i));
			}
		}
		in1.close();
		return readIn;
	}
	public static ArrayList<Integer> encrypt(ArrayList<Character> x, ArrayList<Character> y){
		int keysize = y.size();
		int j = 0;
		Integer a, b, c;
		ArrayList<Integer> out = new ArrayList<Integer>();
		for(int i = 0; i < x.size(); i++){
			if(j == keysize){
				j = 0;
			}
			a = (int)x.get(i);
			b = (int)y.get(j);
			j++;
			c = a^b;
			out.add(c);
		}
		return out;
	}
	public static void encryptToFile(ArrayList<Integer> a, File b) throws IOException{
		FileOutputStream fos = new FileOutputStream(b);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		for(int i = 0; i < a.size(); i++){
			bw.write(a.get(i).toString());
			bw.newLine();
		}
		bw.close();
	}
	public static void decryptToFile(String a, File b) throws IOException{
		FileOutputStream fos = new FileOutputStream(b);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.write(a);
		bw.close();
	}
	public static String decrypt(ArrayList<Integer> x, ArrayList<Character> y){
		int keysize = y.size();
		int j = 0;
		Integer a, b, c;
		int z;
		String out = "";
		for(int i = 0; i < x.size(); i++){
			if(j == keysize){
				j = 0;
			}
			a = x.get(i);
			b = (int)y.get(j);
			j++;
			c = a^b;
			z = c;
			out += (char)z;
		}
		return out;
	}

}
