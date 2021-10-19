import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Contact } from './contact';
import { ContactService } from './contact.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'ContactManagerApp';
  public contacts: Contact[] = [];
  public editContact!: Contact;
  public deleteContact!: Contact;

  constructor(private contactService: ContactService){}

  ngOnInit() {
    this.getContacts();
  }

  public getContacts(): void {
    this.contactService.getContacts().subscribe(
      (response: Contact[]) => {
        this.contacts = response;
        console.log(this.contacts);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddContact(addForm: NgForm): void {
    console.log(addForm.value);
    document.getElementById('add-contact-form')!.click();
    this.contactService.addContact(addForm.value).subscribe(
      (response: Contact) => {
        console.log(response);
        this.getContacts();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateContact(contact: Contact): void {
    this.contactService.updateContact(contact).subscribe(
      (response: Contact) => {
        console.log(response);
        this.getContacts();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteContact(contactId: number): void {
    this.contactService.deleteContact(contactId).subscribe(
      (response: void) => {
        console.log(response);
        this.getContacts();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(contact: Contact, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addContactModal');
    }
    if (mode === 'edit') {
      this.editContact = contact;
      button.setAttribute('data-target', '#updateContactModal');
    }
    if (mode === 'delete') {
      this.deleteContact = contact;
      button.setAttribute('data-target', '#deleteContactModal');
    }
    container!.appendChild(button);
    button.click();
  }

}
