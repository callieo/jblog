package taichitech.jblog.oo;

public class TimeSeriesCalendar implements Comparable<TimeSeriesCalendar> {
	private long date;
	private String expression;

	public String toString() {
		return expression;
	}

	@Override
	public int compareTo(TimeSeriesCalendar ts) {
		if (this.date > ts.date) {
			return 1;
		} else if (this.date < ts.date) {
			return -1;
		} else {
			return 0;
		}
	}

}