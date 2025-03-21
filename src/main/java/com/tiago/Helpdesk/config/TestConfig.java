package com.tiago.Helpdesk.config;

import com.tiago.Helpdesk.service.DBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
    @Autowired
    private DBServices dbServices;

    public void instanciaDB() {
        this.dbServices.instanciaDB();
    }
}
