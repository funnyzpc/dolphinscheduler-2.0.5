package org.apache.dolphinscheduler.server;

import org.apache.dolphinscheduler.api.ApiApplicationServer;
import org.apache.dolphinscheduler.server.master.MasterServer;
import org.apache.dolphinscheduler.server.worker.WorkerServer;

//import org.apache.curator.test.TestingServer;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

// -Dlogging.config=classpath:logback-master.xml -Ddruid.mysql.usePingMethod=false -Dspring.profiles.active=standalone,postgresql
@EnableAutoConfiguration
@ComponentScan
public class StandaloneServer {
    public static void main(String[] args) throws Exception {
//        final TestingServer server = new TestingServer(true);
//        System.setProperty("registry.servers", server.getConnectString());

        new SpringApplicationBuilder(
            ApiApplicationServer.class,
            MasterServer.class,
            WorkerServer.class
//            AlertServer.class
//            PythonGatewayServer.class
        ).profiles("master", "worker", "api",/* "alert", "python-gateway", "h2",*/ "standalone","postgresql").run(args);
    }
}

/*
java -cp dolphinscheduler-standalone-server-2.0.5.jar org.apache.dolphinscheduler.server.StandaloneServer
 */