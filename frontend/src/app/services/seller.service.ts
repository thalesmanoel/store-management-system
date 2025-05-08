import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Seller } from '../models/seller';
import { environment } from '../../environment/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class SellerService {

  constructor() { }

  http = inject(HttpClient);

   API = environment.apiUrl + "/seller";

  findAll(): Observable<Seller[]> {
    return this.http.get<Seller[]>(this.API+"/get");
  }

  findById(id: number): Observable<Seller> {
    return this.http.get<Seller>(this.API+"/get/"+id);
  }

  registerSeller(seller: Seller): Observable<Seller[]> {
    return this.http.post<Seller[]>(this.API + "/post", seller);
  }

  updateSeller(seller: Seller, id: number): Observable<Seller[]> {
    const payload: any = {
      name: seller.name,
      email: seller.email,
      registrationDate: seller.registrationDate
    };
    if (seller.password) {
      payload.password = seller.password;
    }
    return this.http.put<Seller[]>(this.API+"/update/"+id, seller);
  }

  deleteSeller(id: number){
    return this.http.delete<void>(this.API+"/delete/"+id);
  }
}
