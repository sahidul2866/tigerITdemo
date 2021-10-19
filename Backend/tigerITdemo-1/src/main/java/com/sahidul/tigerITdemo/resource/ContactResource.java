package com.sahidul.tigerITdemo.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahidul.tigerITdemo.model.Contact;
import com.sahidul.tigerITdemo.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactResource {
	private final ContactService contactservice;

	public ContactResource(ContactService contactservice) {
		super();
		this.contactservice = contactservice;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Contact>> getAllContacts(){
		System.out.println("Get request");
		List<Contact> contacts = contactservice.findAllContacts();
		return new ResponseEntity<>(contacts, HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
    public ResponseEntity<Contact> getContactById (@PathVariable("id") Long id) {
        Contact contact = contactservice.findContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
    	System.out.println("Add request");
        Contact newContact = contactservice.addContact(contact);
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Contact> updateEmployee(@RequestBody Contact contact) {
    	System.out.println("Update request");
        Contact updateContact = contactservice.updateContact(contact);
        return new ResponseEntity<>(updateContact, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable("id") Long id) {
    	System.out.println("Delete request");
        contactservice.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

