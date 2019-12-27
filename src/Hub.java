import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

public class Hub {

	private static Reminders reminders;
	private static Map<String, String> contacts = new HashMap<>();;
	private static List <Class<? extends Event>> test = new ArrayList<>();

	public static void main(String[] args) {

		reminders = new Reminders();
		reminders.start();
		new Hub();
		operations();
		
	}


	public Hub() {

		BezirkMiddleware.initialize();
		final Bezirk bezirk = BezirkMiddleware.registerZirk("Hub");
		System.err.println(I18N.getString(Messages.START_HUB));

		final Class<? extends Event>[] events = getAlerts(test);

		//final EventSet setEvents = new EventSet(events);

		final EventSet setEvents = new EventSet(AlertEvent.class,  MovimentoUpdateEvent.class);//TODO

		setEvents.setEventReceiver(new EventSet.EventReceiver() {

			@Override
			public void receiveEvent(Event event, ZirkEndPoint sender) {
				//in case we need to do specific stuff
				if (event instanceof ReminderEvent) {
					ReminderEvent reminder = (ReminderEvent) event;
					//do stuff
				}

				//print event message
				System.out.println(event.toString());


				/*
				//TODO
				if(event instanceof AlertEvent) {
					AlertEvent alert = (AlertEvent) event;
					System.err.println(alert.toString());
				}else if(event instanceof MovimentoUpdateEvent) {
					MovimentoUpdateEvent movimento = (MovimentoUpdateEvent) event;
					System.err.println(movimento.toString());
				}else if(event instanceof ReminderEvent) {
					ReminderEvent reminder = (ReminderEvent) event;
					reminders.addReminder(reminder);
				}*/

			}
		});

		bezirk.subscribe(setEvents);
	}

	public void addEvent(Class<? extends Event> class1) {
		test.add(class1);

	}

	@SuppressWarnings("unchecked")
	private Class<? extends Event>[] getAlerts(List<Class<? extends Event>> test) {
		Class<? extends Event>[] result = new Class[test.size()];
		for(int i = 0; i < test.size() ; i++) {
			result[i] = test.get(i);
		}
		return result;
	}

	private static void operations() {

		Scanner sc = new Scanner(System.in);

		while(true) {
			try {
				System.out.println("Selecione uma das seguintes possiveis operações:\nAviso\nContacto");
				String input = sc.nextLine().toLowerCase();
				if(input.equals("aviso")) {
					aviso(sc);
				}else if(input.equals("contacto")){
					contacto(sc);
				}else {
					System.out.println("Tente novamente");
				}
			}catch(Exception e) {
				System.out.println("Inseriu algum dado errado, tente novamente");
			}
		}
	}

	private static void contacto(Scanner sc) {

		System.out.println("Indique o nome do contacto");
		String name = sc.nextLine();
		System.out.println("Insira o numero telefonico do contacto");
		String number = sc.nextLine();

		if(!contacts.get(name).equals(number)) {
			contacts.put(name, number);
			System.out.println(I18N.getString(Messages.CONTACT_GOOD));
		}else {
			System.out.println(I18N.getString(Messages.CONTACT_BAD));
		}
		

	}

	private static void aviso(Scanner sc) {

		System.out.println(I18N.getString(Messages.INSERT_MSG));
		String msg = sc.nextLine();
		System.out.println(I18N.getString(Messages.INSERT_DATE));
		String time = sc.nextLine();
		System.out.println(I18N.getString(Messages.INSERT_DATE));
		String endTime = sc.nextLine();
		System.out.println(I18N.getString(Messages.INSERT_RATE));
		int timeFrame = sc.nextInt();

		reminders.addReminder(new ReminderEvent(msg, "112", time, endTime, timeFrame));
		System.err.println(I18N.getString(Messages.SENT_REMINDER) + " 112");

	}

}
