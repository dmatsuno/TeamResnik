import java.util.Random;

/**
 * This class represents a VOR station object. The Station emits 2 signals, one
 * constant signal as a directional reference, and one variable signal that
 * rotates 360 degrees around the station.
 * 
 * @author Team Resnik
 *
 */
public class Station {
	public static int signalC;

	public Station() {
		signalC = this.signalC;
	}

	public static int getSignal() {
		return signalC;
	}

	/**
	 * Sets the constant directional reference signal
	 * 
	 * @param signal
	 */
	public static void setSignalC(int signal) {
		Station.signalC = signal;
	}

	/**
	 * Sets the sweeping 360 degree variable signal
	 */
	public int setSignalV() {
		int s = 0;
		Random randomSig = new Random();
		s = randomSig.nextInt(360);
		return s;
	}

}
