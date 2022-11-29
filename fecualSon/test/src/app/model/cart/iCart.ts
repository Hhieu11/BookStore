import {Iaccount} from '../account/iaccount';

export interface ICart {
  cartId?: number;
  cartCode?: string;
  cartQuantity?: number;
  cartTotalMoney?: number;
  cartStatus?: boolean;
  cartAccountId?: Iaccount;
  cartPurchaseDate?: string;

}
