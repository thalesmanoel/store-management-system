import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { HeaderComponent } from "../header/header.component";

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
    createdAt: [new Date().toISOString()],
  });

  constructor(private fb: FormBuilder) {}

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    console.log('Seller Data:', this.form.value);
    // Aqui você pode enviar os dados ao backend
  }

  get f() {
    return this.form.controls;
  }
}
