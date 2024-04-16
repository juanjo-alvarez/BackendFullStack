package com.campusdual.controller;

import com.campusdual.api.IContactService;
import com.campusdual.model.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private IContactService ContactService;

    @PostMapping("/get")
    public ContactDto queryContact(@RequestBody ContactDto ContactDto){
        try{
            return ContactService.queryContact(ContactDto);
        }catch (EntityNotFoundException e){
            ContactDto result = new ContactDto();
            result.setName("Contacto no encontrado");
            return result;
        }
    }

    @GetMapping("/getAll")
    public List<ContactDto> queryAllContacts(){
        return ContactService.queryAllContacts();
    }

    @PostMapping("/add")
    public int addContact(@RequestBody ContactDto ContactDto){
        return ContactService.insertContact(ContactDto);
    }

    @PutMapping("/update")
    public int updateContact(@RequestBody ContactDto ContactDto){
        return ContactService.updateContact(ContactDto);
    }

    @DeleteMapping("/delete")
    public int deleteContact(@RequestBody ContactDto ContactDto){
        return ContactService.deleteContact(ContactDto);
    }
}
