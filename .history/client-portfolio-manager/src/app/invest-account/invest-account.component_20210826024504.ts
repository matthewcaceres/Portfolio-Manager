import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import { InvestAccountService } from 'src/services/invest-account.service';
import {InvestAccount} from './invest-account/app';

@Component({
  selector: 'app-invest-account',
  templateUrl: './invest-account.component.html',
  styleUrls: ['./invest-account.component.css']
})
export class InvestAccountComponent implements OnInit {


  
  constructor(private router: Router, private service: InvestAccountService) { }
  
  getAccounts(id:number) {
    this.service.getInvestmentAccounts().subscribe(data => {
      this.accounts = data;
    });
  }

  addAccount(): void {
    this.router.navigate(['add'])
      .then((e) => {
        if (e) {
          console.log("Account has been added successfully!");
        } else {
          console.log("failed to add account!");
        }
      });
  };



  ngOnInit(): void {
    this.router.events.subscribe(value => {
      this.getAccounts();
    });
  }



}
