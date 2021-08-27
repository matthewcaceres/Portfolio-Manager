import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InvestAccountService } from 'src/services/invest-account.service';
import { InvestAccount } from 'src/app/invest-account';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

class Account {
  id: number;
  total: number;
  constructor() {
    this.id = 0;
    this.total = 0;
  }
}
@Component({
  selector: 'app-invest-account',
  templateUrl: './invest-account.component.html',
  styleUrls: ['./invest-account.component.css'],
})
export class InvestAccountComponent implements OnInit {
  accounts: any = [];

  
  sum:number=0;

  constructor(private router: Router, private service: InvestAccountService) {}

  ngOnInit(): void {
    this.getAccounts(1);
  }

  getAccounts(id: number) {
    this.service.getInvestmentAccounts(id).subscribe(
      (data) => {
        this.accounts = data;
      },
      () => {},
      () => {
        console.log(this.accounts);
        for (let i = 0; i < this.accounts.length; i++) {
          this.service.AccountTotal(this.accounts[i].id).subscribe((data: any) => {
            this.accounts[i].total = data;
            this.sum+=data;
          });
        }
      }
    );
  }
}
