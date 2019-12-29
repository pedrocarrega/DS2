package Events;
import java.time.LocalDateTime;

import com.bezirk.middleware.messages.Event;

public class ReminderEvent extends Event{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int timeFrame;
	private LocalDateTime time;
	private LocalDateTime endTime;
	private String msg;
	private String num;
	
	public ReminderEvent(String msg, String num, String time, String endTime, int timeFrame) {
		this.msg = msg;
		this.num = num;
		this.time = LocalDateTime.parse(time);
		this.endTime = LocalDateTime.parse(endTime);
		this.timeFrame = timeFrame;
	}
	
	public String toString() {
		return time.getHour() + ":" + time.getMinute() + " - " + msg;
	}
	
	public void updateTime() {
			time = LocalDateTime.now().plusMinutes(timeFrame);
	}
	
	public boolean endReminder() {
		return LocalDateTime.now().isAfter(endTime);
	}
	
	public boolean itsTime() {
		return time.isBefore(LocalDateTime.now());
	}
	
	public String getMsg() {
		return this.msg;
	}

}
