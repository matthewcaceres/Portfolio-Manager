import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class InsightsService {

  constructor(private http: HttpClient) { }

  getIndices(time:string) {
    // return this.http.get(`http://localhost:8080/account/indices?time=${time}`);
    return this.http.get(`http://portfoliomanager-portfoliomanager.namdevops12.conygre.com/account/indices?time=${time}`);
  }
  
}
