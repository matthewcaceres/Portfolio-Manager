import { Component, OnInit } from '@angular/core';
import {  ChartOptions, ChartType } from 'chart.js';
import { Label, SingleDataSet } from 'ng2-charts';
import {NetWorthService} from '../../services/net-worth.service';
@Component({
  selector: 'app-net-worth',
  templateUrl: './net-worth.component.html',
  styleUrls: ['./net-worth.component.css']
})

export class NetWorthComponent implements OnInit {
  time: string = "month";
  chartData:SingleDataSet = [];
  chartType: ChartType = "line"
   lineChartLabels: Label[] = []
   lineChartOptions: (ChartOptions) = {
    responsive: true,
    legend: {
      display: false
  },
    scales: {
      // We use this empty structure as a placeholder for dynamic theming.
      xAxes: [{ticks: {
        beginAtZero: true
      ,display: false}}],
      yAxes: [
        {
          id: 'y-axis-0',
          position: 'left',
        },
        {
          id: 'y-axis-1',
          position: 'right',
          gridLines: {
            color: 'rgba(255,0,0,0.3)',
          },
          ticks: {
            fontColor: 'red',
          }
        }
      ]
    }
  };
  
  constructor(private netWorthService: NetWorthService) { }

  ngOnInit(): void {
    this.getData(this.time);
    this.setDates(28);
    
  }
  getData(time:string){
    this.netWorthService.getNetworth(1,time)
    .subscribe((data:any)=>{
      this.chartData=data;
  })}
  setDates(days:number){
    let day = new Date();
    let oldDay = new Date();
    oldDay.setDate(day.getDate()-days)
    while(days>=0){
      this.lineChartLabels.push(oldDay.toLocaleDateString());
      oldDay.setDate(oldDay.getDate()+1);
      days--;
    }
  }
}
