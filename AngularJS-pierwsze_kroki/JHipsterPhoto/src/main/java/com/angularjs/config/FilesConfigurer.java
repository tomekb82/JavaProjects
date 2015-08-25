package com.angularjs.config;

import com.angularjs.domain.Photo;
import com.angularjs.repository.PhotoRepository;
import com.angularjs.repository.util.FileUtil;
import com.angularjs.web.filter.CachingHttpHeadersFilter;
import com.angularjs.web.filter.StaticResourcesProductionFilter;
import com.angularjs.web.filter.gzip.GZipServletFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlet.InstrumentedFilter;
import com.codahale.metrics.servlets.MetricsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import javax.servlet.*;
import java.io.File;
import java.util.*;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
public class FilesConfigurer implements ServletContextInitializer, EmbeddedServletContainerCustomizer {

    private static final String IMAGE_DIR = "./src/main/webapp/resources/images/";

    private final Logger log = LoggerFactory.getLogger(FilesConfigurer.class);

    @Inject
    private Environment env;

    @Inject
    FileUtil fileUtil;

    @Inject

    private PhotoRepository photoRepository;


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("Files configuration, using profiles: {}", Arrays.toString(env.getActiveProfiles()));

        //initilize();
        log.info("Files application fully configured");
    }


    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {

    }
}
