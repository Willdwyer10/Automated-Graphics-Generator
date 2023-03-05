

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
	
	/**
	 * Helper method for the Band's number of words
	 * NOTE: new words are counted by the number of capital letters in the name!!
	 * 
	 * @return the number of words in Band name
	 */
	private int getNumWords() {
	  int count = 0;
	  for (int i = 0; i < this.name.length(); i++) {
	    if(Character.isUpperCase(this.name.charAt(i))) count ++;
	    
	  }
	  System.out.println();
	  return count;
	}
	
	/**
	 * 
	 * @return an array that contains one string for the first line of the name and one string for
	 *             for the second line of the string
	 */
	public String[] nameIntoSections() {
	  String[] returner = new String[2];
	  
	  // Case where the name of the Band is 1 word
	  if(this.getNumWords() == 1) {
	    returner[0] = this.name;
	  } // end if
	  
	  // Case where the name of the Band is 2 words long
	  else if (this.getNumWords() == 2) {
	    // finds the index of the FIRST occurring space character
	    int spacePosition = 0;
	    for(int i = 0; i < this.name.length(); i ++) {
          if(this.name.charAt(i) == ' ') {
            spacePosition = i;
            break;
          }
        } // end for
	    
	    // determines the number of spaces in the name String
	    int numSpaces = 0;
	    for(int i = 0; i < this.name.length(); i ++) {
	      if(this.name.charAt(i) == ' ') {
	        numSpaces++;
	      }
	    } // end for
	    
	    // Case where there is only one space (aka, just two words with a space between)
	    if(numSpaces == 1) {
	      returner[0] = this.name.substring(0, spacePosition);
	      returner[1] = this.name.substring(spacePosition + 1, this.name.length());
	    } // end if
	    
	    // Case where there are two words joined by something other than a single space character
	    else {
	      for(int i = (this.name.length() / 2) - 1; i < this.name.length(); i++) {
	        if(this.name.charAt(i) == '/' || this.name.charAt(i) == '&') {
	          returner[0] = this.name.substring(0, spacePosition);
	          returner[1] = this.name.substring(spacePosition+1, this.name.length());
	        }
	        
	        else {
	          returner[0] = this.name.substring(0, spacePosition + 2);
	          returner[1] = this.name.substring(spacePosition + 3, this.name.length());
	        }
	      } // end for
	    } // end else 
	  } // end else if
	  
	  // case where the name is more than two words long
	  else {
	    returner[0] = this.name;
	    returner[1] = this.name;
	  }
	  
	  // changes all the "/" characters to "&" characters
	  for(int i = 0; i < 2; i++) {
	    if(returner[i] != null) returner[i] = returner[i].replaceAll("\\/", "&").toUpperCase();
	  }
	  
	  return returner;
	}

	
	/**
	 * toString method for band objects
	 * 
	 * return a string representation of the bands
	 */
	@Override
	public String toString() {
	  String returnString = "M_" +this.month + "_" + this.date + "_" + this.name;
	  return returnString.replaceAll("[\\&\\/\\\"\\(\\)\\']", "").replaceAll(" ", "");
	}
}
