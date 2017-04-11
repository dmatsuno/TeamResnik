import java.util.Random;
import java.util.Scanner;

public class VOR {

	public static void main(String[] args) {
		VOR vor = new VOR();
		
		int obs= vor.getOBS();
		int signal = vor.getSignal();
		
		String typeTF = vor.getToAndFrom(obs,signal);
		
		System.out.println("Obs: " + obs);
		System.out.println("Signal: " + signal);
		System.out.println("To or From: " + typeTF);
                
                System.out.println();
        
                int obs2= vor.getOBS();
                int radio = vor.randomRadio();

                String typeTF2 = vor.getToAndFrom(obs2,radio);

                System.out.println("Obs2: " + obs2);
                System.out.println("Signal2: " + radio);
                System.out.println("To or From (2): " + typeTF2);
	}
	
	
	/**
	 * Asks directly what obs settings are from pilot through console.
	 */
	public int getOBS(){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What is your obs setting?");
		int obs = scan.nextInt();

		return obs;
	}
	
	/**
	 * Asks directly what the radio signal is through console.
	 */
	public int getSignal(){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What is your signal setting?");
		int signal = scan.nextInt();
		
		return signal;
	}
	
	/**
	 * Checks if it is a to or from signal.
	 */
	public String getToAndFrom(int obs, int signal){
		int lowest = 0;
		int highest = 0;
		
		if(obs < 90){ 
			lowest = (obs - 90) + 360;		
			highest = obs + 90;
			
			if(signal >= lowest || signal <= highest){
				return "FROM";
			}
			else {
				return "TO";
			}
		}
		else if(obs >= 90 && obs <= 270){
			lowest = obs - 90;
			highest = obs + 90;
			
			if(signal >= lowest && signal <= highest){
				return "FROM";
			}
			else {
				return "TO";
			}
		}
		else{
			lowest = obs - 90;
			highest = (obs + 90) - 360;
			
			if(signal >= lowest || signal <= highest ){
				return "FROM";
			}
			else {
				return "TO";
			}
		}		
	}
	
	/**
	 * If the plane is directly above the station then it will be a bad signal and will 
	 *@return false
	 */
	public boolean isGoodSignal(){
		return false; 
	}
        
        /**
        * Create a fake random radio
        */
        public int randomRadio() {
            int result = 0;
            Random randNum = new Random();
            result = randNum.nextInt(360);
//          System.out.println(result);
            return result;
        }

}
