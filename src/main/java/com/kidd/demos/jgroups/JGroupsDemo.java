package com.kidd.demos.jgroups;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Kidd
 * CreateTime 2018/1/2.
 */
@Log4j2
public class JGroupsDemo extends ReceiverAdapter{

    private static JChannel channel;

    private static final String CLUSTER_A = "CLUSTER_A";

    public static void main(String[] args) throws Exception{
        JGroupsDemo demo = new JGroupsDemo();
        demo.start();
    }

    public void start() throws Exception{
        channel = new JChannel().setReceiver(this);
        channel.connect(CLUSTER_A);
        eventLoop();
        channel.close();
    }

    private static void eventLoop() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (;;){
            System.out.print(">  ");
            System.out.flush();
            String line = br.readLine().toLowerCase();
            if (StringUtils.startsWith(line, "quit")){
                break;
            }
            line="[Kidd] " + line;
            Message msg=new Message(null, line).setFlag(Message.Flag.OOB);
            channel.send(msg);
        }
    }

    @Override
    public void viewAccepted(View view) {
        log.info("view: " + view);
    }

    @Override
    public void receive(Message msg) {
        log.info(msg.getSrc() + ":" + msg.getObject());
    }

}
