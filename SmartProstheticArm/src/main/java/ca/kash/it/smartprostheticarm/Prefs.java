/*
    Kevin Santizo n01382533 Section B
    (Alex) Narendre Ramdhan n01240746 Section B
    Samad Agha n01364908 Section B
    Henry To n01365792 Section B
*/
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
