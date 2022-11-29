import { Component, OnInit } from '@angular/core';
import {BookService} from '../service/book/book.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../service/security/token-storage.service';
import {ICartBook} from '../model/cart/ICartBook';
import {ICart} from '../model/cart/iCart';
import { render } from 'creditcardpayments/creditCardPayments';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  listCart: ICartBook [] = [];
  accountId: number;
  totalCart = 0;
  paymentPayPal: any;
  cartDelete: ICartBook = {
    cartId: {},
    bookId: {}
  };
  constructor(private bookService: BookService,
              private router: Router, private activatedRoute: ActivatedRoute,
              private tokenStorageService: TokenStorageService) {
    this.paymentPayPal = render(
      {
        id: '#myPaypalButtons',
        currency: 'USD',
        value: this.totalCart.toString(),
        // value: '100 USD' ,
        onApprove: (details) => {
          alert('Thanh toan thanh cong');
        }
      }
    );
  }


  ngOnInit(): void {
    this.accountId = this.tokenStorageService.getUser().account.accountId;
    this.getListCart();
  }
  getListCart() {
    this.bookService.getAllCart(this.accountId).subscribe(data => {
       this.listCart = data;
       this.getTotal(this.listCart);
       console.log(data);
      },
      (error => {
        console.log(error);
      }));
  }
  getTotal(data: any) {
    let subs = 0;
    for (const item of data) {
      subs += (item.bookId.bookPrice * item.cartId.cartQuantity) ;
    }
    this.totalCart = subs;
  }


  validateInputCart(event: any, i: number, cart: ICartBook) {
    const qty = +event.target.value;
    console.log(qty);
    const total = qty * cart.bookId.bookPrice;
    this.bookService.changeQuantityCart(qty, total, cart.cartId.cartId).subscribe(
      data => {
        this.getListCart();
      },
      (error => {
        console.log(error);
      }));

  }

  delete(cartId: number) {
    this.bookService.deleteBookCart(cartId).subscribe(
      () => {
      },
      () => {
      },
      () => {
        this.getListCart();
        // this.getImportListNotPagination();
      });
  }
}
