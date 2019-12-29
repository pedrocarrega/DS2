package Core;
import Core.Product;
import Events.LightEvent;
import i18n.I18N;
import i18n.Messages;

public aspect Luz {
	
	after() : call(void java.io.PrintStream.println(..)){
		System.out.print(I18N.getString(Messages.LIGHT) +"\n");
	}
	
	before() : execution(public static void main(String[])) && within(Product) {
        Product.addEvent(LightEvent.class);
    }
	
	

}
