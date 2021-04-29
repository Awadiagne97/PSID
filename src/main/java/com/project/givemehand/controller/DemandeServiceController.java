package com.project.givemehand.controller;

import com.project.givemehand.models.entity.*;
import com.project.givemehand.repository.RequestRepository;
import com.project.givemehand.services.OffreService;
import com.project.givemehand.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
/**
 *  Elle represente le controller de la classe demandeService
 */
public class DemandeServiceController {

    @Autowired
    private RequestService service;
    @Autowired
    private RequestRepository demandesRep;

    @Autowired
    private OffreService offreService;
    @RequestMapping(path ="/requestServiceById/{id}", method = RequestMethod.GET)
    public Demande getServiceRequest( @PathVariable("id") Long id)
    {
        return service.getServiceRequest(id);
    }

    @RequestMapping(path ="/addRequestService", method = RequestMethod.POST)
    public ResponseEntity<Demande> addDemande(@RequestBody Demande demande)
    {
        return service.addRequestService(demande);
    }

    @DeleteMapping(value = "/deleteServiceRequest/{requestId}")
    public void deleteServiceRequest(@PathVariable("requestId") Long requestId)
    {
        service.deleteServiceRequest(requestId);
    }

    @RequestMapping(path ="/getRequestfilter/{sta}/{nbMedailles}/{date}", method = RequestMethod.GET)

    public List<Demande> getRequestService(@PathVariable String sta, @PathVariable String nbMedailles, @PathVariable String date)
    {

        Statut statut = Statut.valueOf(sta.toUpperCase());
        String jour = date.substring(0,2);
        String mois = date.substring(2,4);
        String annee = date.substring(4,8);
        String d = jour.concat("/"+mois+"/"+annee);
        Filtre f = new Filtre(statut,Integer.parseInt(nbMedailles), new Date(d));
        return service.filterRequest(f);
    }
    @RequestMapping(path ="/getDemandesByOffer/{idOffre}", method = RequestMethod.GET)
    public List<Demande> getDemandesByOffer(@PathVariable("idOffre") Long idOffre){
        System.out.println("Id Offre" + idOffre);

        Offre offre = offreService.getOfferById(idOffre);
        System.out.println("Offre" + offre.toString());

        return service.getDemandesByOffre(offre);

    }

    /**
     * API renvoyant la liste des catégorie des offres
     * @return Status []
     */
    @RequestMapping(path ="/getAllStatus", method = RequestMethod.GET)
    public Statut[] getAllStatus(){
        Statut[] statuts = Statut.values();
        return statuts ;
    }
    @RequestMapping(path ="/getAllDemandes", method = RequestMethod.GET)
    public List<Demande> getDemandesByOffer(){


        return demandesRep.findAll();

    }
    //@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)

    //@RequestMapping(path="/incrementerMedailles/{idDemande}", method = RequestMethod.GET)
    @PutMapping("/virtualMoney/{idDemande}")
    public Demande virtualMoney(@PathVariable Long idDemande){
        return service.virtualMoney(idDemande);

    }
    @RequestMapping(path ="/isDemandCanUpdated/{idDemande}/{newStatut}", method = RequestMethod.GET)
    public boolean isDemandCanUpdated(@PathVariable Long idDemande, @PathVariable String newStatut){
            return service.isDemandCanUpdated(idDemande,newStatut);
    }


    @PutMapping("/updateRequestService/{id}")
    public ResponseEntity<Demande> updateRequestService(@PathVariable Long id, @RequestBody Demande demande)
    {
        return service.updateRequestService(id,demande);
    }



}
