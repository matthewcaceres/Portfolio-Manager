import { Component, OnInit } from '@angular/core';
import { InsightsService } from 'src/services/insights.service';

@Component({
  selector: 'app-insights',
  templateUrl: './insights.component.html',
  styleUrls: ['./insights.component.css']
})
export class InsightsComponent implements OnInit {

  constructor(private indicesService: InsightsService) { }

  times = ['YTD', '3month', 'month', 'week', 'today'];
  // times: any = [{'YTD': "Year To Date"}, {'3month': "3 Months"}, {'month': "Month"}, {'week': "Week"}, {'today': "Today"}];
  timeParam = this.times[4];
  results: any = {}
  indices: any = []

  ngOnInit(): void {
    this.getIndices(this.times[4]);
  }

  getIndices(time:string) {
    this.indicesService.getIndices(time).subscribe((data:any) => {
      for (let key of Object.keys(data)) {
        let temp:object = {};
          temp = {ticker: key, percent: data[key]}
          this.indices.push(temp)
      }
    })
  }

}
