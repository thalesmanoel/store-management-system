import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { HeaderComponent } from "../header/header.component";

@Component({
  selector: 'app-product-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HeaderComponent],
  templateUrl: './product-register.component.html',
  styleUrl: './product-register.component.scss'
})
export class ProductRegisterComponent {
  form = this.fb.group({
    name: ['', Validators.required],
    stock: [null, [Validators.required, Validators.min(0)]],
    price: [null, [Validators.required, Validators.min(0)]],
    createdAt: [new Date().toISOString()],
  });

  constructor(private fb: FormBuilder) {}

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    console.log('Product Data:', this.form.value);
    // Aqui você pode enviar os dados ao backend
  }

  get f() {
    return this.form.controls;
  }
}
