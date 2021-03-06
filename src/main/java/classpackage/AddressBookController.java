package classpackage;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddressBookController {

    @Autowired
    private AddressRepository repoBook;

    @Autowired
    private BuddyRepository repoBuddy;
    @GetMapping(path="/", produces = "application/json")
    public List<BuddyInfo> getBuddies(){
        return repoBuddy.findAll();
    }

    @GetMapping("/createAddressBook")
    public AddressBook createAddressBook(){
        AddressBook book = new AddressBook();
        BuddyInfo buddyInfo = new BuddyInfo("Omar", "Ottawa", "123", 22);
        book.addBuddy(buddyInfo);
        repoBook.save(book);
        return book;
    }

    @GetMapping("/addBuddy")
    public ResponseEntity<Object> addBuddy(@RequestParam(name = "name", required = false, defaultValue = "Odee") String name, String address, String phoneNumber){

        //long id = book.getBuddyList().size() + 1;
        BuddyInfo buddy = new BuddyInfo(name, address, phoneNumber);

        //buddy.setid(id);
       repoBuddy.save(buddy);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(buddy.getid()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/error")
    public void error(){

    }
}
