import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Contact } from '../model/contact.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ContactService {
  private http = inject(HttpClient);

  list(page: number, size: number, search?: any): Observable<any> {
    let params = new HttpParams();
    params = params.set('page', page.toString())
    params = params.set('size', size.toString())

    if (search !== undefined) {
      params = params.set('search', search);      
    }

    return this.http.get<any>('http://localhost:8080/api/contacts/search', {
      params,
    });
  }

  get(id: number) {
    return this.http.get<Contact>(`http://localhost:8080/api/contacts/${id}`);
  }

  create(contact: any) {
    return this.http.post<Contact>(
      'http://localhost:8080/api/contacts',
      contact
    );
  }

  update(id: number, contact: any) {
    return this.http.put<Contact>(
      `http://localhost:8080/api/contacts/${id}`,
      contact
    );
  }

  delete(id: number) {
    return this.http.delete<void>(`http://localhost:8080/api/contacts/${id}`);
  }
}
