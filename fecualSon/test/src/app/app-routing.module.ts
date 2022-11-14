import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {HomeComponent} from './home/home.component';
import {CartComponent} from './cart/cart.component';
import {LoginComponent} from './security/login-jwt/login.component';
import {FooterComponent} from './footer/footer.component';
import {BookdetailComponent} from './book/booklist/bookdetail/bookdetail.component';


const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
   {path: 'home', component: HomeComponent},
  {path: 'cart', component: CartComponent},
  {path: 'login', component: LoginComponent},
  {path: 'footer', component: FooterComponent},
  {path: 'detail/:id', component: BookdetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
