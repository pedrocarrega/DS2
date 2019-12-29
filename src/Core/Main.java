package Core;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.bezirk.middleware.messages.Event;

import Events.ReminderEvent;
import i18n.Messages;
import i18n.I18N;
public class Main {

	private static Reminders reminders;
	private static Map<String, String> contacts;
	private static List <Class<? extends Event>> listEvents;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		contacts = new HashMap<>();
		listEvents = new ArrayList<>();
		reminders = new Reminders();
		reminders.start();

		//Operações de alertas e adicionar contactos
		operations2();
	}
	
	private static void operations2() {
		
		Scanner sc = new Scanner(System.in);
		
		Menu menu = new Menu(sc);
		Option exit = new Option(I18N.getString(Messages.EXIT));
		Option contacto = new Option(I18N.getString(Messages.CTC_OP));
		Option aviso = new Option(I18N.getString(Messages.SET_WRNG));
		Option removeAviso = new Option(I18N.getString(Messages.RM_OP));
		menu.addMenuItem(exit, (i) -> {return i;});
		menu.addMenuItem(contacto, (i) -> {contacto(sc); return i;});
		menu.addMenuItem(aviso, (i) -> {aviso(sc); return i;});
		menu.addMenuItem(removeAviso, (i) -> {removeAviso(sc); return i;});
		
		int optionNum;
		Option option;

		do {
			System.out.println(menu);
			optionNum = readOption(menu, sc);
			option = menu.getOption(optionNum);
			try{
				menu.execute(optionNum);
			}catch(Exception e) {
				System.out.println(I18N.getString(Messages.SOMETHING_WRONG));
			}
		}while(!option.equals(exit));
		
	}
	
	private static int readOption(Menu menu, Scanner sc) {
		int value;
		do {
			System.out.println("Option? ");
			value = Integer.parseInt(sc.nextLine()); 
			if (!menu.isValid(value))
				System.out.println("Value not valid.");			
		} while(!menu.isValid(value));
		return value;
	}

	private static void operations() {

		Scanner sc = new Scanner(System.in);

		while(true) {
			try {
				System.out.println(I18N.getString(Messages.OPERATIONS));
				String input = sc.nextLine().toLowerCase();
				if(input.equals(I18N.getString(Messages.SET_WRNG))) {
					aviso(sc);
				}else if(input.equals(I18N.getString(Messages.CTC_OP))){
					contacto(sc);
				}else if(input.equals(I18N.getString(Messages.RM_OP))){
					removeAviso(sc);
				}else {
					System.out.println(I18N.getString(Messages.TRY_AGN));
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println(I18N.getString(Messages.SOMETHING_WRONG));
			}
		}
	}

	private static void contacto(Scanner sc) {

		System.out.println(I18N.getString(Messages.NAME_CTC));
		String name = sc.nextLine();
		System.out.println(I18N.getString(Messages.NUM_CTC));
		String number = sc.nextLine();

		if(!contacts.containsKey(name) || !contacts.get(name).equals(number)) {
			synchronized(contacts) {
				contacts.put(name, number);
			}
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
		int timeFrame = Integer.parseInt(sc.nextLine());

		reminders.addReminder(new ReminderEvent(msg, "112", time, endTime, timeFrame));
		System.err.println(I18N.getString(Messages.SENT_REMINDER) + " 112");

	}

	private static void removeAviso(Scanner sc) {

		if(!reminders.isEmpty()) {
			int index = -1;
			System.out.println(I18N.getString(Messages.REMOVE_MSG));
			System.out.println(reminders.activeReminders());
			while(index == -1 && !reminders.isEmpty()) { //caso os reminders fiquem vazios a meio do remove
				index = Integer.parseInt(sc.nextLine());
				index = reminders.remove(index);
			} 
			
			System.out.println(I18N.getString(Messages.REMOVE_GOOD));
		}else {
			System.out.println(I18N.getString(Messages.REMOVE_BAD));
		}
	}

}
