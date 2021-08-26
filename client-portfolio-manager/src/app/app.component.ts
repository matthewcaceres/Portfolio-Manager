import { Component, OnInit } from '@angular/core';
import { NetWorthService } from 'src/services/net-worth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  user:any;
  constructor(private service: NetWorthService){
  }

  ngOnInit(){
    this.getUser();

  }
  getUser(){
    this.service.getUser(1).subscribe((user)=>{
      this.user = user;
    });
  }
  
  title = 'client-portfolio-manager';
}
