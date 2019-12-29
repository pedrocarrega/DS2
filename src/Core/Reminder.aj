package Core;
import Core.Product;
import Events.ReminderEvent;

public aspect Reminder {

	before() : execution(public static void main(String[])) && within(Product) {
        Product.addEvent(ReminderEvent.class);
    }
	
}
