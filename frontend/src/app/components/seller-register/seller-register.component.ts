import { SellerService } from './../../services/seller.service';
import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { HeaderComponent } from "../header/header.component";
import { Seller } from '../../models/seller';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-seller-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HeaderComponent],
  templateUrl: './seller-register.component.html',
  styleUrl: './seller-register.component.scss'
})
export class SellerRegisterComponent {

  form = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
    registrationDate: [new Date().toISOString()],
  });

  constructor(private fb: FormBuilder) {}

  seller: Seller = new Seller();

  sellerService = inject(SellerService);
  router = inject(Router);
  route = inject(ActivatedRoute);

  ngOnInit() {
      const id = this.route.snapshot.paramMap.get('id');
      if (id) {
        this.sellerService.findById(+id).subscribe({
          next: (seller) => {
            this.seller = seller;
            this.form.patchValue({
              name: seller.name,
              email: seller.email,
              password: seller.password,
              registrationDate: new Date(seller.registrationDate).toISOString()
            });
          },
          error: (erro) => {
            alert('Erro ao carregar os dados do sellere!');
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

      const sellerData: Seller = {
        id: this.seller.id ?? null,
        name: this.form.value.name!,
        email: this.form.value.email!,
        password: this.form.value.password!,
        registrationDate: new Date(this.form.value.registrationDate!)
      };

      const request = this.seller.id
        ? this.sellerService.updateSeller(sellerData, this.seller.id)
        : this.sellerService.registerSeller(sellerData);

      request.subscribe({
        next: () => {
          this.router.navigate(['/seller-list']);
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
