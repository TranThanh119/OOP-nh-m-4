public class Time {
    int hour;
    int minute;
    int second;

    // Default constructor
    Time() { 
        setTime(0, 0, 0); 
    }

    // Constructor with hour parameter
    Time(int h) { 
        setTime(h, 0, 0); 
    }

    // Constructor with hour and minute parameters
    Time(int h, int m) { 
        setTime(h, m, 0); 
    }

    // Constructor with hour, minute, and second parameters
    Time(int h, int m, int s) { 
        setTime(h, m, s); 
    }

    // Method to set the time
    Time setTime(int h, int m, int s) {
        setHour(h);
        setMinute(m);
        setSecond(s);
        return this;
    }

    // Method to set the hour
    Time setHour(int h) {
        hour = ((h >= 0 && h < 24) ? h : 0);
        return this;
    }

    // Method to set the minute
    Time setMinute(int m) {
        minute = ((m >= 0 && m < 60) ? m : 0);
        return this;
    }

    // Method to set the second
    Time setSecond(int s) {
        second = ((s >= 0 && s < 60) ? s : 0);
        return this;
    }

    // Method to get the hour
    int getHour() { 
        return hour; 
    }

    // Method to get the minute
    int getMinute() { 
        return minute; 
    }

    // Method to get the second
    int getSecond() { 
        return second; 
    }

    // Method to return the time as a string
    public String toString() {
        return String.format(
            "%02d:%02d:%02d %s", 
            (hour == 0 || hour == 12) ? 12 : hour % 12, 
            minute, 
            second, 
            (hour < 12) ? "AM" : "PM"
        );
    }

    // Main method to test the Time class
    public static void main(String[] args) {
        Time time1 = new Time();           // 00:00:00 AM
        Time time2 = new Time(13);         // 01:00:00 PM
        Time time3 = new Time(13, 45);     // 01:45:00 PM
        Time time4 = new Time(13, 45, 30); // 01:45:30 PM

        System.out.println(time1);
        System.out.println(time2);
        System.out.println(time3);
        System.out.println(time4);
    }
}
