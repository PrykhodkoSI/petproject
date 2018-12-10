package com.epam.rd.june2018.session.web.listener;

import com.epam.rd.june2018.session.service.Interface.OutdatedBillManagerService;
import com.epam.rd.june2018.session.service.ServiceFabric;


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
        //TODO WTF (D:/Users/Stas/IdeaProjects/homework/pet_project/session/target/session/WEB-INF/classes/liquibase/db-changelog-master.xml does not exist) actually exists
        OutdatedBillManagerService service = ServiceFabric.getOutdateBillManagerService();//Init Service
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
