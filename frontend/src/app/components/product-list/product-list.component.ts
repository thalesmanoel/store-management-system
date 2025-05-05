import { Component, inject } from '@angular/core';
import { HeaderComponent } from "../header/header.component";
import { ConfirmDeleteComponent } from "../../modals/confirm-delete/confirm-delete.component";
import { Product } from '../../models/product';
import { Router, RouterModule } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [HeaderComponent, ConfirmDeleteComponent, RouterModule, CommonModule],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.scss'
})
export class ProductListComponent {
list: Product[] = [];
productService = inject(ProductService);
router = inject(Router);
showModal = false;
deleteProductId: number | null = null;


findAll(){
  this.productService.findAll().subscribe({
    next: list => {
      this.list = list;
      console.log(this.list);
    },
    error: erro => {
      console.error('Erro ao buscar os dados:', erro);
      alert("Ocorreu algum erro!");
    },
  });
}

edit(product: Product){
  console.log(product)
  this.router.navigate(['/product/edit', product.id]);
}

deleteById(){
  if (this.deleteProductId !== null) {
  this.showModal = false;
    this.productService.deleteProduct(this.deleteProductId).subscribe({
      next: () => {
        this.list = this.list.filter(product => product.id !== this.deleteProductId);
      },
      error: erro => {
        alert('Erro ao deletar produto!');
        console.error(erro);
      }
    });
  } else {
    console.error("ID do cliente não encontrado");
  }
}

openConfirm(productId: number) {
  this.deleteProductId = productId;
  this.showModal = true;
}

ngOnInit(){
  this.findAll();
}
}
