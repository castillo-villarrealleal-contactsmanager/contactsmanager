import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public static Contact[] toContact(List<String> info){
        Contact[] infoOb = new Contact[(info.size()/2)];
        for(int i = 0; i < info.size()-1; i++){
            if(i % 2 == 0){
                infoOb[i/2] = new Contact(info.get(i));
            }else{
                infoOb[(i-1)/2].setPhoneNumber(info.get(i));
            }
        }
        return infoOb;
    }

    public static void main(String[] args) {
        String directory = "src";
//        Defining the path to contacts.txtx file
        String filename = "contacts.txt";
        Path definedPath = Paths.get(directory,filename);
        System.out.println(definedPath);
        List<String> fullInfo =contactInfoList(definedPath);
        System.out.println(fullInfo);
        List<Contact>infoOB = Arrays.asList(toContact(fullInfo));
        System.out.println(infoOB);
    }
}
