package monPremierProjetMaven;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDates {
	
	public static Date date= new Date();
	
	public static void main(String[] args) {
		
		System.out.println(new SimpleDateFormat("dd/MM/YY").format(date));
		System.out.println();
		System.out.println(new SimpleDateFormat("dd/MM/YYYY").format(date));
		System.out.println();
		System.out.println(new SimpleDateFormat("dd/MM/YYYY HH:mm").format(date));
		System.out.println();
		System.out.println(new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(date));
		System.out.println();
		System.out.println(new SimpleDateFormat("dd MMMM YYYY").format(date));
		System.out.println();
		System.out.println(new SimpleDateFormat("E dd/MM/YY").format(date));
		System.out.println();
		System.out.println(new SimpleDateFormat("EEEE dd MMMM YYYY").format(date));
		System.out.println();
		System.out.println(new SimpleDateFormat("EEEE dd MMMM YYYY HH:mm").format(date));
		System.out.println();
		System.out.println(new SimpleDateFormat("EEEE dd MMMM YYYY HH:mm:ss").format(date));
		System.out.println(new SimpleDateFormat("").format(date));
	}
	

}
