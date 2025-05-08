import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Seller } from '../../models/seller';
import { SellerService } from '../../services/seller.service';
import { Router, RouterModule } from '@angular/router';
import { HeaderComponent } from "../header/header.component";
import { ConfirmDeleteComponent } from "../../modals/confirm-delete/confirm-delete.component";
import { ErrorModalComponent } from "../../modals/error-modal/error-modal.component";

@Component({
  selector: 'app-seller-list',
  standalone: true,
  imports: [CommonModule, RouterModule, HeaderComponent, ConfirmDeleteComponent, ErrorModalComponent],
  templateUrl: './seller-list.component.html',
  styleUrl: './seller-list.component.scss'
})
export class SellerListComponent {
list: Seller[] = [];
sellerService = inject(SellerService);
router = inject(Router);
showModal = false;
showErrorModal = false;
errorMessage = '';
deleteSellerId: number | null = null;

findAll(){
  this.sellerService.findAll().subscribe({
    next: list => {
      this.list = list;
      console.log(this.list);
    },
    error: erro => {
      console.error('Erro ao buscar os dados:', erro);
      this.errorMessage = 'Ocorreu algum erro.';
      this.showErrorModal = true;
    },
  });
}

edit(seller: Seller){
  console.log(seller);
  this.router.navigate(['/seller/edit', seller.id]);
}

deleteById(){
  if (this.deleteSellerId !== null) {
  this.showModal = false;
    this.sellerService.deleteSeller(this.deleteSellerId).subscribe({
      next: () => {
        this.list = this.list.filter(seller => seller.id !== this.deleteSellerId);
      },
      error: erro => {
        this.errorMessage = 'Ocorreu algum erro.';
        this.showErrorModal = true;
        console.error(erro);
      }
    });
  } else {
    console.error("ID não encontrado");
  }
}

openConfirm(sellerId: number) {
  this.deleteSellerId = sellerId;
  this.showModal = true;
}

ngOnInit(){
  this.findAll();
}
}
