import { Component, inject } from '@angular/core';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';
import { Router, RouterModule } from '@angular/router';
import { HeaderComponent } from "../header/header.component";
import { CommonModule } from '@angular/common';
import { SaleService } from '../../services/sale.service';
import { Sale } from '../../models/sale';
import { FormsModule } from '@angular/forms';
import { PurchaseComponent } from "../../modals/purchase/purchase.component";

@Component({
  selector: 'app-buy-list',
  standalone: true,
  imports: [HeaderComponent, RouterModule, CommonModule, FormsModule, PurchaseComponent],
  templateUrl: './buy-list.component.html',
  styleUrl: './buy-list.component.scss'
})
export class BuyListComponent {
  list: Product[] = [];
  showModal = false;

  constructor(
    private productService: ProductService,
    private saleService: SaleService,
  ) {}

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts() {
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

  getQuantity(productId: number): number {
    const product = this.list.find(p => p.id === productId);
    return product ? product.quantity ?? 0 : 0;
  }

  add(product: Product) {
    const foundProduct = this.list.find(p => p.id === product.id);
    if (foundProduct) {
      if ((foundProduct.quantity ?? 0) < product.stock) {
        foundProduct.quantity = (foundProduct.quantity ?? 0) + 1;
      }
    } else {
      this.list.push({ ...product, quantity: 1 });
    }
  }

  sub(product: Product) {
    const foundProduct = this.list.find(p => p.id === product.id);
    if (foundProduct && (foundProduct.quantity ?? 0) > 0) {
      foundProduct.quantity = (foundProduct.quantity ?? 0) - 1;
    }
  }

  getTotal(): number {
    return this.list.reduce((sum, p) => sum + (p.price * (p.quantity ?? 0)), 0);
  }

  finalizePurchase() {

    const hasValidProduct = this.list.some(p => (p.quantity ?? 0) > 0);
    if (!hasValidProduct) {
      alert('Adicione ao menos um produto à lista antes de finalizar a compra');
      return;
    }

    const saleData = {
      items: this.list
        .filter(p => (p.quantity ?? 0) > 0)
        .map(p => ({
          productId: p.id,
          quantity: p.quantity ?? 0,
          unitPrice: p.price
        })),
      total: this.getTotal()
    };

    this.saleService.registerSale(saleData).subscribe({
      next: () => {
        this.showModal = true;
      },
      error: (err) => {
        console.error('Erro ao finalizar compra', err);
        alert('Erro ao finalizar compra');
      }
    });
  }

  modalConfirm(){
    this.showModal = false;
  }
}
