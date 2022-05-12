package com.superology.squadapi.squadapi.controllers;

import com.superology.squadapi.squadapi.models.domainPlayer.DomainPlayer;
import com.superology.squadapi.squadapi.models.domainSquad.Squad;
import com.superology.squadapi.squadapi.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private KafkaService service;

    @GetMapping("/api/topics")
    public List<String> home(){
        return service.getTopics();
    }

    @GetMapping("/api/squad/{id}")
    public Squad squad(@PathVariable String id){
        int squadId = Integer.parseInt(id);
        return service.getSquads().get(squadId);
    }

    @GetMapping("/api/squad")
    public List<Squad> squadAll(){
        return service.getSquads();
    }

    @GetMapping("/api/player")
    public List<DomainPlayer> playerAll(){
        return service.getPlayers();
    }

    @GetMapping("/api/player/{id}")
    public DomainPlayer playerId(@PathVariable String id){
        return service.getPlayers().get(Integer.parseInt(id));
    }
}
