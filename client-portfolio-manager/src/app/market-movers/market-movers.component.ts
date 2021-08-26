import { Component, OnInit } from '@angular/core';
import { MarketMoversService } from 'src/services/market-movers.service';

@Component({
  selector: 'app-market-movers',
  templateUrl: './market-movers.component.html',
  styleUrls: ['./market-movers.component.css']
})
export class MarketMoversComponent implements OnInit {

  moversResults: any = {}
  marketLosers: any = []
  marketGainers: any = []

  constructor(private marketService: MarketMoversService) { }

    ngOnInit(): void {
      this.getMarketMovers(1);
  }

  getMarketMovers(id: number) {
    this.marketService.getMarketMoversData(id).subscribe((data) => {
      this.moversResults = data
      for (let key of Object.keys(this.moversResults)) {
        let temp:object = {};
        if( this.moversResults[key] < 0) {
          temp = {ticker: key, percent: this.moversResults[key]}
          this.marketLosers.push(temp)
        } else {
          temp = {ticker: key, percent: this.moversResults[key]}
          this.marketGainers.push(temp)
        }
      }
    })
  }

}
