
import java.text.NumberFormat;
import java.util.Scanner;

public class newCalculator {
	
	final static float percent = 0.01f;
	final static byte months = 12;
	
	public static void main(String[] args) {
		
		int principal = (int)readNumber("Principal($1k - $1M): ", 1000, 1_000_000);
		float intrest = (float) readNumber("Intrest Rate: ", 0, 30);
		byte years = (byte) readNumber("Period(In Years): ", 1, 30);
		
		printMortgage(principal, intrest, years);
		paymentSchedule(principal, intrest, years);
	}

	private static void printMortgage(int principal, float intrest, byte years) {
		double mortgage = calculateMortgage(principal, intrest, years);
		String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
		System.out.println("Mortgage Payment : " + mortgageFormatted);
		System.out.println("--------------------");
	}

	private static void paymentSchedule(int principal, float intrest, byte years) {
		System.out.println();
		System.out.println("Paymemt Schedule");
		System.out.println("--------------------");
		
		for(short month = 1; month <= years * months; month++) {
			double balance = readBalance(principal, intrest, years, month);
			System.out.println(NumberFormat.getCurrencyInstance().format(balance));
		}
	}
	
	public static double readNumber(String prompt, int min, int max) {
		Scanner scanner = new Scanner(System.in);
		double value;
		
		while (true) {
			System.out.print(prompt);
			value = scanner.nextInt();
			if (value >= min && value <= max) 
				break;
			else
				System.out.println("Enter a number between " + min + " and " + max);
			scanner.close();
		}
		return value;
	}
	
	public static double readBalance(int principal, float intrest, byte years, short numberOfPaymentsMade) {
		
		float intrestInMonths = (intrest / months) * percent;
		short years_to_months = (short)(years * months);
		
		double balance = principal
				* (Math.pow(1 + intrestInMonths, years_to_months) - Math.pow(1 + intrestInMonths, numberOfPaymentsMade)) 
				/ ((Math.pow(1 + intrestInMonths, years_to_months) - 1));
		return balance;
	}

	public static double calculateMortgage(int principal, float intrest, byte years) {
		float intrestInMonths = (intrest / months) * percent;
		short years_to_months = (short)(years * months);
		
		double x = Math.pow(1 + intrestInMonths, years_to_months);
		double mortgage = principal * intrestInMonths * x / (x - 1);
		
		return mortgage;
	}
}