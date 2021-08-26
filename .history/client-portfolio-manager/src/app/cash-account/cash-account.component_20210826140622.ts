import { Component, OnInit } from '@angular/core';
import { CashAccountService } from 'src/services/cash-account.service';

@Component({
  selector: 'app-cash-account',
  templateUrl: './cash-account.component.html',
  styleUrls: ['./cash-account.component.css']
})
export class CashAccountComponent implements OnInit {

  apiResults: any = {};

  constructor(private cashAcctService: CashAccountService) { }

  ngOnInit(): void {
  
      this.makeCall(1);
  
  }

  makeCall(id:number) {
    this.cashAcctService.getCashAccounts(id).subscribe((data) => {
      console.log("Cash Accounts: ", data)
      this.apiResults = data;
    })
  }

}
