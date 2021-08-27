// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  local: 'http://localhost:8080',
  remote: 'http://portfoliomanager-portfoliomanager.namdevops12.conygre.com',
  services: {
    getMarketMoversRemote: (id:number) => `${environment.remote}/account/${id}/movers/`,
    getMarketMoversLocal: (id: number) => `${environment.local}/account/${id}//movers`,
    getIndicesRemote: (time: string) => `${environment.remote}/account/indices?time=${time}`,
    getIndicesLocal: (time: string) => `${environment.local}/account/indices?time=${time}`,
    getCashAccountRemote: (id: number) => `${environment.remote}/account/${id}/cash`,
    getCashAccountLocal: (id: number) => `${environment.local}/account/${id}/cash`,
    getInvestAccountRemote: (id: number) => `${environment.remote}/account/${id}/invest`,
    getInvestAccountLocal: (id: number) => `${environment.local}/account/${id}/invest`,
    getAccountTotalRemote: (id: number) => `${environment.remote}/invest/total/${id}`,
    getAccountTotalLocal: (id: number) => `${environment.local}/invest/total/${id}`,
    getCashFlowRemote: (id:number, time:string) => `${environment.remote}/account/${id}/cash?time=${time}`,
    getCashFlowLocal: (id:number, time:string) => `${environment.local}/account/${id}/cash?time=${time}`,
    getNetWorthRemote: (id:number, time:string) => `${environment.remote}/account/${id}/networth?time=${time}`,
    getNetWorthLocal: (id:number, time:string) => `${environment.local}/account/${id}/networth?time=${time}`,
    getUserRemote: (id: number) => `${environment.remote}/account/${id}`,
    getUserLocal: (id: number) => `${environment.local}/account/${id}`
  }
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
