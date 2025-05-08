import { Component, inject, OnInit } from '@angular/core';
import { SaleItemView } from '../../models/sale-item-view';
import { SaleService } from '../../services/sale.service';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from "../header/header.component";
import { ConfirmDeleteComponent } from "../../modals/confirm-delete/confirm-delete.component";
import { ErrorModalComponent } from "../../modals/error-modal/error-modal.component";

@Component({
  selector: 'app-sale-list',
  standalone: true,
  imports: [CommonModule, HeaderComponent, ConfirmDeleteComponent, ErrorModalComponent],
  templateUrl: './sale-list.component.html',
  styleUrl: './sale-list.component.scss'
})
export class SaleListComponent implements OnInit {
  list: SaleItemView[] = [];
  showModal = false;
  showErrorModal = false;
  errorMessage = '';
  deleteSaleId: number | null = null;
  saleService = inject(SaleService);

  ngOnInit(): void {
    this.saleService.getAllSaleItems().subscribe({
      next: data => this.list = data,
      error: err => {
        console.error('Erro ao buscar itens de venda', err);
        alert('Erro ao carregar os itens de venda');
      }
    });
  }

  deleteById() {
    if (this.deleteSaleId !== null) {
      this.saleService.deleteSale(this.deleteSaleId).subscribe({
        next: () => {
          this.list = this.list.filter(item => item.saleId !== this.deleteSaleId);
          this.showModal = false;
        },
        error: err => {
          console.error('Erro ao deletar venda', err);
          this.errorMessage = 'Erro ao deletar a venda';
          this.showErrorModal = true;
        }
      });
    }
  }

  openConfirm(saleId: number) {
    this.deleteSaleId = saleId;
    this.showModal = true;
  }
}
