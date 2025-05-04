import { inject, Injectable } from '@angular/core';
import { Product } from '../models/product';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor() { }

  http = inject(HttpClient);

    API = "http://localhost:8080/product";

    findAll(): Observable<Product[]> {
      return this.http.get<Product[]>(this.API+"/get");
    }

    findById(id: number): Observable<Product> {
      return this.http.get<Product>(this.API+"/get/"+id);
    }

    registerProduct(product: Product): Observable<Product[]> {
      return this.http.post<Product[]>(this.API + "/post", product);
    }

    updateProduct(product: Product, id: number): Observable<Product[]> {
      return this.http.put<Product[]>(this.API+"/update/"+id, product);
    }

    deleteProduct(id: number){
      return this.http.delete<void>(this.API+"/delete/"+id);
    }
}
