import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import { InvestAccountService } from 'src/services/invest-account.service';
import {InvestAccount} from 'src/app/invest-account';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-invest-account',
  templateUrl: './invest-account.component.html',
  styleUrls: ['./invest-account.component.css']
})
export class InvestAccountComponent implements OnInit {
  public accounts: InvestAccount[];
  public editEmployee: InvestAccount;
  public deleteAccount: InvestAccount;
  ngOnInit(): void {
    this.router.events.subscribe(value => {
      this.getAccounts();
    });
  }


  
  constructor(private router: Router, private service: InvestAccountService) { }
  
  public getAccounts(id:number): void {
    this.service.getInvestmentAccounts(id).subscribe(
      (response: InvestAccount[]) => {
        this.accounts = response;
        console.log(this.accounts);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public addInvestAccount(addForm: NgForm): void {
    document.getElementById('add-account-form').click();
    this.service.addAccount(addForm.value).subscribe(
      (response: InvestAccount) => {
        console.log(response);
        this.getAccounts();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public updateAccount(account: InvestAccount): void {
    this.service.updateAccount(account).subscribe(
      (response: InvestAccount) => {
        console.log(response);
        this.getAccounts();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public deleteAccount(account: InvestAccount): void {
    this.service.deleteAccount(account).subscribe(
      (response: void) => {
        console.log(response);
        this.getAccounts();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(account: InvestAccount, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#add');
    }
    if (mode === 'edit') {
      this.editEmployee = employee;
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if (mode === 'delete') {
      this.deleteEmployee = employee;
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }
    container.appendChild(button);
    button.click();
  }


 



}
