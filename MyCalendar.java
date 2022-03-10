/**
 * Name: Morales, Kyle
 * ID: A16162998
 * Email: kmmorale@ucsd.edu
 * Sources used: Zybooks, course
 * 
 * TreeMap implementation of a calender
 */
public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;
    public MyCalendar() {
        this.calendar = new MyTreeMap<Integer, Integer>();
    }
    
    public boolean book(int start, int end) throws IllegalArgumentException{
        if(start < 0 || start >= end) {
            throw new IllegalArgumentException();
        }
        else if(this.calendar.ceilingKey(start) != null || this.calendar.floorKey(start) != null) {
            if(this.calendar.ceilingKey(start) < end || this.calendar.get(this.calendar.floorKey(start)) > start) {
                return false;
            }
        }
        else {
            this.calendar.put(start, end);
            return true;
        }
        return false;
    }

    public MyTreeMap getCalendar(){
        return this.calendar;
    }
}