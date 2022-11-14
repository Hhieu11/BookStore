import {Category} from './category';
import {Author} from './author';
import {Promotion} from './promotion';

export interface Book {
  bookId?: number;
  bookCode?: string;
  bookName?: string;
  bookImage?: string;
  bookContent?: string;
  bookPrice?: number;
  bookTranslator?: string;
  bookTotalPage?: number;
  bookSize?: number;
  bookPublishDate?: string;
  bookQuantity?: number;
  bookFlag?: boolean;
  bookPublisher?: string;
  categoryId?: Category;
  bookAuthorId?: Author;
  bookPromotionId?: Promotion;


}
