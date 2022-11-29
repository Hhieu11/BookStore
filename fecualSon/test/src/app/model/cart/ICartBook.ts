import {ICart} from './iCart';
import {Book} from '../book/book';

export interface ICartBook {
  cartBookId?: number;
  cartId?: ICart;
  bookId?: Book;
}
