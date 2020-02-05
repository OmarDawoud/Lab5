package classpackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

@Entity
public class AddressBook implements Serializable{

    @OneToMany
    private List<BuddyInfo> buddyList;

    @Id
    @GeneratedValue
    private Long id;


    public Long getid(){
        return this.id;
    }

    public void setid(Long id){
        this.id = id;
    }

    public AddressBook() {
        this.buddyList = new ArrayList<>();
    }


    public List<BuddyInfo> getBuddyList(){
        return this.buddyList;
    }

    public void setBuddyList(ArrayList<BuddyInfo> buddyList){
        this.buddyList = buddyList;
    }

    public void addBuddy(BuddyInfo newBuddy) {
        if(newBuddy != null) {
            buddyList.add(newBuddy);
        }
    }

    public void removeBuddy(int index) {
        buddyList.remove(index);
    }

    public BuddyInfo getBuddy(int index) {
        return buddyList.get(index);
    }

    public int size() {
        return buddyList.size();
    }

    public void clear() {
        this.buddyList = new ArrayList<>();
    }

    public String toString() {
        String a = "";
        for(BuddyInfo buddy : buddyList) {
            a = a + buddy.toString()+"\n";
        }
        return a;
    }

    public String toXML() {
        String x = "<AddressBook>\n";
        for(BuddyInfo buddy : buddyList) {
            x = x + buddy.toXML()+"\n";
        }
        x = x + "</AddressBook>";
        return x;
    }

    public static AddressBook importFile(File f) {

        try {
            FileInputStream fileIn = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            AddressBook book = (AddressBook) in.readObject();
            fileIn.close();
            in.close();
            System.out.println("Serialized data imported.");
            System.out.println(book.toString());
            return book;
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error import Serialization");
            return null;
        }

    }

    public AddressBook importXML(File f) throws ParserConfigurationException, SAXException, IOException{

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser s = spf.newSAXParser();

        AddressHandler ah = new AddressHandler();
        s.parse(f, ah);

        return ah.getBook();

    }

    public void export() {

        try{
            FileOutputStream fileOut =
                    new FileOutputStream("FileStreamBook.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data saved");

        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("Error export serialization");
        }
    }

    public void exportXML() {

        try{
            String s = this.toXML();
            BufferedWriter out = new BufferedWriter(new FileWriter("myFile.txt"));
            out.write(s);
            out.close();
        } catch(IOException e1) {
            System.out.println("error exporting");
        }
    }

    private class AddressHandler extends DefaultHandler{

        private AddressBook book;
        private BuddyInfo buddy;
        private String s;

        public AddressBook getBook() {
            return book;
        }
        public void startElement(String u, String ln, String qName, Attributes a) {

            switch(qName) {
                case "<AddressBook>":
                    book = new AddressBook();
                    break;
                case "<BuddyInfo>":
                    buddy = new BuddyInfo();
                    break;
            }
        }

        public void endElement(String uri, String ln, String localName, String qName) {

            switch(qName) {
                case "Name":
                    buddy.setName(s);
                    break;
                case "City":
                    buddy.setCity(s);
                    break;
                case "PhoneNumber":
                    buddy.setPhoneNumber(s);
                    break;
                case "BuddyInfo":
                    book.addBuddy(buddy);
                    break;
            }

        }

        public void characters(char[] ch, int start, int length) {
            s = new String(ch,start,length);
        }
    }

    public static void main(String args[]){

        BuddyInfo buddy = new BuddyInfo("Omar","Ottawa","4156292");
        BuddyInfo buddy2 = new BuddyInfo("Chris","Ottawa","666");
        AddressBook book = new AddressBook();

        book.addBuddy(buddy);
        book.addBuddy(buddy2);

        System.out.println("");
        System.out.println("");
        book.exportXML();
        try {
            AddressBook book1 = book.importXML(new File("myFile.txt"));
            System.out.println(book.toXML());
        }
        catch(ParserConfigurationException | SAXException | IOException e) {
            System.out.println("main ERROR");
            System.out.println(e.getMessage());
        }
    }

}