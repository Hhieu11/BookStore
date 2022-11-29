import { Component, OnInit } from '@angular/core';
import {Book} from '../model/book/book';
import {BookService} from '../service/book/book.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {TokenStorageService} from '../service/security/token-storage.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  page = 1;
  size: number;
  id: number;
  totalItems: number;
  bookList: Book[] = [];
  name: string;
  indexPage: number;
  accountId: number;
  constructor(private bookService: BookService,
              private router: Router, private activatedRoute: ActivatedRoute,
              private tokenStorageService: TokenStorageService,
              private toastrService: ToastrService) { }

  ngOnInit(): void {
    this.accountId = this.tokenStorageService.getUser().account.accountId;
    this.activatedRoute.paramMap.subscribe((param: ParamMap) => {
      this.id = +param.get('id');
      this.getAllBookByCategoryId(this.id, this.page);
      this.name = param.get('searchKey');
      this.searchBook(this.name, this.page);

    });
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


  getAllBookByCategoryId(id: number, page: number) {
    this.page = page;
    this.bookService.getAllBookByCategoryId(id, this.page - 1).subscribe((data: any) => {
        this.bookList = data.content;
        this.size = data.size;
        this.totalItems = data.totalElements;
      },
      () => {
        this.page--;
        this.getAllBookByCategoryId(id, this.page);
      },
      () => {
      }
    );
  }
  searchBook(name: string, page: number) {
    this.page = page;
    this.bookService.searchBook(name, this.page - 1).subscribe((data: any) => {
        this.bookList = data.content;
        this.size = data.size;
        this.totalItems = data.totalElements;
      },
      () => {
        this.page--;
        this.searchBook(name, this.page);
      },
      () => {
      }
    );
  }

  addToCart(book: Book) {
    this.bookService.addBookIntoCart(book, this.accountId).subscribe(data => {
        this.toastrService.success('Them moi thanh cong!', 'Thong bao: ');
    },
      (error => {
        console.log(error);
      }));
  }

}
