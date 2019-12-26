import java.util.ArrayList;
import java.util.List;

public class Reminders extends Thread implements Runnable{

	private List<ReminderEvent> reminders;
	private boolean run;


	public Reminders() {
		this.reminders = new ArrayList<>();
		this.run = true;
	};

	public void run() {

		List<ReminderEvent> toRemove = new ArrayList<>();

		while(run) {
			for(ReminderEvent r : reminders) {
				if(r.itsTime()) {
					r.toString();
					r.updateTime();
					if(r.endReminder()) {
						toRemove.add(r);
					}
				}
			}

			reminders.removeAll(toRemove);
			toRemove.removeAll(toRemove);
		}
	}

	public void addReminder(ReminderEvent event) {
		synchronized (reminders) {
			reminders.add(event);				
		}
	}
	
	public void kill() {
		this.run = false;
	}

}