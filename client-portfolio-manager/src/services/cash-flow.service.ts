import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class CashFlowService {

  constructor(private http:HttpClient) { }
  // we need a method of this service - in this case we call an API end-point
  getTransactions(id:number,time:string){ // all httpClient services are OBSERVABLES
    return this.http.get(`http://portfoliomanager-portfoliomanager.namdevops12.conygre.com/account/1/cash?time=${time}`)
  }

}
