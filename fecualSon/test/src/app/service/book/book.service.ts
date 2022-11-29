import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Book} from '../../model/book/book';
import {Category} from '../../model/book/category';
import {ICartBook} from '../../model/cart/ICartBook';
import {ICart} from '../../model/cart/iCart';


@Injectable({
  providedIn: 'root'
})
export class BookService {
  readonly API_URL = 'http://localhost:8082/api/book';
  readonly API_URL1 = 'http://localhost:8082/api/cart';
  constructor(private  http: HttpClient) {}

getAllBook(page: number): Observable<Book[]> {
return this.http.get<Book[]>(this.API_URL + '/book-list?page=' + page);
}

  findBookById(id: number): Observable<Book> {
    return this.http.get<Book>(`${this.API_URL}/detail/${id}`);
  }
  findBookSameCate(categoryId: number): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.API_URL}/${categoryId}`);

  }
  getAllCategory(): Observable<Category[]> {
    return this.http.get<Category[]>(this.API_URL);
  }
  getAllBookByCategoryId(id: number, page: number): Observable<Book[]> {
    return this.http.get<Book[]>(this.API_URL + `/category/${id}?page=` + page);
  }
  searchBook(name: string, page: number): Observable<Book[]> {
    return this.http.get<Book[]>(this.API_URL + `/search/${name}?page=` + page);
  }

  addBookIntoCart(book: Book, accountId: number): Observable<Book[]> {
    return this.http.post<Book[]>(this.API_URL1 + `/addBookIntoCart/` + accountId, book);
  }
  getAllCart( accountId: number): Observable<ICartBook[]> {
    return this.http.get<ICartBook[]>(this.API_URL1 + `/list-cart-book/${accountId}` );
  }
  changeQuantityCart(quantity: number, total: number, cartId: number): Observable<void> {
    // @ts-ignore
    return this.http.post<void>(`${this.API_URL1}/changeQuantityCart?quantity=${quantity}&money=${total}&cartId=${cartId}`);
  }

  deleteBookCart(cartId: number): Observable<void> {
    return this.http.delete<void>(this.API_URL1 + '/cart-delete?cardId=' + cartId);
  }


}
