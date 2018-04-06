package sk.miscik.main;

/**
 * Created by client on 02.03.2018.
 */
public class Applicant {

    private String firstName, lastName, email, phone, city, room, position, date;

    public Applicant(String firstName, String lastName, String email, String phone, String city, String room, String position, String date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.room = room;
        this.position = position;
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String format(String format) {
        format = format.replaceAll("!f", this.firstName);
        format = format.replaceAll("!l", this.lastName);
        format = format.replaceAll("!e",this.email);
        format = format.replaceAll("!n",this.phone);
        format = format.replaceAll("!c",this.city);
        format = format.replaceAll("!r",this.room);
        format = format.replaceAll("!p",this.position);
        format = format.replaceAll("!d",this.date);
        return format;
    }
}
