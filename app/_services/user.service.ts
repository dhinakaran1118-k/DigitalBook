import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';

const API_URL = 'http://localhost:8091/api/test/';
//const BOOK_API = 'http://localhost:8091/api/auth/';
const BOOK_API = "https://cmapfzknj3.execute-api.ap-northeast-1.amazonaws.com/UAT/";

  const headers= new HttpHeaders({ 'Content-Type': 'application/json',
  'Access-Control-Allow-Origin':"*" });

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient,private tokenStorageService: TokenStorageService) { }

  search(title: any, authorName: any,category: any,publisher: any) : Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("authorName",authorName)
                             .append("category",category)
                             .append("publisher",publisher)
                             .append("title",title);

    return this.http.get(BOOK_API + 'searchbooks', { params:queryParams});
  }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'guest', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'author', { responseType: 'text' });
  }

  
}
