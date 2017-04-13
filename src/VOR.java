import java.util.Random;
import java.util.Scanner;

public class VOR {

	public static void main(String[] args) {
		VOR vor = new VOR();
		Station s = new Station();
		int stSignal = s.getSignal();
		
		int obs= vor.getOBS();
		int signal = vor.getSignal();
		
		String typeTF = vor.getToAndFrom(obs,signal);
		
		System.out.println("Obs: " + obs);
		System.out.println("Signal: " + signal);
		System.out.println("To or From: " + typeTF);
		
		boolean signalType = vor.isGoodSignal(0, 0, -9, 50);
		
		System.out.println("Signal Type: " + signalType);
          
      /**      
                System.out.println();
        
                int obs2= vor.getOBS();
                int radio = vor.randomRadio();

                String typeTF2 = vor.getToAndFrom(obs2,radio);

                System.out.println("Obs2: " + obs2);
                System.out.println("Signal2: " + radio);
                System.out.println("To or From (2): " + typeTF2);
	*/
		for (int i = 0; i < 361; i++) {
			for (int j = 0; j < 361; j++) {
				String typeTF3 = vor.getToAndFrom(i,j);
                		System.out.println("(Test) Obs: " + i);
                		System.out.println("(Test) Radio: " + j);
                		String isGood = vor.isGoodSignalD(i, j, "GOOD");
                		System.out.println("(Test) Good or Bad: " + isGood);
                		if (isGood.equals("GOOD")) {
                    			System.out.println("(Test) To or From: " + typeTF3);
               			}
                		System.out.println();
            		}
        	}
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
	 * Change once we get station.
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
	 * The radius of the cone of confusion is 10. 
	 *@return false
	 */
	public boolean isGoodSignal(int xSlocation, int ySlocation, int xPlocation, int yPlocation){
		if( (Math.abs(xSlocation - xPlocation) < 10) || (Math.abs(ySlocation - yPlocation) <10) ) {
			return false; 
		}
		else{
			return true;
		}
	}
	
	public void printVOR(){
		
          
	}
	
	public String isGoodSignalD(int obs, int signal, String isGood) {
		String result = isGood;
		int lowest = 0;
		int highest = 0;
		int range1 = 0;
		int range2 = 0;
		int diff = 0;
		if(obs < 90) { 
		    lowest = (obs - 90) + 360;		
		    highest = obs + 90;

		    if(signal >= lowest || signal <= highest){
			// FROM
			if (obs > signal) {
			    diff = obs - signal;
			    if (diff <= 10) {
				System.out.println(diff + " degree off, to Right");
				return result;
			    } else {
				if (diff >= 89 && diff <= 91) {
				    result = "BAD";
				    return result;
				}
			       System.out.println("More than ten degree off, to Right");
			       return result;
			   }
			} else {
			    if (signal > highest) {
				obs = obs + 360;
				diff = obs - signal;
			    } else {
				diff = signal - obs;
			    }
			   if (diff <= 10) {
				System.out.println(diff + " degree off, to Left");
				return result;
			    } else {
			       if (diff >= 89 && diff <= 91) {
				    result = "BAD";
				    return result;
				}
			       System.out.println("More than ten degree off, to Left");
			       return result;
			   }
			}
		    } else {
			// TO 
			obs = obs + 180;
			if (obs > signal) {
			    diff = obs - signal;
			    if (diff <= 10) {
				System.out.println(diff + " degree off, to Left");
				return result;
			    } else {
				if (diff >= 89 && diff <= 91) {
				    result = "BAD";
				    return result;
				}
			       System.out.println("More than ten degree off, to Left");
			       return result;
			   }
			} else {
			    diff = signal - obs;
			   if (diff <= 10) {
				System.out.println(diff + " degree off, to Right");
				return result;
			    } else {
			       if (diff >= 89 && diff <= 91) {
				    result = "BAD";
				    return result;
				}
			       System.out.println("More than ten degree off, to Right");
			       return result;
			   }
			}
		    }
		} else if(obs >= 90 && obs <= 270) {
		    lowest = obs - 90;
		    highest = obs + 90;

		    if(signal >= lowest && signal <= highest) {
			// FROM
			if (obs > signal) {
			    diff = obs - signal;
			    if (diff <= 10) {
				System.out.println(diff + " degree off, to Right");
				return result;
			    } else {
				if (diff >= 89 && diff <= 91) {
				    result = "BAD";
				    return result;
				}
			       System.out.println("More than ten degree off, to Right");
			       return result;
			   }
			} else {
			   diff = signal - obs;
			   if (diff <= 10) {
				System.out.println(diff + " degree off, to Left");
				return result;
			    } else {
			       if (diff >= 89 && diff <= 91) {
				    result = "BAD";
				    return result;
				}
			       System.out.println("More than ten degree off, to Left");
			       return result;
			   }
			}
		    } else {
			// TO
			obs = obs + 180;
			if (obs > signal) {
			    diff = obs - signal;
			    if (diff <= 10) {
				System.out.println(diff + " degree off, to Left");
				return result;
			    } else {
				if (diff >= 89 && diff <= 91) {
				    result = "BAD";
				    return result;
				}
			       System.out.println("More than ten degree off, to Left");
			       return result;
			   }
			} else {
			    if (signal < lowest) {
				signal = signal + 360;
				diff = signal - obs;
			    } else {
				diff = signal - obs;
			    }
			   if (diff <= 10) {
				System.out.println(diff + " degree off, to Right");
				return result;
			    } else {
			       if (diff >= 89 && diff <= 91) {
				    result = "BAD";
				    return result;
				}
			       System.out.println("More than ten degree off, to Right");
			       return result;
			   }
			}
		    }
		} else {
		    lowest = obs - 90;
		    highest = (obs + 90) - 360;

		    if(signal >= lowest || signal <= highest ) {
			// FROM
			if (obs > signal) {
			    diff = obs - signal;
			    if (diff <= 10) {
				System.out.println(diff + " degree off, to Right");
				return result;
			    } else {
				if (diff >= 89 && diff <= 91) {
				    result = "BAD";
				    return result;
				}
			       System.out.println("More than ten degree off, to Right");
			       return result;
			   }
			} else {
			    if (signal < highest) {
				signal = signal + 360;
				diff = signal - obs;
			    } else {
				diff = signal - obs;
			    }
			    if (diff <= 10) {
				System.out.println(diff + " degree off, to Left");
				return result;
			    } else {
				if (diff >= 89 && diff <= 91) {
				    result = "BAD";
				    return result;
				}
			       System.out.println("More than ten degree off, to Left");
			       return result;
			   }
			}
		    } else {
			// TO
			obs = obs + 180;
			if (obs > signal) {
			    diff = obs - signal;
			    if (diff <= 10) {
				System.out.println(diff + " degree off, to Left");
				return result;
			    } else {
				if (diff >= 89 && diff <= 91) {
				    result = "BAD";
				    return result;
				}
			       System.out.println("More than ten degree off, to Left");
			       return result;
			   }
			} else {
			     diff = signal - obs;
			   if (diff <= 10) {
				System.out.println(diff + " degree off, to Right");
				return result;
			    } else {
			       if (diff >= 89 && diff <= 91) {
				    result = "BAD";
				    return result;
				}
			       System.out.println("More than ten degree off, to Right");
			       return result;
			   }
			}
		    }
		}
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
