import {environment} from "../../environments/environment";

export const BASE_URL = `${environment.scheme}://${environment.serverHost}`;

export const monthNames = [
  { id: 1, name: 'January', short: 'Jan'},
  { id: 2, name: 'February', short: 'Feb'},
  { id: 3, name: 'March', short: 'Mar'},
  { id: 4, name: 'April', short: 'Apr'},
  { id: 5, name: 'May', short: 'May'},
  { id: 6, name: 'June', short: 'Jun'},
  { id: 7, name: 'July', short: 'Jul'},
  { id: 8, name: 'August', short: 'Aug'},
  { id: 9, name: 'September', short: 'Sept'},
  { id: 10, name: 'October', short: 'Oct'},
  { id: 11, name: 'November', short: 'Nov'},
  { id: 12, name: 'December', short: 'Dec'},
]
