package com.superology.squadapi.squadapi.service;

import com.superology.squadapi.squadapi.kafka.AbstractKafkaConsumer;
import com.superology.squadapi.squadapi.kafka.SquadKafkaConsumer;
import com.superology.squadapi.squadapi.kafka.PlayerKafkaConsumer;
import com.superology.squadapi.squadapi.models.domainPlayer.DomainPlayer;
import com.superology.squadapi.squadapi.models.domainSquad.Squad;
import com.superology.squadapi.squadapi.kafka.KafkaProperties;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class KafkaService{
    private Map<String, List<PartitionInfo>> topicMap;

    @Autowired
    private SquadKafkaConsumer squadConsumer;

    @Autowired
    private PlayerKafkaConsumer playerConsumer;

    @Autowired
    private JpaRepository<Squad, Long> squadRepository;

    @Autowired
    private JpaRepository<DomainPlayer, Long> playerRepository;

    @PostConstruct
    private void init() throws ExecutionException, InterruptedException {
        if(topicMap == null){
            topicMap = AbstractKafkaConsumer.listKafkaTopics();
        }
        List<String> topicNames = topicMap.keySet().stream().collect(Collectors.toList());

        /*squadConsumer.setDaemon(true);
        squadConsumer.start();*/

        playerConsumer.setDaemon(true);
        playerConsumer.start();
    }

    public List<String> getTopics(){
        return topicMap.keySet().stream().collect(Collectors.toList());
    }

    public List<Squad> getSquads(){
        return squadRepository.findAll();
    }

    public List<DomainPlayer> getPlayers(){
        return playerRepository.findAll();
    }
}
