package com.kidd.demos.jgroups;

import lombok.extern.log4j.Log4j2;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

/**
 * @author Kidd
 * CreateTime 2017/12/8.
 */
@Log4j2
public class MyReceiver extends ReceiverAdapter {

    @Override
    public void receive(Message msg) {
        String s = new String(msg.getBuffer());
        log.info(s);
    }
}
