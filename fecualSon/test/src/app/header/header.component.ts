import { Component, OnInit, Injectable } from '@angular/core';
import {Router} from '@angular/router';
import {TokenStorageService} from '../service/security/token-storage.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
@Injectable({
  providedIn: 'root'
})
export class HeaderComponent implements OnInit {

  private roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showAccountantBoard = false;
  showSellBoard = false;
  userName: string;
  constructor(private tokenStorageService: TokenStorageService,
              private router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      this.userName = this.tokenStorageService.getUser().account.username;
      this.roles = this.tokenStorageService.getUser().account.roles[0].roleName;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      // this.showAccountantBoard = this.roles.includes('ROLE_ACCOUNTANT');
      this.showSellBoard = this.roles.includes('ROLE_USER');

      console.log('roles: ' + this.roles);
    }
  }
  logout() {
    this.tokenStorageService.signOut();
    window.location.assign('/home');
    // this.router.navigateByUrl('/home');
    this.router.navigateByUrl('/home');
  }
}
