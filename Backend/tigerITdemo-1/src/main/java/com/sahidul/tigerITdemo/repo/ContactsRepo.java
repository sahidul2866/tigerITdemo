package com.sahidul.tigerITdemo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahidul.tigerITdemo.model.Contact;

public interface ContactsRepo extends JpaRepository<Contact, Long>{

	void deleteContactById(Long id);

	Optional<Contact> findContactById(Long id);
	
}
