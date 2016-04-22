package com.hivemq.plugin.elasticbeam.servlet;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet that responds to the heartbeat GET request from the Load Balancer
 *
 * @author Dominik Obermaier
 */
public class ElasticBeamHeartbeatServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(ElasticBeamHeartbeatServlet.class);

    private MetricRegistry metricRegistry;

    @Inject
    public ElasticBeamHeartbeatServlet(final MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        log.info("Heartbeat Request from IP {} (port {}) received on listener {}:{} and URI {}",
                req.getRemoteAddr(), req.getRemotePort(), req.getLocalAddr(), req.getLocalPort(), req.getRequestURI());

        //We're just returning a 200
        resp.setStatus(HttpServletResponse.SC_OK);

        //We're adding additional metrics
        final Meter meter = metricRegistry.meter("elasticbeam-heartbeat-meter");
        meter.mark();

    }
}
