import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Sale } from '../models/sale';
import { SaleItemView } from '../models/sale-item-view';
import { environment } from '../../environment/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class SaleService {

  constructor() { }

  http = inject(HttpClient);

     API = environment.apiUrl + "/sale";

    findAll(): Observable<Sale[]> {
      return this.http.get<Sale[]>(this.API+"/get");
    }

    findById(id: number): Observable<Sale> {
      return this.http.get<Sale>(this.API+"/get/"+id);
    }

    registerSale(sale: Sale): Observable<any> {
      return this.http.post(this.API + "/post", sale, { responseType: 'text' }).pipe(
          map((response: string) => {
              try {
                  return JSON.parse(response);
              } catch (e) {
                  throw { error: response };
              }
          })
      );
  }

  getAllSaleItems(): Observable<SaleItemView[]> {
    return this.http.get<SaleItemView[]>(this.API+"/get/items-view");
  }

}
