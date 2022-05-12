package com.superology.squadapi.squadapi.kafka;

import com.superology.squadapi.squadapi.models.domainSquad.Squad;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class SquadKafkaConsumer extends AbstractKafkaConsumer<Squad>{
    private static String topic = "br_domain_squad";

    public SquadKafkaConsumer() {
        super(topic, Squad.class);
    }

    public SquadKafkaConsumer(String topicName, Class cls) {
        super(topicName, cls);
    }

    @Override
    protected void afterReadHook(Squad value) {
        Optional<Squad> otherSquadOptional = super.repository.findById(value.getTeam_id());
        if(otherSquadOptional.isPresent()){
            Squad otherSquad = otherSquadOptional.get();
            if(value.getVersion() > otherSquad.getVersion()){
                repository.deleteById(otherSquad.getTeam_id());
            }
            else return;
        }
    }
}
