package Events;
import com.bezirk.middleware.messages.Event;

public class PulsacaoEvent extends Event{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int heartRate /* integer, e.g., 79 */;
	
	public PulsacaoEvent(int heartRate) {
		this.heartRate = heartRate;
	}

	public int getHeartRate() {
		return heartRate;
	}

	@Override
	public String toString() {
		return String.format("HeartRate: %s", heartRate);
    }
}
