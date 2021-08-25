import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CashAccountService {

  constructor(private http: HttpClient) { }

  getCashAccounts() {
    return this.http.get('http://localhost:8080/cash_account')
  }
  
}
