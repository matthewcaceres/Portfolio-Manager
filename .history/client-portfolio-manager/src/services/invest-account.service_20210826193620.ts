import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

const baseurl = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class InvestAccountService {

  constructor(private http: HttpClient) { }

  getInvestmentAccounts(id:number): Observable<any> {
    return this.http.get(`${baseurl}/account/${id}/invest`);
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

  AccountTotal(account: Object): Observable<Object>{
    return this.http.put(`${baseurl}/invest/total/{id}`, account);
  }








}
