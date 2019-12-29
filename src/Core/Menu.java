package Core;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Menu {
	
	private List<Option> options;
	private Map<Option, Function> actions;
	private Scanner sc;
	
	public Menu(Scanner sc){
		options = new ArrayList<>();
		actions = new HashMap<>();
		this.sc = sc;
	}

	public void addMenuItem(Option o, Function f){
		if(!actions.containsKey(o)) {
			options.add(o);
		}
		actions.put(o, f);
	}
	
	public Option getOption(int i) {
		return options.get(i);
	}
	
	public void execute(int i) {
		Option o = options.get(i);
		actions.get(o).apply(0);//ma pratica mas para testar
	}
	
	public boolean isValid(int pos) {
		return pos >= 0 && pos < options.size();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for(Option o : options) {
			sb.append(" " + i + " - " + o.toString() + " ");
			i++;
		}
		return sb.toString();
	}
	
	public Scanner getSc() {
		return sc;
	}
	
}
