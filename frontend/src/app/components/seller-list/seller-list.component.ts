import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Seller } from '../../models/seller';
import { SellerService } from '../../services/seller.service';
import { Router, RouterModule } from '@angular/router';
import { HeaderComponent } from "../header/header.component";

@Component({
  selector: 'app-seller-list',
  standalone: true,
  imports: [CommonModule, RouterModule, HeaderComponent],
  templateUrl: './seller-list.component.html',
  styleUrl: './seller-list.component.scss'
})
export class SellerListComponent {
lista: Seller[] = [];

sellerService = inject(SellerService);
router = inject(Router)

findAll(){
  this.sellerService.findAll().subscribe({
    next: lista => {
      this.lista = lista;
      console.log(this.lista);
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
  if (confirm("Tem certeza que deseja deletar esse registro?")) {
    this.sellerService.deleteSeller(id).subscribe({
      next: () => {
        this.lista = this.lista.filter(seller => seller.id !== id);
      },
      error: erro => {
        alert('Erro ao deletar produto!');
        console.error(erro);
      }
    });
  }
}

ngOnInit(){
  this.findAll();
}
}
