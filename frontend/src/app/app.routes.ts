import { Routes } from '@angular/router';
import { MenuComponent } from './components/menu/menu.component';
import { ClientRegisterComponent } from './components/client-register/client-register.component';
import { SellerRegisterComponent } from './components/seller-register/seller-register.component';
import { ProductRegisterComponent } from './components/product-register/product-register.component';
import { SellerListComponent } from './components/seller-list/seller-list.component';
import { ClientListComponent } from './components/client-list/client-list.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { BuyListComponent } from './components/buy-list/buy-list.component';
import { SaleListComponent } from './components/sale-list/sale-list.component';

export const routes: Routes = [
  {path: "", redirectTo: "menu", pathMatch: 'full'},
  {path: "menu", component: MenuComponent},
  {path: "client-register", component: ClientRegisterComponent},
  {path: "seller-register", component: SellerRegisterComponent},
  {path: "product-register", component: ProductRegisterComponent},
  {path: "seller-list", component: SellerListComponent},
  {path: "client-list", component: ClientListComponent},
  {path: "product-list", component: ProductListComponent},
  {path: "buy-list", component: BuyListComponent},
  {path: "sale-list", component: SaleListComponent},
  {path: "seller/edit/:id", component: SellerRegisterComponent},
  {path: "client/edit/:id", component: ClientRegisterComponent},
];
