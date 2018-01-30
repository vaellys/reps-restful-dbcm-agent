package com.reps.micro;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.boot.ApplicationPid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


//@SpringCloudApplication
@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@ComponentScan(basePackages = "com.reps")
@ServletComponentScan(basePackages = "com.reps")
@EnableRedisHttpSession
//@EnableHystrix
public class RepsApplication extends WebMvcConfigurerAdapter
{
    public static void main(String[] args)
    {
        SpringApplication application = new SpringApplication(RepsApplication.class); 
        application.run(args);
    }
    
    @PostConstruct
    private void handlePid() throws IOException {
        File file = new File("dbcm-agent.pid");
        new ApplicationPid().write(file);
        file.deleteOnExit();
    }
}
