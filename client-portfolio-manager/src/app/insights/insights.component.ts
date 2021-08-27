import { Component, OnInit } from '@angular/core';
import { InsightsService } from 'src/services/insights.service';
import { StructuredType } from 'typescript';

@Component({
  selector: 'app-insights',
  templateUrl: './insights.component.html',
  styleUrls: ['./insights.component.css']
})
export class InsightsComponent implements OnInit {

  constructor(private indicesService: InsightsService) { }

  times:string[]= ['YTD', '3month', 'month', 'week', 'today'];
  timeDisplay:string[] = ['Year to date', '3 Months', 'Month', 'Week','Today'];
  tickers=new Map();
  timeParam = this.timeDisplay[4];
  results:number[]= []
  indices: any = []

  ngOnInit(): void {
    this.tickers.set('^GSPC','S&P')
    this.tickers.set('^DJI','DOW')
    this.tickers.set('^IXIC','NAS')
    this.tickers.set('^TNX','10B')
    this.getIndices(this.times[4]);
    
  }

  getIndices(time:string) {
    let newTime:string="";
    for(let i=0;i<this.timeDisplay.length;i++){
      if(this.timeDisplay[i]===time){
        newTime=this.times[i];
      }
    }
    
    this.indices=[];
    this.results=[];
    this.indicesService.getIndices(newTime).subscribe((data:any) => {

      for (let key of Object.keys(data)) {
        let temp:object = {};
          temp = {ticker: key, percent: Number.parseFloat(data[key]).toFixed(2)}
          this.indices.push(temp)
      }
     this.tickers.forEach((val,key)=>{
       for(let i =0; i<this.indices.length;i++){
         if(key==this.indices[i].ticker){
            this.results.push(this.indices[i].percent);
         }
       }
     });
     console.log(this.results)
    })
  }

}
