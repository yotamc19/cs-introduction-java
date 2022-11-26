/**
 * Class Flight represents a Flight object that contain any information about the flight
 *
 * @author Yotam Combe
 * @version 24.03.2022
 */
public class Flight
{
    private String _origin;
    private String _destination;
    private Time1 _departure;
    private int _flightDuration;
    private int _noOfPassengers;
    private boolean _isFull;
    private int _price;
    private final int MAX_CAPACITY = 250;
    
    /**
     * Creates a Flight object
     * @param String origin to know where the flight takes from
     * @param String destination to know where the flight is heading
     * @param int hDeparture to know the hours number of the flight departure
     * @param int mDeparture to know the minutes number of the flight departure
     * @param int flightDuration to know for how long will the flight be in air
     * @param int noOfPassengers to know how many passengers are on this flight
     * @param int price to know the price of one ticket for this flight
     */
    public Flight(String origin, String destination, int hDeparture, int mDeparture, int flightDuration, int noOfPassengers, int price) {
        if(noOfPassengers > MAX_CAPACITY) //in case the amount of passengers on the flight is above the maximum
            noOfPassengers = MAX_CAPACITY;
        if(noOfPassengers < 0) //in case the amount of passengers is illegal
            noOfPassengers = 0;
        if(flightDuration < 0) //in case the flight duration is illegal
            flightDuration = 0;
        if(price < 0) //in case the flight price is illegal
            price = 0;
            
        _origin = origin;
        _destination = destination;
        _departure = new Time1(hDeparture, mDeparture);
        _flightDuration = flightDuration;
        _noOfPassengers = noOfPassengers;
        _isFull = (noOfPassengers == MAX_CAPACITY);
        _price = price;
    }
    
    /**
     * Creates a Flight object
     * @param Flight other to copy its values
     */
    public Flight(Flight other) {
        _origin = other._origin;
        _destination = other._destination;
        _departure = new Time1(other._departure);
        _flightDuration = other._flightDuration;
        _noOfPassengers = other._noOfPassengers;
        _isFull = other._isFull;
        _price = other._price;
    }
    
    //getters
    /**
     * Gives back the origin of this Flight
     * @return String which represents the origin of this flight
     */
    public String getOrigin() {
        return _origin;
    }
    
    /**
     * Gives back the destination of this Flight
     * @return String which represents the destination of this flight 
     */
    public String getDestination() {
        return _destination;
    }
    
    /**
     * Gives back a Time1 object which represents the departure time of this flight
     * @return Time1 which represents the departure time of this flight
     */
    public Time1 getDeparture() {
        return new Time1(_departure); //avoids aliasing
    }
    
    /**
     * Gives back this Flight duration
     * @return int which represents this Flight duration
     */
    public int getFlightDuration() {
        return _flightDuration;
    }
    
    /**
     * Gives back the number of passengers on this Flight
     * @return int which represents the number of passengers on this Flight
     */
    public int getNoOfPassengers() {
        return _noOfPassengers;
    }
    
    /**
     * Checks if this Flight is full
     * @return true if this Flight is full, false if not
     */
    public boolean isFull() {
        return _isFull;
    }
    
    /**
     * Gives back the price of this Flight
     * @return int which represents the price of one ticket for this Flight
     */
    public int getPrice() {
        return _price;
    }
    
    //setters
    /**
     * Sets the origin of this Flight
     * @param String origin to know what origin should it set
     */
    public void setOrigin(String origin) {
        _origin = origin;
    }
    
    /**
     * Sets the destination of this Flight
     * @param String destination to know what destination should it set
     */
    public void setDestination(String destination) {
        _destination = destination;
    }
    
    /**
     * Sets the departure of this Flight
     * @param Time1 departure to know what departure should it set
     */
    public void setDeparture(Time1 departure) {
        _departure = new Time1(departure); //avoids aliasing
    }
    
    /**
     * Sets the flight duration of this Flight
     * @param int flightDuration to know what flight duration should it set
     */
    public void setFlightDuration(int flightDuration) {
        if(flightDuration < 0) //in case flight duration is illegal
            _flightDuration = 0;
        else
            _flightDuration = flightDuration;
    }
    
    /**
     * Sets the number of passengers of this Flight
     * @param int noOfPassengers to know what nuber of passengers should it set
     */
    public void setNoOfPassengers(int noOfPassengers) {
        if(_isFull && noOfPassengers < MAX_CAPACITY) {
            _noOfPassengers = noOfPassengers;
            _isFull = false;
        }
        if(!_isFull && noOfPassengers < MAX_CAPACITY) //covers the first two cases when the int we get is smaller then 250
            _noOfPassengers = noOfPassengers;
        if(noOfPassengers >= MAX_CAPACITY) { //covers the last case possible when the int we get is equal or bigger then 250
            _noOfPassengers = MAX_CAPACITY;
            _isFull = true;
        }
    }
    
    /**
     * Sets the price of this Flight
     * @param int price to know what price should it set
     */
    public void setPrice(int price) {
        _price = price;
    }
    
    //others
    /**
     * Checks if two Flights are equal
     * @param Flight other to compare with this Flight
     * @return true if the two are equal' false if not
     */
    public boolean equals(Flight other) {
        if(_origin == other._origin && _destination == other._destination && _departure.equals(other._departure))
            return true;
        return false;
    }
    
    /**
     * Gives back the arrival time of this Flight
     * @return Time1 which represents the arrival time of this Flight
     */
    public Time1 getArrivalTime() {
        return _departure.addMinutes(_flightDuration);
    }
    
    /**
     * Adds a number of passengers to this Flight and declares if it was able to
     * @param int noOfPassengers to know how many passengers should it add
     * @return true if it was able to add the passengers, false if not
     */
    public boolean addPassengers(int noOfPassengers) {
        if(_noOfPassengers + noOfPassengers <= MAX_CAPACITY) { //it is able to add the passengers
            _noOfPassengers += noOfPassengers;
            if(_noOfPassengers == MAX_CAPACITY) //updates _isFull value if needed
                _isFull = true;
            return true;
        }
        return false;
    }
    
    /**
     * Checks if one flight is cheaper then the other
     * @param Flight other to compare with this Flight
     * @return true if this Flight is cheaper then Flight other
     */
    public boolean isCheaper(Flight other) {
        return (_price < other._price);
    }
    
    /**
     * Gives back the total amount spent on tickets for this Flight
     * @return int which represents the amount spent on tickets for this Flight
     */
    public int totalPrice() {
        return (_noOfPassengers * _price);
    }
    
    /**
     * Checks if one flight is lands earlier then the other one
     * @param Flight other to compare with this Flight
     * @return true if this Flight lands earlier then other Flight, false if not
     */
    public boolean landsEarlier(Flight other) {
        return (this.getArrivalTime().before(other.getArrivalTime()));
    }
    
    /**
     * Gives back a short summery of this Flight
     * @return String which includes a short summery of this Flight
     */
    public String toString() {
        String isFull = " not "; //first value of String isFull
        if(_isFull) //updates String isFull if needed
            isFull = " ";
        return ("Flight from " + _origin + " to " + _destination + " departs at " + _departure.toString() + ". Flight is" + isFull + "full.");
    }
}