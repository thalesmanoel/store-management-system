import { SaleItem } from "./sale-item";

export class Sale {
  id?: number;
  items!: {
    productId: number;
    quantity: number;
    unitPrice: number;
  }[];
  total!: number;
}

