package com.hivemq.plugin.elasticbeam.configuration;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.DisableableFeature.PARAMETER_FORMATTING;
import static org.aeonbits.owner.Config.DisableableFeature.VARIABLE_EXPANSION;

@Config.DisableFeature({VARIABLE_EXPANSION, PARAMETER_FORMATTING})
public interface ElasticBeamConfiguration extends Config {

    @Key("heartbeat.port")
    @DefaultValue("9090")
    int getHeartbeatPort();

    @Key("heartbeat.bindAddress")
    @DefaultValue("0.0.0.0")
    String getHeartbeatBindAddress();

    @Key("heartbeat.path")
    @DefaultValue("/elasticbeam/heartbeat")
    String getHeartbeatPath();
}