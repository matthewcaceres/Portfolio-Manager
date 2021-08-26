import { Component, OnInit } from '@angular/core';
import { ChartOptions, ChartType } from 'chart.js';
import { Label, SingleDataSet } from 'ng2-charts';
import { NetWorthService } from '../../services/net-worth.service';
@Component({
  selector: 'app-net-worth',
  templateUrl: './net-worth.component.html',
  styleUrls: ['./net-worth.component.css'],
})
export class NetWorthComponent implements OnInit {
  timeFrames = ['LAST 7 DAYS', 'LAST 30 DAYS', 'LAST 3 MONTHS', 'YEAR TO DATE'];
  timeFrame = 0;
  difference: string = '';
  time: string = 'week';
  chartData: [] = [];
  chartType: ChartType = 'line';
  lineChartLabels: Label[] = [];
  lineChartOptions: ChartOptions = {
    responsive: true,
    legend: {
      display: false,
    },
    tooltips: {
      enabled: true,
    },
    elements: {
      point:{
          radius: 0
      }
  },
    scales: {
      // We use this empty structure as a placeholder for dynamic theming.
      xAxes: [
        {
          ticks: {
            autoSkip:true,
            maxTicksLimit: 12,
            beginAtZero: true,
            display: true,
          },
          gridLines:{
          }
        },
      ],
      yAxes: [
        {
          id: 'y-axis-0',
          position: 'right',
        },
      ],
    },
  };

  constructor(private netWorthService: NetWorthService) {}

  ngOnInit(): void {
    this.getData(this.time);
    this.setDates(7);
  }

  changeTime(): void {
    if (this.timeFrame == 3) {
      this.timeFrame = 0;
      this.getData('week');
      this.setDates(7);
    } else {
      this.timeFrame++;
      switch (this.timeFrame) {
        case 1:
          this.getData('month');
          this.setDates(28);
          break;
        case 2:
          this.getData('quarter');
          this.setDates(28 * 3);
          break;
        case 3:
          this.getData('year');
          let today: Date = new Date();
          let date2: Date = new Date('01/04/2021');
          let diff = today.getTime() - date2.getTime();
          let diffDays = Math.floor(diff / (1000 * 60 * 60 * 24));
          console.log(diffDays);
          this.setDates(diffDays);
          break;
      }
    }
  }

  getData(time: string) {
    this.netWorthService.getNetworth(1, time).subscribe(
      (data: any) => {
        this.chartData = data;
      },
      () => {},
      () => {
        let num =
          this.chartData[this.chartData.length - 1] -
          this.chartData[this.chartData.length - this.chartData.length];
          let percent = num/this.chartData[this.chartData.length - this.chartData.length] *100;
        if (num > 0) {
          this.difference = '+' + num.toFixed(2) +" (" +percent.toFixed(2)+"%)";
        } else this.difference = '-' + num.toFixed(2) + num.toFixed(2) +" (" +percent.toFixed(2)+"%)";;
      }
    );
  }
  setDates(days: number) {
    this.lineChartLabels = [];
    let day = new Date();
    let oldDay = new Date();
    oldDay.setDate(day.getDate() - days);
    while (days >= 0) {
      this.lineChartLabels.push(oldDay.toLocaleDateString());
      oldDay.setDate(oldDay.getDate() + 1);
      days--;
    }
  }
}
