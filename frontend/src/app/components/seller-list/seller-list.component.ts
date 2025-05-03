import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Seller } from '../../models/seller';
import { SellerService } from '../../services/seller.service';
import { Router, RouterModule } from '@angular/router';
import { HeaderComponent } from "../header/header.component";
import { ConfirmDeleteComponent } from "../../modals/confirm-delete/confirm-delete.component";

@Component({
  selector: 'app-seller-list',
  standalone: true,
  imports: [CommonModule, RouterModule, HeaderComponent, ConfirmDeleteComponent],
  templateUrl: './seller-list.component.html',
  styleUrl: './seller-list.component.scss'
})
export class SellerListComponent {
list: Seller[] = [];

sellerService = inject(SellerService);
router = inject(Router);
showModal = false;

findAll(){
  this.sellerService.findAll().subscribe({
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

edit(seller: Seller){
  console.log(seller);
  this.router.navigate(['/seller/edit', seller.id]);
}

deleteById(id: number){
  this.showModal = false;
    this.sellerService.deleteSeller(id).subscribe({
      next: () => {
        this.list = this.list.filter(seller => seller.id !== id);
      },
      error: erro => {
        alert('Erro ao deletar produto!');
        console.error(erro);
      }
    });
}

openConfirm() {
  this.showModal = true;
}

ngOnInit(){
  this.findAll();
}
}
