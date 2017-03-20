import java.util.Scanner;

public class VOR {

	public static void main(String[] args) {
		VOR vor = new VOR();
		vor.getOBS();
	}
	
	
	/**
	 * Asks directly what obs settings are from pilot through console.
	 */
	public void getOBS(){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What is your obs setting?");
		int obs = scan.nextInt();
		
		System.out.println("Your obs is " + obs);
		
		scan.close();
	}
	
	/**
	 * Asks directly what the radio signal is through console.
	 */
	public void getSignal(){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What is your signal setting?");
		int signal = scan.nextInt();
		
		System.out.println("Your signal is " + signal);
		
		scan.close();
	}
	
	/**
	 * If the plane is directly above the station then it will be a bad signal and will 
	 *@return false
	 */
	public boolean isGoodSignal(){
		return false; 
	}

}
