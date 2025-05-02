import { Routes } from '@angular/router';
import { MenuComponent } from './components/menu/menu.component';
import { ClientRegisterComponent } from './components/client-register/client-register.component';
import { SellerRegisterComponent } from './components/seller-register/seller-register.component';
import { ProductRegisterComponent } from './components/product-register/product-register.component';

export const routes: Routes = [
  {path: "", redirectTo: "menu", pathMatch: 'full'},
  {path: "menu", component: MenuComponent},
  {path: "client-register", component: ClientRegisterComponent},
  {path: "seller-register", component: SellerRegisterComponent},
  {path: "product-register", component: ProductRegisterComponent},
  {path: "product-register", component: ProductRegisterComponent},
];
