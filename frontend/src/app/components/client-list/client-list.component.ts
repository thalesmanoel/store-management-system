import { Component, inject } from '@angular/core';
import { HeaderComponent } from "../header/header.component";
import { ClientService } from '../../services/client.service';
import { Client } from '../../models/client';
import { Router, RouterModule, } from '@angular/router';
import { ConfirmDeleteComponent } from "../../modals/confirm-delete/confirm-delete.component";
import { ErrorModalComponent } from "../../modals/error-modal/error-modal.component";

@Component({
  selector: 'app-client-list',
  standalone: true,
  imports: [HeaderComponent, RouterModule, ConfirmDeleteComponent, ErrorModalComponent],
  templateUrl: './client-list.component.html',
  styleUrl: './client-list.component.scss'
})
export class ClientListComponent {
  list: Client[] = [];
  deleteClientId: number | null = null;
  clientService = inject(ClientService);
  router = inject(Router);
  showModal = false;
  showErrorModal = false;
  errorMessage = '';

  findAll(){
    this.clientService.findAll().subscribe({
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

  edit(client: Client){
    this.router.navigate(['/client/edit/', client.id]);
  }

  deleteById() {
    if (this.deleteClientId !== null) {
      this.showModal = false;
      this.clientService.deleteClient(this.deleteClientId).subscribe({
        next: () => {
          this.list = this.list.filter(client => client.id !== this.deleteClientId);
        },
        error: erro => {
          this.errorMessage = 'Ocorreu algum erro.';
          this.showErrorModal = true;
          console.error(erro);
        }
      });
    } else {
      console.error("ID do cliente não encontrado");
    }
  }

  ngOnInit(){
    this.findAll();
  }

  openConfirm(clientId: number) {
    this.deleteClientId = clientId;
    this.showModal = true;
  }
}

