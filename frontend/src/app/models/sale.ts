import { SaleItem } from "./sale-item";

export class Sale {
  id?: number;
  clientId!: number;
  sellerId!: number;
  items!: {
    productId: number;
    quantity: number;
    unitPrice: number;
  }[];
  total!: number;
}

