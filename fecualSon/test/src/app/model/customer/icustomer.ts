import {Iaccount} from '../account/iaccount';

export interface Icustomer {
  customerId ?: number;
  customerName?: string;
  customerPhone?: string;
  customerEmail?: string;
  customerImage?: string;
  customerAddress?: string;
  customerAccountId?: Iaccount;

}
