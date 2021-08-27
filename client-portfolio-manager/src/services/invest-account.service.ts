import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { environment } from 'src/environments/environment';

const baseurl = 'http://portfoliomanager-portfoliomanager.namdevops12.conygre.com';

@Injectable({
  providedIn: 'root'
})
export class InvestAccountService {

  investAccount: any= environment.services.getInvestAccountRemote;
  accountTotal: any= environment.services.getAccountTotalRemote;

  constructor(private http: HttpClient) { }

  getInvestmentAccounts(id:number): Observable<any> {
    return this.http.get(this.investAccount(id));
  }
  
  addAccount(account: Object): Observable<Object> {
    return this.http.post(`${baseurl}/invest/add`, account);
  }

  deleteAccount(account: Object): Observable<any> {
    return this.http.delete(`${baseurl}/invest/delete`, {responseType: 'text'});
  }
  
  updateAccount(account: Object): Observable<Object>{
    return this.http.put(`${baseurl}/invest/update`, account);
  }
  
  AccountTotal(id:number): Observable<any>{
    return this.http.get(this.accountTotal(id));
  }








}
