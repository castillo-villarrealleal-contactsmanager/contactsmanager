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

    public static List<String> fromContact(List<Contact> infoOb){
        List<String> info = new ArrayList<>();
        for(Contact string: infoOb){

            info.add(string.getName());
            info.add(string.getPhoneNumber());

        }
        return info;
    }
//    Converts array list created in contactInfoList into objects for easy manipulation.
    public static List<Contact> toContact(List<String> info) {

        List<Contact> infoOb = new ArrayList<>();
        for(int i = 0; i < info.size(); i++){
            if(i % 2 == 0) {
                infoOb.add(new Contact(info.get(i)));
            } else{
                infoOb.get((i - 1) / 2).setPhoneNumber(info.get(i));
            }
        }
        return infoOb;
    }

    //METHODS FOR VIEWING ALL CONTACTS. //NOT RETURNING ANYTHING PRINTING TO CONSOLE
    //prints out all objects gives object ID
    public static void showAllContacts(List<Contact> infoOb) {
        System.out.println("Name | Phone Number");
        System.out.println("--------------------");
        for(Contact contacts : infoOb) {

            System.out.println(contacts.getName() + " | " + contacts.getPhoneNumber());
//            System.out.println(contacts.getPhoneNumber());
        }

    }

    //METHOD TO ADD CONTACTS
    public static List<Contact> addContacts(List<Contact> contactName) {
        Scanner name = new Scanner(System.in);
        Scanner number = new Scanner(System.in);
        System.out.println("Please enter a new contact name: ");
        List<Contact> contactNames = contactName;
        String newContact = name.nextLine();
        Contact newerContact = new Contact(newContact);
        System.out.println("Please enter the new contacts phone number: ");
        String phoneNumber = number.nextLine();
        newerContact.setPhoneNumber(phoneNumber);
        contactNames.add(newerContact);
        return contactNames;

    }

    public static List<Contact> deleteContacts(List<Contact> infoOb){
        Scanner deleteInput = new Scanner(System.in);
        System.out.println("Which contact would you like to delete?");
        String contactName = deleteInput.nextLine();
        for(int i = 0; i < infoOb.size(); i++){
            if(infoOb.get(i).getName().equals(contactName)){
                infoOb.remove(i);
            }else {
                continue;
            }
        }
        return infoOb;
    }

    public static void searchContact(List<Contact> infoOb){
        Scanner searchName = new Scanner(System.in);
        System.out.println("Which Contact would you like to search?");
        String contactName = searchName.nextLine();

        for(int i = 0; i < infoOb.size(); i++){
            if(infoOb.get(i).getName().equals(contactName)){
                System.out.println(infoOb.get(i).getName());
                System.out.println(infoOb.get(i).getPhoneNumber());
                break;
            } else if(!(infoOb.get(i).getName().equals(contactName))) {
                System.out.println("This contact is not in our list. Try again.");
                System.out.println("");
                break;
            } else {
                continue;
            }

        }
    }

    public static String userInterface(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("\n1. View contacts");
        System.out.println("2. Add a new contact");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete and existing contanct");
        System.out.println("5. Exit.");
        System.out.println("Enter an option (1,2,3,4 or 5):");
        String choice = userInput.nextLine();

        return choice;

    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String directory = "src";
//        Defining the path to contacts.txtx file
        String filename = "contacts.txt";
        Path definedPath = Paths.get(directory,filename);
//        System.out.println(definedPath);
        List<String> fullInfo =contactInfoList(definedPath);
//        System.out.println(fullInfo);
        List<Contact> infoOB = toContact(fullInfo);
//
        System.out.println("Welcome to the contact manager app!");
        String choice;

            do {

                choice = userInterface();
                if (choice.equals("1")) {
                    showAllContacts(infoOB);
                } else if (choice.equals("2")) {
                    addContacts(infoOB);
                } else if (choice.equals("3")) {
                    searchContact(infoOB);
                } else if (choice.equals("4")) {
                    deleteContacts(infoOB);
                } else if (choice.equals("5")) {
                    System.out.println("GoodBye");

                }

            }while(!choice.equals("5"));
        List<String> groceryList = Arrays.asList("");
        Files.write(Paths.get(directory,filename), groceryList);
        Files.write(Paths.get(directory,filename),fromContact(infoOB));




//        showAllContacts(infoOB);
//        //reading array and reassigniong infoOB to a new Array.
//        infoOB = addContacts(infoOB, "Ramon VillarrealLeal", "4154202467");
//        showAllContacts(infoOB);
//
//        infoOB = deleteContacts(infoOB,"larry castillo");
//        showAllContacts(infoOB);
//
//        searchContact(infoOB,"Ramon VillarrealLeal");
    }




}
