import {Component, OnInit} from '@angular/core';
import {BookService} from '../../../service/book/book.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Book} from '../../../model/book/book';

@Component({
  selector: 'app-booklist',
  templateUrl: './bookdetail.component.html',
  styleUrls: ['./bookdetail.component.css']
})
export class BookdetailComponent implements OnInit {

  books: Book = {};
  book1: Book [] = [];
  id: number;
  categoryId: number;

  constructor(private bookService: BookService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(paramMap => {
      this.id = Number(paramMap.get('id'));
      this.bookService.findBookById(this.id).subscribe(book => {
        this.books = book;
        this.bookService.findBookSameCate(this.books.categoryId.categoryId).subscribe(
          (book1) => {
            this.book1 = book1;
          }
        );
      });
    });

    // this.getBookSameCate();
  }

//   getBookSameCate() {
//   this.bookService.findBookSameCate().subscribe(data => {
// this.book1 = data;
// console.log(this.book1);
//   });
//   }


}
