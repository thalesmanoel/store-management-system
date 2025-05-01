import { Routes } from '@angular/router';
import { MenuComponent } from './components/menu/menu.component';
import { ClientRegisterComponent } from './components/client-register/client-register.component';

export const routes: Routes = [
  {path: "", redirectTo: "menu", pathMatch: 'full'},
  {path: "menu", component: MenuComponent},
  {path: "client-register", component: ClientRegisterComponent}
];
