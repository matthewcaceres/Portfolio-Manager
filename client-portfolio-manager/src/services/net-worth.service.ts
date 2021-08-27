import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import {environment} from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NetWorthService {

  netWorth: any= environment.services.getNetWorthRemote;
  user: any= environment.services.getUserRemote;

  constructor(private http:HttpClient) { }
  // we need a method of this service - in this case we call an API end-point
  getNetworth(id:number,time:string){ // all httpClient services are OBSERVABLES
    return this.http.get(this.netWorth(id, time));
  }

   getUser(id:number){ // all httpClient services are OBSERVABLES
    return this.http.get(this.user(id));
  }

}
