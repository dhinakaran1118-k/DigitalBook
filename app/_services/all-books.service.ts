import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';

//const API_URL = 'http://localhost:8091/api/auth/';
const API_URL = 'https://cmapfzknj3.execute-api.ap-northeast-1.amazonaws.com/UAT/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 
  'Access-Control-Allow-Origin':"*" })
};

@Injectable({
  providedIn: 'root'
})
export class AllBooksService {

  constructor(private http: HttpClient,private tokenStorageService: TokenStorageService) { }

  getbooks() : Observable<any> {
    //return this.http.get(API_URL +"reader/getallbooks");
    return this.http.get(API_URL +"viewallbooks");
  }
}
