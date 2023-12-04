package eu.chrost.booking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LimitedResourcesConfiguration {
    @Bean
    public LimitedResources limitedResources() {
        return new LimitedResources(50);
    }
}
