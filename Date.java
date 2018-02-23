
public class Date {
	private int month, day, year;
	
	Date(int m, int d, int y){
		this.month = m;
		this.day = d;
		this.year = y;
	}
	
	public void setDay(int d) {
		this.day = d;
	}
	public void setMonth(int m) {
		this.month = m;
	}
	public void setYear(int y) {
		this.year = y;
	}
	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	public String getDate() {
		return month + "/" + day + "/" + year;
	}
	

}
