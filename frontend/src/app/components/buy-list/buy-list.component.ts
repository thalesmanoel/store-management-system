import { Component, inject } from '@angular/core';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';
import { Router, RouterModule } from '@angular/router';
import { HeaderComponent } from "../header/header.component";

@Component({
  selector: 'app-buy-list',
  standalone: true,
  imports: [HeaderComponent, RouterModule],
  templateUrl: './buy-list.component.html',
  styleUrl: './buy-list.component.scss'
})
export class BuyListComponent {
list: Product[] = [];

productService = inject(ProductService);
router = inject(Router);
quantity:number = 0;

findAll(){
  this.productService.findAll().subscribe({
    next: list => {
      this.list = list.map(product => ({ ...product, quantity: 0 }));
      console.log(this.list);
    },
    error: erro => {
      console.error('Erro ao buscar os dados:', erro);
      alert("Ocorreu algum erro!");
    },
  });
}

add(product: any) {
  if (product.quantity < product.stock) {
    product.quantity++;
  }
}

sub(product: any) {
  if (product.quantity > 0) {
    product.quantity--;
  }
}

ngOnInit(){
  this.findAll();
}
}
