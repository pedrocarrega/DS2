package Alert;
import Core.Product;
import Events.AlertEvent;
import i18n.I18N;
import i18n.Messages;

public aspect AlertFeature {

	before(): call(* *.operations2()){
		new AlertAssistantZirk();
	} 

	before() : execution(public static void main(String[])) && within(Product) {
        Product.addEvent(AlertEvent.class);
    }
	
	after() : call(void foward(AlertEvent)){
		System.out.print(I18N.getString(Messages.SENT_SMS) + "\n");
	}

}
