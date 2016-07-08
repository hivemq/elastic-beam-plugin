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

package com.hivemq.plugin.elasticbeam.callbacks;

import com.hivemq.plugin.elasticbeam.configuration.ElasticBeamConfiguration;
import com.hivemq.plugin.elasticbeam.servlet.ElasticBeamHeartbeatServlet;
import com.hivemq.spi.callback.CallbackPriority;
import com.hivemq.spi.callback.events.broker.OnBrokerStart;
import com.hivemq.spi.callback.exception.BrokerUnableToStartException;
import com.hivemq.spi.services.rest.RESTService;
import com.hivemq.spi.services.rest.listener.HttpListener;

import javax.inject.Inject;
import java.util.Collections;

/**
 * @author Dominik Obermaier
 */
public class AddHTTPHeartbeatOnStartup implements OnBrokerStart {


    public static final String LISTENER_NAME = "elasticbeam-heartbeat-listener";
    private RESTService restService;
    private ElasticBeamConfiguration elasticBeamConfiguration;

    @Inject
    AddHTTPHeartbeatOnStartup(final RESTService restService,
                              final ElasticBeamConfiguration elasticBeamConfiguration) {
        this.restService = restService;
        this.elasticBeamConfiguration = elasticBeamConfiguration;
    }

    @Override
    public void onBrokerStart() throws BrokerUnableToStartException {

        final HttpListener httpListener = new HttpListener(LISTENER_NAME, elasticBeamConfiguration.getHeartbeatBindAddress(), elasticBeamConfiguration.getHeartbeatPort());
        restService.addListener(httpListener);

        restService.addServlet(ElasticBeamHeartbeatServlet.class, elasticBeamConfiguration.getHeartbeatPath(), Collections.singleton(LISTENER_NAME));
    }

    @Override
    public int priority() {
        return CallbackPriority.LOW;
    }
}
