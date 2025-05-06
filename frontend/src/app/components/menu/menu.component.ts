import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import {RouterModule} from '@angular/router';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [HeaderComponent, RouterModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent {

}
