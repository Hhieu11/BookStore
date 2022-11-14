import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Book} from '../../model/book/book';


@Injectable({
  providedIn: 'root'
})
export class BookService {
  readonly API_URL = 'http://localhost:8080/api/book';
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
}
