import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { HeaderComponent } from "../header/header.component";
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';
import { ActivatedRoute, Router } from '@angular/router';

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
    registrationDate: [new Date().toISOString()],
    stock: this.fb.control<number | null>(null, [Validators.required, Validators.min(0)]),
    price: this.fb.control<number | null>(null, [Validators.required, Validators.min(0)]),
  });

  constructor(private fb: FormBuilder) {}

   product: Product = new Product();

    productService = inject(ProductService);
    router = inject(Router);
    route = inject(ActivatedRoute);

    ngOnInit() {
        const id = this.route.snapshot.paramMap.get('id');
        if (id) {
          this.productService.findById(+id).subscribe({
            next: (product) => {
              this.product = product;
              this.form.patchValue({
                name: product.name,
                registrationDate: new Date(product.registrationDate).toISOString(),
                stock: product.stock,
                price: product.price
              });
            },
            error: (erro) => {
              alert('Erro ao carregar os dados do producte!');
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

        const productData: Product = {
          id: this.product.id ?? null,
          name: this.form.value.name!,
          registrationDate: new Date(this.form.value.registrationDate!),
          stock: this.form.value.stock!,
          price: this.form.value.price!,
        };

        const request = this.product.id
          ? this.productService.updateProduct(productData, this.product.id)
          : this.productService.registerProduct(productData);

        request.subscribe({
          next: () => {
            this.router.navigate(['/product-list']);
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
