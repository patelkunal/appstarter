package org.coderearth.kitchens.appstarter;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by kunal_patel on 9/21/16.
 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    public static final int DEFAULT_PORT = 8080;
    private static final String CONTEXT_PATH = "/";
    private static final String MAPPING_URL = "/*";
    private static String processId = null;
    private static final String CONFIG_LOCATION = "org.coderearth.kitchens";

    public static void main(String[] args) {
        Stream.of(args).forEach(System.out::println);
        try {
            new Application().run();
        } catch (Exception e) {
            LOGGER.error("Application startup failed !!", e);
            System.exit(-1);
        }
    }

    public void run() throws Exception {
        if(System.getProperty("pid.file") != null) {
            String processName = ManagementFactory.getRuntimeMXBean().getName();
            processId = processName.split("@")[0];
            BufferedWriter pidWriter = Files.newBufferedWriter(Paths.get(System.getProperty("pid.file"), new String[0]), StandardCharsets.UTF_8, new OpenOption[0]);
            pidWriter.write(processId);
            pidWriter.newLine();
            IOUtils.closeQuietly(pidWriter);
        }
        LOGGER.debug("Starting server at port {" + DEFAULT_PORT + "}");
        Server server = new Server(DEFAULT_PORT);

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);

        server.setHandler(getServletContextHandler(context));
        server.setStopAtShutdown(true);

        ShutdownHook shutdownHook = new ShutdownHook();
        Runtime.getRuntime().addShutdownHook(shutdownHook);
        server.start();
        LOGGER.info("Server started at port {" + DEFAULT_PORT + "}");
        server.join();
    }

    private ServletContextHandler getServletContextHandler(WebApplicationContext context) throws IOException {
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath(CONTEXT_PATH);

        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        contextHandler.addEventListener(new ContextLoaderListener(context));

        return contextHandler;
    }

    private class ShutdownHook extends Thread {

        @Override
        public void run() {
            LOGGER.info("Performing application shutdown !!");
            LOGGER.info("application shutdown is completed !!");
        }
    }

}