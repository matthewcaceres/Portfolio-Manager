import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import {environment} from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CashFlowService {

  cashFlow: any= environment.services.getCashFlowRemote;

  constructor(private http:HttpClient) { }
  // we need a method of this service - in this case we call an API end-point
  getTransactions(id:number,time:string){ // all httpClient services are OBSERVABLES
    return this.http.get(this.cashFlow(id, time));
  }

}
