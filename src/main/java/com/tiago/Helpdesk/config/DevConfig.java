package com.tiago.Helpdesk.config;

import com.tiago.Helpdesk.service.DBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBServices dbServices;

    @Value("$(spring.jpa.hibernate.ddl-auto)")
    public String value;

    public boolean instanciaDB() {
        if (value.equals("create")) {
            this.dbServices.instanciaDB();
        }
        return false;
    }
}
