import { Component, OnInit, inject } from '@angular/core';
import { ContactService } from '../services/contact.service';
import { DatePipe } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Contact } from '../model/contact.interface';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import {NgxPaginationModule} from 'ngx-pagination';

@Component({
  selector: 'app-contact-list',
  standalone: true,
  imports: [DatePipe, RouterModule, ReactiveFormsModule, NgxPaginationModule],
  templateUrl: './contact-list.component.html',
  styleUrl: './contact-list.component.css'
})
export default class ContactListComponent implements OnInit{
  private fb = inject(FormBuilder);
  private contactService = inject(ContactService);

  formSearch?: FormGroup;

  contacts: Contact[] = [];
  currentPage: number =1;
  pageSize = 2;
  totalItems: number=0;

  ngOnInit(): void {
    this.formSearch = this.fb.group({
      search: ['']
    });

    this.loadAll();
  }

  loadAll(){
    this.contactService.list(this.currentPage-1, this.pageSize)
    .subscribe(contacts => {
      this.contacts = contacts.content;
      this.totalItems = contacts.totalElements;
    });
  }

  deleteContact(contact: Contact){
    this.contactService.delete(contact.id)
    .subscribe(() => {
      this.loadAll();
    });
  }

  pageChange(event: any){
    this.currentPage = event;
    this.loadAll();
  }
}
