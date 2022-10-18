import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

const endpoint = 'https://jsonplaceholder.typicode.com/posts';
@Injectable({
  providedIn: 'root'
})

export class PostService {

  private dataSource: BehaviorSubject<string> = new BehaviorSubject<string>('Initial Value');
  data: Observable<string> = this.dataSource.asObservable();
  
  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<any> {
    return this.http.get(endpoint);
  }

  sendData(data: string) {
    this.dataSource.next(data);
  }

}
