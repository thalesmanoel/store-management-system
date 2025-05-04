import { SaleItem } from "./sale-item";

export class Sale {
  id?: number;
  totalPrice?: number;
  clientId!: number; // Mantém apenas o clientId
  items!: SaleItem[];
}

