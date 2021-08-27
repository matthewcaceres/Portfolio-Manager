import { Component, OnInit } from '@angular/core';
import { CashAccountService } from 'src/services/cash-account.service';

@Component({
  selector: 'app-cash-account',
  templateUrl: './cash-account.component.html',
  styleUrls: ['./cash-account.component.css']
})
export class CashAccountComponent implements OnInit {

  accounts: any = [];
  sum:number=0;

  constructor(private cashAcctService: CashAccountService) { }

  ngOnInit(): void {
  
      this.makeCall(1);
  
  }

  makeCall(id:number) {
    this.cashAcctService.getCashAccounts(id).subscribe((data) => {
      console.log("Cash Accounts: ", data)
      this.accounts = data;
    },()=>{},()=>{
      for(let i = 0 ; i<this.accounts.length;i++){
        this.sum+=this.accounts[i].total;
      }
    })
  }

}
