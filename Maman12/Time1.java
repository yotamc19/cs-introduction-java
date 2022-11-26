/**
 * This class represents the time as shown in a digital clock
 *
 * @author Yotam Combe
 * @version 24.03.2022
 */
public class Time1
{
    private int _hour = 0;
    private int _minute = 0; //given zero in case of illegal value
    private final int MIN = 0;
    private final int MAX_MINUTE = 59;
    private final int MAX_HOUR = 23;
    
    /**
     * Creates a Time1 object
     * @param h number for the hours number
     * @param m number for the minutes number
     */
    public Time1(int h, int m) {
        if(h >= MIN && h <= MAX_HOUR)
            _hour = h;
        if(m >= MIN && m <= MAX_MINUTE)
            _minute = m;
    }
    
    /**
     * Creates a Time1 object
     * @param other Time1 to duplicate the values of it
     */
    public Time1(Time1 other) {
        _hour = other._hour;
        _minute = other._minute;
    }
    
    //getters
    /**
     * Gets back the hours number
     * @return the hours number in this Time1
     */
    public int getHour() {
        return _hour;
    }
    
    /**
     * Gets back the minutes number
     * @return the minutes number in this Time1
     */
    public int getMinute() {
        return _minute;
    }
    
    //setters
    /**
     * Sets the hours number
     * @param int num to insert to the hours number
     */
    public void setHour(int num) {
        if(num >= MIN && num <= MAX_HOUR) //in case num is illegal the hours number won't change
            _hour = num;
    }
    
    /**
     * Sets the minutes number
     * @param int num to insert to the minutes number
     */
    public void setMinute(int num) {
        if(num >= MIN && num <= MAX_MINUTE) //in case num is illegal minutes number won't change
            _minute = num;
    }
    
    //others
    /**
     * Gives back the time which this Time1 represents
     * @return a String which represents the time in this Time1
     */
    public String toString() {
        String h = "" + _hour;
        String m = "" + _minute;
        if(_hour < 10)
            h = "0" + _hour;
        if(_minute < 10)
            m = "0" + _minute;
        return (h + ":" + m);
    }
    
    /**
     * Gives back the minutes from midnight
     * @return the number of minutes that past since midnight
     */
    public int minFromMidnight() {
        return (_minute + (_hour * 60));
    }
    
    /**
     * Checks if two Time1 objects are equal
     * @param other Time1 object to compare with this Time1
     * @return true if they are equal, false if not
     */
    public boolean equals(Time1 other) {
        return (_hour == other.getHour() && _minute == other.getMinute());
    }
    
    /**
     * Checks if this Time1 is before the other Time1
     * @param other Time1 to compare with this Time1
     * @return true if this Time1 is before other, false if not
     */
    public boolean before(Time1 other) {
        if(_hour < other.getHour())
            return true;
        if(_hour > other.getHour())
            return false;
        return (_minute < other.getMinute()); //will run in case hours are the same
    }
    
    /**
     * Checks if this Time1 is after the other Time1
     * @param other Time1 to compare with this Time1
     * @return true if this Time1 is after other, false if not
     */
    public boolean after(Time1 other) {
        return !this.before(other);
    }
    
    /**
     * Gives back the minutes difference between two Time1
     * @param other Time1 to know between which other time should it check the difference
     * @return the amount of minutes between this Time1 and other
     */
    public int difference(Time1 other) {
        if(this.before(other)) {
            return (((other.getHour() - _hour) * 60) - _minute + other.getMinute());
        }
        return (((_hour - other.getHour()) * 60) - other.getMinute() + _minute); //will run in case the first Time1 is after the second
    }
    
    /**
     * Adds minutes to this Time1 object
     * @param int num to know how much minutes should it add
     * @return an updated Time1 object after num minutes added
     */
    public Time1 addMinutes(int num) {
        Time1 end = new Time1(this); //creates a duplicate if this Time1 in order to not change this
        end._minute += num;
        if(end._minute > MAX_MINUTE) {
            end._minute -= 60;
            end._hour++;
        }
        if(end._hour == MAX_HOUR + 1)
            end._hour = 0;
        return end;
    }
}