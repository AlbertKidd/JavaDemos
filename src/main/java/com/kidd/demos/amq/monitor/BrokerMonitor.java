package com.kidd.demos.amq.monitor;

import lombok.Setter;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.BrokerView;

/**
 * @author Kidd
 */
public class BrokerMonitor {

    @Setter
    private BrokerService brokerService;

    public BrokerView getAdminView(){
        try {
            return brokerService.getAdminView();
        } catch (Exception e) {
            throw new RuntimeException("获取BrokerAdminViews失败", e);
        }
    }


}
