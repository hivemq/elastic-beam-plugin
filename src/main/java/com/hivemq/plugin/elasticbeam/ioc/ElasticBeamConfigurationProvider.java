package com.hivemq.plugin.elasticbeam.ioc;

import com.hivemq.plugin.elasticbeam.configuration.ConfigurationReader;
import com.hivemq.plugin.elasticbeam.configuration.ElasticBeamConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * The provider which is responsible for creating the ElasticBeamConfiguration object
 *
 * @author Dominik Obermaier
 */
public class ElasticBeamConfigurationProvider implements Provider<ElasticBeamConfiguration> {

    private final ConfigurationReader configReader;

    @Inject
    ElasticBeamConfigurationProvider(final ConfigurationReader configReader) {
        this.configReader = configReader;
    }

    @Override
    public ElasticBeamConfiguration get() {
        //We're actually reading the config file
        return configReader.readConfiguration();
    }
}