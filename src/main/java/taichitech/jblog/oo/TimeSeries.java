package taichitech.jblog.oo;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TimeSeries{
	static final String FROM_DATE = "19700101";
	private static TimeSeries timeSeries = null;

	static {
		Calendar ST = Calendar.getInstance();
		ST.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
	}

	public static TimeSeries getInstance() {
		if (timeSeries == null) {
			timeSeries = new TimeSeries();
		}
		return timeSeries;
	}

	public static void main(String[] args) {
		TimeSeries ts = TimeSeries.getInstance();
		TimeSeriesPoint today = ts.getTimeSeriesPoint("20130101");
		ts.next(today, 5);
		System.out.println(ts);
	}

	private List<TimeSeriesPoint> data;

	private Map<String, TimeSeriesPoint> mapData;

	private TimeSeries() {
		mapData = new TreeMap<String, TimeSeriesPoint>();
	}

	public TimeSeriesPoint getTimeSeriesPoint(int index) {
		return data.get(index);
	}

	public TimeSeriesPoint getTimeSeriesPoint(String date) {
		return mapData.get(date);
	}

	public TimeSeriesPoint next(TimeSeriesPoint timeSeriesPoint, int i) {
		int index = data.indexOf(timeSeriesPoint);
		index = index + i;
		if (index < 0 || index >= data.size()) {
			return null;
		} else {
			return data.get(index);
		}
	}

}
