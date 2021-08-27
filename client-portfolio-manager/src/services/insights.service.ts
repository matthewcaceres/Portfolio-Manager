import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {environment} from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InsightsService {

  indices: any= environment.services.getIndicesRemote;

  constructor(private http: HttpClient) { }

  getIndices(time:string) {
    return this.http.get(this.indices(time));
  }
  
}
