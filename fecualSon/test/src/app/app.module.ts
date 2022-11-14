import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';

import {Router, RouterLink, RouterModule} from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { CartComponent } from './cart/cart.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {JWT_OPTIONS, JwtHelperService} from '@auth0/angular-jwt';
import {authInterceptorProviders} from './heplers/auth.interceptor';
import {APP_BASE_HREF} from '@angular/common';
import { AccessDeniedComponent } from './security/access-denied/access-denied/access-denied.component';
import { LoginComponent } from './security/login-jwt/login.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { BookdetailComponent } from './book/booklist/bookdetail/bookdetail.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    CartComponent,
    AccessDeniedComponent,
    LoginComponent,
    BookdetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule,
    NgxPaginationModule,
    FormsModule


  ],
  providers: [ authInterceptorProviders,
    JwtHelperService,
    {provide: JWT_OPTIONS, useValue: JWT_OPTIONS},
    { provide: APP_BASE_HREF, useValue: '/'}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
