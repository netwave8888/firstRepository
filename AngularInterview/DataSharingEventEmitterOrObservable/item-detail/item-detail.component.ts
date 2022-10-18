import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { PostService } from '../post.service';

@Component({
  selector: 'app-item-detail',
  templateUrl: './item-detail.component.html',
  styleUrls: ['./item-detail.component.css']
})
export class ItemDetailComponent implements OnInit {

  @Input() item = '';
  @Output() newItemEvent = new EventEmitter<string>();

  text :string = "default value";
  constructor(private postService : PostService) { }

  ngOnInit(): void {
  }

  editItemDetail(value:string) {
    this.newItemEvent.emit(value);
  }

  keyup(value : string) {
    this.text = value;
  }

  sendNewData(data: string) {
    this.text = data;
    this.postService.sendData(data);
  }
}


