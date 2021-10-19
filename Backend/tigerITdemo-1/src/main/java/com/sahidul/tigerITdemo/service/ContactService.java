package com.sahidul.tigerITdemo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahidul.tigerITdemo.exception.UserNotFoundException;
import com.sahidul.tigerITdemo.model.Contact;
import com.sahidul.tigerITdemo.repo.ContactsRepo;

@Service
public class ContactService {
	private final ContactsRepo contactsrepo;

	@Autowired
	public ContactService(ContactsRepo contactsrepo) {
		super();
		this.contactsrepo = contactsrepo;
	}
	
	public Contact addContact(Contact contact) {
		return contactsrepo.save(contact);
	}
	
	public List<Contact> findAllContacts(){
		return contactsrepo.findAll();
	}
	
	public Contact updateContact(Contact contact) {
		return contactsrepo.save(contact);
	}
	
	public void deleteContact(Long id) {
		contactsrepo.deleteById(id);
	}

	public Contact findContactById(Long id) {
		return contactsrepo.findContactById(id).orElseThrow(() -> new UserNotFoundException("Contact by id" + id + "was not found"));
	}

}
