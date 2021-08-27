import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {environment} from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MarketMoversService {

  marketMovers: any= environment.services.getMarketMoversRemote;

  constructor(private http: HttpClient) { }

  getMarketMoversData(id:number) {
    return this.http.get(this.marketMovers(id))
  }
  
}
