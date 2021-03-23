export class Pagenation { 
      
    constructor (  public page: number,
    public size: number ,
    public sortField: string, 
    public sortOrder: string,
    public requestObject:any
 ) {  } 
}