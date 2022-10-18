import { Component, OnInit } from '@angular/core';
import { PostService } from './post.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  POSTS: any;
  page: number = 1;
  count: number = 0;
  tableSize: number = 7;
  tableSizes: any = [3, 6, 9, 12];
  currentItem : string = 'Television'; // old input/output way. 

  constructor(private postService: PostService) {}
  ngOnInit(): void {
    this.fetchPosts();
    this.getData();
  }
  fetchPosts(): void {
    // this.postService.getAllPosts().subscribe(
    //   (response) => {
    //     this.POSTS = response;
    //     console.log(response);
    //   },
    //   (error) => {
    //     console.log(error);
    //   }
    // );
    this.postService.getAllPosts().subscribe({
      next: (response) => {
        this.POSTS = response;
        console.log(response);
      }, 
      error: (error) => console.log(error),
      complete: () => console.log('complete')
    });
      
    
  }
  onTableDataChange(event: any) {
    this.page = event;
    this.fetchPosts();
  }
  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.fetchPosts();
  }


  newItemDetail(editItem : string) {
    this.currentItem = editItem;
    console.log("modified: " + this.currentItem);
  }

  getData() {
    this.postService.data.subscribe(response => {
      this.currentItem = response;
      console.log(response);  // you will receive the data from sender component here.
    });
  }

}
