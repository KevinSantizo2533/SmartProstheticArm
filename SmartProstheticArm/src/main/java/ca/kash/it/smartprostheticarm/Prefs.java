package ca.kash.it.smartprostheticarm;

public class Prefs {

    String orientation;
    boolean emergencyalerts;

    public Prefs(String orientation,Boolean emergencyalerts){

        this.orientation = orientation;
        this.emergencyalerts = emergencyalerts;
    }

    public String getOrientation(){
        return orientation;

    }
    public boolean getNotifications(){
        return emergencyalerts;

    }
}
