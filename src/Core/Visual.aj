package Core;
import i18n.I18N;
import i18n.Messages;

public aspect Visual {
	// TODO Auto-generated aspect
	
	after() : call(void java.io.PrintStream.println(..)){
		System.out.print(I18N.getString(Messages.VOICE) + "\n");
	}
}