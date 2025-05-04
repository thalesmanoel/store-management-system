import { Component, inject } from '@angular/core';
import { HeaderComponent } from "../header/header.component";
import { CommonModule } from '@angular/common';
import { Product } from '../../models/product';
import { Client } from '../../models/client';
import { ClientService } from '../../services/client.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-purchase-summary',
  standalone: true,
  imports: [HeaderComponent, CommonModule],
  templateUrl: './purchase-summary.component.html',
  styleUrl: './purchase-summary.component.scss'
})
export class PurchaseSummaryComponent {
  list: Product[] = [];

  clients: Client[] = [];
  selectedClientId: number | null = null;

  clientService = inject(ClientService);
  purchaseService = inject(PurchaseService);
  router = inject(Router);
  route = inject(ActivatedRoute);

  ngOnInit() {
    const nav = this.router.getCurrentNavigation();
    const state = nav?.extras.state as { selectedProducts: Product[] };

    if (state?.selectedProducts) {
      this.list = state.selectedProducts;
    }

    this.loadClients();
  }

  loadClients() {
    this.clientService.findAll().subscribe({
      next: (data) => (this.clients = data),
      error: (err) => {
        console.error('Erro ao buscar clientes', err);
        alert('Erro ao carregar clientes');
      },
    });
  }

  getTotal(): number {
    return this.list.reduce((sum, p) => sum + (p.price * p.quantity), 0);
  }

  finalizePurchase() {
    if (!this.selectedClientId) {
      alert('Selecione um cliente antes de finalizar a compra');
      return;
    }

    const purchaseData = {
      clientId: this.selectedClientId,
      items: this.list.map(p => ({
        productId: p.id,
        quantity: p.quantity,
        price: p.price
      })),
      total: this.getTotal()
    };

    this.purchaseService.create(purchaseData).subscribe({
      next: () => {
        alert('Compra finalizada com sucesso!');
        this.router.navigate(['/']);
      },
      error: (err) => {
        console.error('Erro ao finalizar compra', err);
        alert('Erro ao finalizar compra');
      }
    });
  }

}
