/**
 * This class represents the time as the amount of minutes that passed since midnight
 *
 * @author Yotam Combe
 * @version 24.03.2022
 */
public class Time2
{
    private int _minFromMid;
    private final int MIN = 0;
    private final int MAX_MINUTE = 59;
    private final int MAX_HOUR = 23;
    
    /**
     * Creates a Time2 object
     * @param int h for the hours number
     * @param int m for the minutes number
     */
    public Time2(int h, int m) {
        if(h < MIN || h > MAX_HOUR) //in case of illegal value
            h = MIN;
        if(m < MIN || m > MAX_MINUTE) //in case of illegal value
            m = MIN;
            
        _minFromMid = h * 60 + m;
    }
    
    /**
     * Creates a Time2 object
     * @param Time2 other to copy its values
     */
    public Time2(Time2 other) {
        _minFromMid = other._minFromMid;
    }
    
    //getters
    /**
     * Gives back the hours number which Time2 other object represents
     * @param Time2 other to know about what object it should run the code
     * @return int which represents the hours number of Time2 other
     */
    public int getHour(Time2 other) {
        return (other._minFromMid / 60);
    }
    
    /**
     * Gives back the minutes number which Time2 other object represents
     * @param Time2 other to know about what object it should run the code
     * @return int which represents the minutes number of Time2 other
     */
    public int getMinute(Time2 other) {
        return (other._minFromMid % 60);
    }
    
    //setters
    /**
     * Sets the hours number if this Time2
     * @param int num to know to what hours number it should set it
     */
    public void setHour(int num) {
        if(num >= MIN && num <= MAX_HOUR)
            _minFromMid = _minFromMid % 60 + num * 60;
    }
    
    /**
     * Sets the minutes number if this Time2
     * @param int num to know to what minutes number it should set it
     */
    public void setMinute(int num) {
        if(num >= MIN && num <= MAX_MINUTE)
            _minFromMid = (_minFromMid / 60) * 60 + num;
    }
    
    //others
    /**
     * Gives back the minutes which have passed since midnight
     * @return int which represents the minutes that have passed since midnight
     */
    public int minFromMidnight() {
        return _minFromMid;
    }
    
    /**
     * Checks if two Time2 object are equal
     * @param Time2 other to campare with this Time2
     * @return true if they are equal, false if not
     */
    public boolean equals(Time2 other) {
        return (_minFromMid == other._minFromMid);
    }
    
    /**
     * Checks if one Time2 is before the other
     * @param Time2 other to compare with
     * @return true if this Time2 is before other Time2, false if not
     */
    public boolean before(Time2 other) {
        return (_minFromMid < other._minFromMid);
    }
    
    /**
     * Checks if this Time2 is after other Time2
     * @param Time2 other to compare with
     * @return true if this Time2 is after other Time2, false if not
     */
    public boolean after(Time2 other) {
        return other.before(this);
    }
    
    /**
     * Gives back the minutes difference between two Time2 objects
     * @param Time2 other to calculate any difference
     * @return int which represents the difference between the two Time2 objects
     */
    public int difference(Time2 other) {
        return Math.abs(_minFromMid - other._minFromMid);
    }
    
    /**
     * Gives back a String of this Time2 as a digital time
     * @return String which declare what is the time of this Time2
     */
    public String toString() {
        String h = "" + _minFromMid / 60;
        String m = "" + _minFromMid % 60;
        if(_minFromMid / 60 < 10)
            h = "0" + _minFromMid / 60;
        if(_minFromMid % 60 < 10)
            m = "0" + _minFromMid % 60;
        
        return (h + ":" + m);
    }
    
    /**
     * Gives back this Time2 after it added a certain amount of minutes
     * @param int num to know how many minutes should it add
     * @return this Time2 after the minutes have been added
     */
    public Time2 addMinutes(int num) {
        _minFromMid += num;
        return this;
    }
}