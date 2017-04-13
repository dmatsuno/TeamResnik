import java.util.Random;
import java.util.Scanner;

public class VOR {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		VOR vor = new VOR();
		Station s = new Station();
		int stSignal = s.getSignal();
		
		
		//Grid range
		int gridX = 50;
		int gridY = 30;
		
		//Plane location
		int planeX = (int)(Math.random() * gridX);
		int planeY = (int)(Math.random() * gridY);
		
		
		int option = -1;
		
		while(option != 0 ) {
			System.out.println("Type the number and enter to chose your option.");
			System.out.println("0) Exit");
			System.out.println("1) Print Grid");
			System.out.println("2) Change Plane Location");
			System.out.println("3) Get VOR");
			
			option = scan.nextInt();
					
			
			if( option == 0 ) {
				System.out.println("You exited the program.");		
			} 
			else if (option == 1) {
			//grid
				for(int y = 0; y <= gridY; y++){
					for( int x = 0; x <= gridX; x++) {
						if(x == (gridX/2) && y == (gridY/2)){
							System.out.print("S");
						} else if (x == planeX && y == planeY) {
							System.out.print("P");
						} else {
							System.out.print(".");
						}
					}
					System.out.println("");
				}
				System.out.println("");
			}
			else if(option == 2){
			//change plane location
				System.out.println("Planes new X coordinate:");
				planeX = scan.nextInt();
				System.out.println("Planes new Y coordinate:");
				planeY = scan.nextInt();
			}
			else if (option == 3) {
				int obs= vor.setOBS();
				//int signal = vor.setSignal();
		
				
				String typeTF = vor.getToAndFrom(obs,signal);
		
				System.out.println("Obs: " + obs);
				System.out.println("Signal: " + signal);
				System.out.println("To or From: " + typeTF);
		
				boolean signalType = vor.isGoodSignal(0, 0, -9, 50);
		
				System.out.println("Signal Type: " + signalType);
			} 
			else {
				System.out.println("The option you chose does not exist. Please enter again.");
			}
			
			
		
		}
		
		
          
	
	//Test signal
	//vor.testSignal();
		
	}
	
	
	/**
	 * Asks directly what obs settings are from pilot through console.
	 */
	public int setOBS(){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Which way are you headed?");
		int obs = scan.nextInt();

		return obs;
	}
	
	/**
	 * Asks directly what the radio signal is through console.
	 * Change once we get station.
	 */
	public int setSignal(){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What is your signal setting?");
		int signal = scan.nextInt();
		
		return signal;
	}
	
	/**
	 * Checks if it is a to or from signal.
	 */
	public String getToAndFrom(int out, int psignal){
		int lowest = 0;
		int highest = 0;
		
		if(out < 90){ 
			lowest = (out - 90) + 360;		
			highest = out + 90;
			
			if(psignal >= lowest || psignal <= highest){
				return "FROM";
			}
			else {
				return "TO";
			}
		}
		else if(out >= 90 && out <= 270){
			lowest = out - 90;
			highest = out + 90;
			
			if(psignal >= lowest && psignal <= highest){
				return "FROM";
			}
			else {
				return "TO";
			}
		}
		else{
			lowest = out - 90;
			highest = (out + 90) - 360;
			
			if(psignal >= lowest || psignal <= highest ){
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
        
        public void testSignal(){
        VOR vor = new VOR();
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

	
}
