

/**
 * Holds the information of each individual band
 * 
 * @author william
 *
 */
public class Band {
	private int month;
	private int date;
	private String name;
	
	/**
	 * Default constructor, sets instance variables to the following:
	 * 
	 *   month = -1;
	 *   date = -1;
	 *   name = "default;
	 */
	public Band() {
		this.month = -1;
		this.date = -1;
		this.name = "default";
	}
	
	/**
	 * Constructor for a a new band object, creating 
	 * 
	 * @param month - the month the band is playing in
	 * @param date - the date the band is playing on
	 * @param name - the name of the band
	 */
	public Band(int month, int date, String name) throws IllegalArgumentException {
		if(month < 1 || month > 12) throw new IllegalArgumentException("Invalid month");
		if(date < 1 | date > 31) throw new IllegalArgumentException("Invalid date");
		this.month = month;
		this.date = date;
		this.name = name;
	}
	
	/**
	 * Accessor method for the month
	 * 
	 * @return the month of this Band
	 */
	public int getMonth() {
		return this.month;
	}
	
	/**
	 * Accessor method for the date
	 * 
	 * @return the date of this Band
	 */
	public int getDate() {
		return this.date;
	}
	
	/**
	 * Accessor method for the name
	 * 
	 * @return the name of this Band
	 */
	public String getName() {
		return this.name;
	}
}
