package com.prykhodkosi.petproject.servletbased.hotel.web.listener;

import com.prykhodkosi.petproject.servletbased.hotel.service.Interface.OutdatedBillManagerService;
import com.prykhodkosi.petproject.servletbased.hotel.service.ServiceFabric;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationLifecycleListener implements ServletContextListener {

    //private LiquibaseManager config = new LiquibaseManager();
    //private PropertiesManager propertiesManager = new PropertiesManager();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //config.reinitDB();
        //TODO WTF (D:/Users/Stas/IdeaProjects/homework/pet_project/hotel/target/hotel/WEB-INF/classes/liquibase/db-changelog-master.xml does not exist) actually exists
        OutdatedBillManagerService service = ServiceFabric.getOutdateBillManagerService();//Init Service
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
