import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Demande } from "../classes/demande";

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  
  
  @Injectable({
    providedIn: 'root'
  })

export class DemandeService {
    public url =environment.api_url;

    constructor(private http: HttpClient) { 

    }

    getMyRequestService(email: string): Observable<any> {
        return this.http.get(this.url+"/auth/mesdemandes/"+email);
    }
    getRequestService(sta:String,nbmedailles:number, date:String):Observable<any> {
        return this.http.get(this.url+"/getRequestfilter/"+sta+"/"+nbmedailles+"/"+date);
    }
  
    getAllDemandesByOffer(idOffre : number): Observable<any>{
      return this.http.get(this.url+"/getDemandesByOffer/"+ idOffre);

    }
    getAllStatus() : Observable<any>{
      return this.http.get(this.url+"/getAllStatus");

    }
    updateDemande(demande: Demande, idDemande : number) : Observable<any>{
      return this.http.put(this.url+"/updateRequestService/"+idDemande,demande);
     
    }
    virtualMoney(idDemande : number): Observable<any>{
      return this.http.put(this.url+"/virtualMoney/"+ idDemande,{ responseType: 'text'});

    }
    isDemandCanUpdated(idDemande : number,newStatut : String ): Observable<any>{
      return this.http.get(this.url+"/isDemandCanUpdated/"+ idDemande +"/" + newStatut);

    }
}
