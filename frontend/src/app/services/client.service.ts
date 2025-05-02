import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from '../models/client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor() { }

  http = inject(HttpClient);

  API = "http://localhost:8080/client";

  findAll(): Observable<Client[]> {
    return this.http.get<Client[]>(this.API+"/get");
  }

  findById(id: number): Observable<Client> {
    return this.http.get<Client>(this.API+"/get/"+id);
  }

  registerClient(client: Client): Observable<Client[]> {
    return this.http.post<Client[]>(this.API + "/post", client);
  }

  updateClient(client: Client, id: number): Observable<Client[]> {
    return this.http.put<Client[]>(this.API+"/update/"+id, client);
  }

  deleteClient(id: number){
    return this.http.delete<void>(this.API+"/delete/"+id);
  }
}
