package classpackage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import java.io.Serializable;

@Entity
public class BuddyInfo implements Serializable{

    private String name;
    private String city;
    private String phoneNumber;
    private int age;

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getid(){
        return this.id;
    }

    public void setid(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void printBuddy() {
        System.out.println("Name: "+ name);
        System.out.println("City: " + city);
        System.out.println("Phone number: " + phoneNumber);
    }

    public BuddyInfo(String name, String address, String phoneNumber) {
        this.name = name;
        this.city = address;
        this.phoneNumber = phoneNumber;
    }

    public BuddyInfo(String name, String address, String phoneNumber, int age) {
        this.name = name;
        this.city = address;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public int getAge()
    {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /*
    public boolean isOver18() {
        return (this.age > 18);
    }
    */


    public BuddyInfo() {}

    public BuddyInfo(BuddyInfo buddy) {
        this.setName(buddy.getName());
        this.setCity(buddy.getCity());
        this.setPhoneNumber(buddy.getPhoneNumber());
        this.setAge(buddy.getAge());
    }

    public String greetBuddy() {
        return "Hello Buddy!";
    }

    public String toString() {
        return name + "$" + city + "$" + phoneNumber;
    }

    public String toXML() {
        return "<BuddyInfo>\n"+"<Name> " + name +
                " </Name>\n" + "<City> " + city +
                " </City>\n" + "<PhoneNumber> " + phoneNumber
                + " </PhoneNumber>\n" + "</BuddyInfo>";
    }

    public boolean equals(Object o) {
        BuddyInfo buddy = (BuddyInfo) o;
        return getName().equals(buddy.getName());

    }

    public static BuddyInfo importBuddy(String s) {

        String [] partStrings = s.split("\\$");
        BuddyInfo buddy = new BuddyInfo(partStrings[0], partStrings[1], partStrings[2]);
        return buddy;
    }

    public static void main(String args[]) {
        BuddyInfo buddy = new BuddyInfo("omar", "Ottawa","4156292");
        String s = "Chris $ Ottawa $ 666";
        BuddyInfo b = buddy.importBuddy(s);
        System.out.println(b.toString());
        System.out.println(buddy.toXML());

    }

}
