package Core;
import java.util.ArrayList;
import java.util.List;

import Events.ReminderEvent;

public class Reminders extends Thread implements Runnable{

	private List<ReminderEvent> reminders;
	private boolean run;
	private List<ReminderEvent> toRemove;


	public Reminders() {
		this.reminders = new ArrayList<>();
		toRemove = new ArrayList<>();
		this.run = true;
	};

	public void run() {

		while(run) {
			synchronized (reminders) {
				for(ReminderEvent r : reminders) {
					if(r.itsTime()) {
						System.out.println(r.toString());
						r.updateTime();
						if(r.endReminder()) {
							toRemove.add(r);
						}
					}
				}
			}

			if(!toRemove.isEmpty()) {

				reminders.removeAll(toRemove);
				toRemove.removeAll(toRemove);
			}
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
	
	public String activeReminders() {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < reminders.size(); i++) {
			sb.append(i + " - " + reminders.get(i).getMsg() + "\n");
		}
		
		return sb.toString();
	}

	public boolean isEmpty() {
		return reminders.isEmpty();
	}

	public int remove(int index) {
		ReminderEvent r = reminders.get(index);
		toRemove.add(reminders.get(index));

		return r!=null ? index : -1;
	}

}