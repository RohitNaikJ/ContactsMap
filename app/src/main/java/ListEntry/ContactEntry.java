package ListEntry;

/**
 * Created by Rohit on 4/7/16.
 */
public class ContactEntry {
    private String name, email;
    private long phone, ophone;
    private double lat, longe;

    public ContactEntry(String name, String email, long phone, long ophone, double lat, double longe) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.ophone = ophone;
        this.lat = lat;
        this.longe = longe;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getPhone() {
        return phone;
    }

    public long getOphone() {
        return ophone;
    }

    public double getLat() {
        return lat;
    }

    public double getLonge() {
        return longe;
    }
}
