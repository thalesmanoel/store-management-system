import { Component, inject } from '@angular/core';
import { HeaderComponent } from "../header/header.component";
import { ClientService } from '../../services/client.service';
import { Client } from '../../models/client';
import { Router, RouterModule, } from '@angular/router';

@Component({
  selector: 'app-client-list',
  standalone: true,
  imports: [HeaderComponent, RouterModule],
  templateUrl: './client-list.component.html',
  styleUrl: './client-list.component.scss'
})
export class ClientListComponent {
lista: Client[] = [];

clientService = inject(ClientService);
router = inject(Router)

findAll(){
  this.clientService.findAll().subscribe({
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

 ngOnInit(){
  this.findAll();
 }
}

