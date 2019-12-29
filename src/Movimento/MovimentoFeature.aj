package Movimento;

import Core.Menu;
import Core.Option;
import i18n.I18N;
import i18n.Messages;
import Core.Product;
import Events.MovimentoUpdateEvent;

public aspect MovimentoFeature {

	MovimentoAssistantZirk maz = new MovimentoAssistantZirk();
	
	after(Menu menu): initialization(Menu.new(..)) && target(menu) {
		Option exit = new Option(I18N.getString(Messages.MVM_FEATURE));
		menu.addMenuItem(exit, (i) -> { maz.changePatterns(menu.getSc()); return i;});
		maz.checkInactivity(45, 9, 20, 23, 7);
	}
	
	before() : execution(public static void main(String[])) && within(Product) {
        Product.addEvent(MovimentoUpdateEvent.class);
    }
}
