package com.superology.squadapi.squadapi.kafka;

import com.superology.squadapi.squadapi.models.domainPlayer.DomainPlayer;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class PlayerKafkaConsumer extends AbstractKafkaConsumer<DomainPlayer> {
    private static String topic = "br_domain_player";

    public PlayerKafkaConsumer() {
        super(topic, DomainPlayer.class);
    }


    @Override
    protected void afterReadHook(DomainPlayer value) {
        Optional<DomainPlayer> otherSquadOptional = super.repository.findById(value.getId());
        if(otherSquadOptional.isPresent()){
            DomainPlayer other = otherSquadOptional.get();
            if(value.getVersion() > other.getVersion()){
                repository.deleteById(other.getId());
            }
            else return;
        }
    }
}
