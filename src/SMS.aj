
public aspect SMS {
	
	pointcut sendSms() : call (void foward(AlertEvent));
	after(): sendSms() {
	        System.out.println(I18N.getString(Messages.SENT_SMS));
	}
}