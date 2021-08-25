import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class NetWorthService {

  constructor(private http:HttpClient) { }
  // we need a method of this service - in this case we call an API end-point
  getNetworth(id:number,time:string){ // all httpClient services are OBSERVABLES
    return this.http.get(`http://portfoliomanager-portfoliomanager.namdevops12.conygre.com/account/${id}/networth?time=${time}`)
  }

}
