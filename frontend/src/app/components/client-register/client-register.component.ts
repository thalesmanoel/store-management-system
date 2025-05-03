import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { HeaderComponent } from "../header/header.component";
import { Client } from '../../models/client';
import { ClientService } from '../../services/client.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-client-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HeaderComponent],
  templateUrl: './client-register.component.html',
  styleUrl: './client-register.component.scss'
})
export class ClientRegisterComponent {

  form = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    cpf: ['', Validators.required],
    password: ['', Validators.required],
  });

  constructor(private fb: FormBuilder) {}

  client: Client = new Client();

  clientService = inject(ClientService);
  router = inject(Router);
  route = inject(ActivatedRoute);

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.clientService.findById(+id).subscribe({
        next: (client) => {
          this.client = client;
          this.form.patchValue({
            name: client.name,
            email: client.email,
            cpf: client.cpf,
            password: client.password
          });
        },
        error: (erro) => {
          alert('Erro ao carregar os dados do cliente!');
          console.error(erro);
        },
      });
    }
  }

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const clientData: Client = {
      id: this.client.id ?? null,
      name: this.form.value.name!,
      email: this.form.value.email!,
      cpf: this.form.value.cpf!,
      password: this.form.value.password!,
    };

    const request = this.client.id
      ? this.clientService.updateClient(clientData, this.client.id)
      : this.clientService.registerClient(clientData);

    request.subscribe({
      next: () => {
        this.router.navigate(['/client-list']);
      },
      error: erro => {
        alert('Erro ao salvar os dados!');
        console.error(erro);
      },
    });
  }

  get f() {
    return this.form.controls;
  }
}
