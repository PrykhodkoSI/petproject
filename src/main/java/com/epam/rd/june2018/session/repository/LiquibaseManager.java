package com.epam.rd.june2018.session.repository;

import com.epam.rd.june2018.session.config.PropertiesManager;
import com.epam.rd.june2018.session.repository.util.DbManager;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public class LiquibaseManager {
    private static final Logger logger = LoggerFactory.getLogger(LiquibaseManager.class);
    private PropertiesManager propertiesManager = new PropertiesManager();
    private static final String LIQUIBASE_CHANGELOG = "liquibase/db-changelog-master.xml";


    public LiquibaseManager() {
    }

    private Connection getConnection(){
        return DbManager.getConnection(propertiesManager.getApplicationProperties());
    }

    public void reinitDB(){
        try(Connection conn = getConnection()) {
            Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(conn));

            List<ResourceAccessor> resourceAccessors = Arrays.asList(
                    new ClassLoaderResourceAccessor(Thread.currentThread().getContextClassLoader()),
                    new FileSystemResourceAccessor()
            );

            ResourceAccessor resourceAccessor = new CompositeResourceAccessor(resourceAccessors);
            Liquibase liquibase = new Liquibase(LIQUIBASE_CHANGELOG, resourceAccessor, database);
            liquibase.dropAll();
            liquibase.update((Contexts) null);
        }
        catch (Exception e){

            logger.error(e.getMessage());
        }
    }

}
