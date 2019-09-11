package hockey;

import hockey.models.Coordinate;
import hockey.models.HokkeyPlayer;
import hockey.properties.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class PlayerController {
    @Autowired
    Config config;
    @Autowired
    HokkeyPlayer hokkeyPlayer;

    @MessageMapping("/hockey")
    @SendTo("/player/move")
    public Coordinate positioning(PlayerId id) throws Exception {
        hokkeyPlayer.setPosition(Coordinate.builder().xPosition(10).yPosition(20).build());
        hokkeyPlayer.setPlayerId("bot_1");
        if (hokkeyPlayer.getPlayerId().contains(id.getId()))
            return hokkeyPlayer.getPosition();
        else
            return Coordinate.builder().yPosition(0).xPosition(0).build();
    }
}
