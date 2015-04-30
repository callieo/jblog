package lab;


public enum Week {
	DAY1, DAY2, DAY3;
	public String getSchedule() {
		switch (this) {
		case DAY1:
			return "重訓";
		case DAY2:
			return "跑步";
		case DAY3:
			return "部落格";
		default:
			return "寫Code";
		}
	}
}
