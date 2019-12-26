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
	
	
	/*
	public String toString() {
		return msg + " de " + time.toString().replace('T', '|') + " ate "
				+ time.plusHours(timeFrame).toString().replace('T', '|') 
				+ " de " + timeFrame + " em " + timeFrame + " horas com conhecimento de " + num;
	}
	*/
	
	public String toString() {
		return time.getHour() + ":" + time.getMinute() + " - " + msg;
	}
	
	public void updateTime() {
		if(LocalDateTime.now().isBefore(endTime)) {
			time.plusHours(timeFrame);
		}
	}
	
	public boolean endReminder() {
		return LocalDateTime.now().isAfter(endTime);
	}
	
	public boolean itsTime() {
		return LocalDateTime.now().isAfter(time);
	}

}
