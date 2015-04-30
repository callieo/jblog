package taichitech.jblog.oo;

public class TimeSeriesPoint {

	private TimeSeriesCalendar x;
	private float y;

	public TimeSeriesPoint next(int i) {
		return this;
	}

	public String getX() {
		return x.toString();
	}

	public void setX(TimeSeriesCalendar x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
