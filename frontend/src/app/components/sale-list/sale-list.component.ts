import { Component, inject, OnInit } from '@angular/core';
import { SaleItemView } from '../../models/sale-item-view';
import { SaleService } from '../../services/sale.service';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from "../header/header.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-sale-list',
  standalone: true,
  imports: [CommonModule, HeaderComponent],
  templateUrl: './sale-list.component.html',
  styleUrl: './sale-list.component.scss'
})
export class SaleListComponent implements OnInit {
  list: SaleItemView[] = [];
  showModal = false;
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
}
