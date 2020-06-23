import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class app {

//    Reads contacts.txt then converts it to a list. Names are assigned to even indexes in the list. Phone Numbers are assigned to odd indexes.
    public static List<String> contactInfoList(Path definedPath){
        List<String> info = new ArrayList<>();
        try {
            info = Files.readAllLines(definedPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }
//    Converts array list created in contactInfoList into objects for easy manipulation.
    public static Contact[] toContact(List<String> info) {

        Contact[] infoOb = new Contact[(info.size()/2)];
        for(int i = 0; i < info.size(); i++){
            if(i % 2 == 0) {
                infoOb[i/2] = new Contact(info.get(i));
            } else{
                infoOb[(i-1)/2].setPhoneNumber(info.get(i));
            }
        }
        return infoOb;
    }

    //METHODS FOR VIEWING ALL CONTACTS. //NOT RETURNING ANYTHING PRINTING TO CONSOLE
    //prints out all objects gives object ID
    public static void showAllContacts(Contact[] infoOb) {
        for(Contact contacts : infoOb) {
            System.out.println(contacts.getName());
            System.out.println(contacts.getPhoneNumber());
        }

    }

    //METHOD TO ADD CONTACTS
    public static Contact[] addContacts(Contact[] contactName, String newContact, String phoneNumber) {
        Contact[] contactNames = Arrays.copyOf(contactName, contactName.length+1);
        Contact newerContact = new Contact(newContact);
        newerContact.setPhoneNumber(phoneNumber);
        contactNames[contactNames.length-1] = newerContact;
        return contactNames;

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);



        String directory = "src";
//        Defining the path to contacts.txtx file
        String filename = "contacts.txt";
        Path definedPath = Paths.get(directory,filename);
        System.out.println(definedPath);
        List<String> fullInfo =contactInfoList(definedPath);
        System.out.println(fullInfo);
        Contact[] infoOB = toContact(fullInfo);
        System.out.println(infoOB);

        showAllContacts(infoOB);
        //reading array and reassigniong infoOB to a new Array.
        infoOB = addContacts(infoOB, "Ramon Villarreal-Leal", "4154202467");
        showAllContacts(infoOB);
    }




}
