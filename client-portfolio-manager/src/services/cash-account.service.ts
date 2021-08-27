import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../environments/environment';

const baseurl = 'http://portfoliomanager-portfoliomanager.namdevops12.conygre.com';
@Injectable({
  providedIn: 'root'
})
export class CashAccountService {

  cashAccount: any= environment.services.getCashAccountRemote;

  constructor(private http: HttpClient) { }

  getCashAccounts(id:number): Observable<any> {
    return this.http.get(this.cashAccount(id));
  }

  addAccount(account: Object): Observable<Object> {
    return this.http.post(`${baseurl}/cash_account/add`, account);
  }

  deleteAccount(account: Object): Observable<any> {
    return this.http.delete(`${baseurl}/cash_account/delete`, {responseType: 'text'});
  }

  updateAccount(account: Object): Observable<Object>{
    return this.http.put(`${baseurl}/cash_account/update`, {responseType: 'text'});
  }


  
}
