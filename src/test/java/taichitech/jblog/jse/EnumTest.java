package taichitech.jblog.jse;

import lab.Week;

public class EnumTest {
	public static void main(String[] args) {
		Enum e1 = Enum.valueOf(Week.class, "DAY1");
		Enum e2 = Week.valueOf("DAY1");
		System.out.println(e1 == e2);
		System.out.println(e1.equals(e2));

	}

}
