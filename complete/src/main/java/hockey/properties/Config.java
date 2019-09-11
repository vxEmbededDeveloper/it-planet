package hockey.properties;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application")
public class Config {
    @Value("${hockeyfield.coordinates.xleft}")
    private Integer xleft;
    @Value("${hockeyfield.coordinates.xright}")
    private Integer xright;
    @Value("${hockeyfield.coordinates.ybottom}")
    private Integer ybottom;
    @Value("${hockeyfield.coordinates.ytop}")
    private Integer ytop;
}
