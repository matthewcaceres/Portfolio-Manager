import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MarketMoversService {

  constructor(private http: HttpClient) { }

  getMarketMoversData(id:number) {
    return this.http.get(`http://localhost:8080/account/${id}//movers`);
    // return this.http.get(`http://portfoliomanager-portfoliomanager.namdevops12.conygre.com/account/${id}/movers/`);
  }
  
}
