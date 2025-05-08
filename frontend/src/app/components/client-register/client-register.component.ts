import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { HeaderComponent } from "../header/header.component";
import { Client } from '../../models/client';
import { ClientService } from '../../services/client.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ErrorModalComponent } from "../../modals/error-modal/error-modal.component";

@Component({
  selector: 'app-client-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HeaderComponent, ErrorModalComponent],
  templateUrl: './client-register.component.html',
  styleUrl: './client-register.component.scss'
})
export class ClientRegisterComponent {

  form = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    cpf: ['', Validators.required],
    password: [''],
  });

  constructor(private fb: FormBuilder) {}

  client: Client = new Client();
  isEditMode = false;
  showModal = false;
  showErrorModal = false;
  errorMessage = '';

  clientService = inject(ClientService);
  router = inject(Router);
  route = inject(ActivatedRoute);

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.clientService.findById(+id).subscribe({
        next: (client) => {
          this.client = client;
          this.form.patchValue({
            name: client.name,
            email: client.email,
            cpf: client.cpf,
          });
        },
        error: (erro) => {
          this.errorMessage = 'Ocorreu algum erro.';
          this.showErrorModal = true;
          console.error(erro);
        },
      });
    } else {
      this.form.get('password')?.setValidators([Validators.required]);
      this.form.get('password')?.updateValueAndValidity();
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
      password: this.form.value.password || undefined,
    };

    const request = this.isEditMode
      ? this.clientService.updateClient(clientData, this.client.id!)
      : this.clientService.registerClient(clientData);

    request.subscribe({
      next: () => {
        this.router.navigate(['/client-list']);
      },
      error: erro => {
        if (erro.status === 409) {
            this.errorMessage = 'E-mail ou CPF já existente!';
            this.showErrorModal = true;
        } else {
            this.errorMessage = 'Ocorreu algum erro.';
            this.showErrorModal = true;
        }
        console.error(erro);
      },
    });
  }

  get f() {
    return this.form.controls;
  }
}
