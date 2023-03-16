package lab02;

import java.util.Scanner;

public class String_test {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		
		String[] input_arr = input.split(",");
		String name = input_arr[0];
		String file_name = input_arr[1].replace("ppt", "pdf").trim();
	
		String[] name_arr = name.split(" ");
		int name_length = name_arr.length;
		String last_name = name_arr[name_length - 1];
		
		System.out.println("Name Length(Korean) : " + name_length);
		for (int i = 0; i < name_length - 1; i++) {
			System.out.print(name_arr[i].toUpperCase().charAt(0) + ".");
		}
		System.out.print(last_name.toUpperCase().charAt(0) + last_name.substring(1,last_name.length()));
		System.out.print(" submitted ");
		System.out.println(file_name.toUpperCase().charAt(0) + file_name.substring(1, file_name.length()));
		
	}

}

