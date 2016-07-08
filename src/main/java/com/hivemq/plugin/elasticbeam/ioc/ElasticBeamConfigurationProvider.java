/*
 * Copyright 2016 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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