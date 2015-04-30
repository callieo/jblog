package lab;

import java.text.CollationKey;
import java.text.Collator;

public class Lab {

	public static void main(String[] args) {
		Lab lab=new Lab();
		lab.testCollector();
	}
	public void testCollector(){
		Collator collator = Collator.getInstance();
		CollationKey collationKey = collator.getCollationKey("20140101");
	}

}
