import { Component, OnInit } from '@angular/core';
import {Book} from '../model/book/book';
import {BookService} from '../service/book/book.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  page = 1;
  size: number;
  totalItems: number;
  bookList: Book[] = [];
  indexPage: number;
  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.findAll(this.page);
  }
  findAll(page: number) {
    this.page = page;
    this.bookService.getAllBook(this.page - 1).subscribe(
      (data: any) => {
        console.log(data);
        this.bookList = data.content;
        this.size = data.size;
        this.totalItems = data.totalElements;
      }
    );
  }


}
