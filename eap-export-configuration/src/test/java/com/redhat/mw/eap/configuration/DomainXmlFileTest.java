package com.redhat.mw.eap.configuration;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.redhat.mw.eap.configuration.model.DataSource;

public class DomainXmlFileTest {

    @Test
    public void testGetDataSources() throws ParserConfigurationException, SAXException, IOException {
        // DomainXmlFile domainXmlFile = new DomainXmlFile(
        // "/home/rmarting/Workspaces/github/rmarting/mw-utils/eap-export-configuration/src/main/resources/domain/domain-desa.xml");
        // DomainXmlFile domainXmlFile = new DomainXmlFile(
        // "/home/rmarting/Workspaces/github/rmarting/mw-utils/eap-export-configuration/src/main/resources/domain/domain-test.xml");
        DomainXmlFile domainXmlFile = new DomainXmlFile(
                "/home/rmarting/Workspaces/github/rmarting/mw-utils/eap-export-configuration/src/main/resources/domain/domain-prod.xml");

        List<DataSource> datasources = domainXmlFile.getDataSources("mensajeria");
        assertNotNull(datasources);
        System.out.println("Mensajeria");
        System.out.println("*****************************************************");
        System.out.print("xa,poolname,jndiname,enabled,jta,ccm,connection,driver,user,min-pool,max-pool");
        System.out.println();
        for (DataSource datasource : datasources) {
            writeCsvLine(datasource);
        }

        datasources = domainXmlFile.getDataSources("internet");
        assertNotNull(datasources);
        System.out.println("internet");
        System.out.println("*****************************************************");
        System.out.print("xa,poolname,jndiname,enabled,jta,ccm,connection,driver,user,min-pool,max-pool");
        System.out.println();
        for (DataSource datasource : datasources) {
            writeCsvLine(datasource);
        }

        datasources = domainXmlFile.getDataSources("intranet");
        assertNotNull(datasources);
        System.out.println("Intranet");
        System.out.println("*****************************************************");
        System.out.print("xa,poolname,jndiname,enabled,jta,ccm,connection,driver,user,min-pool,max-pool");
        System.out.println();
        for (DataSource datasource : datasources) {
            writeCsvLine(datasource);
        }

        datasources = domainXmlFile.getDataSources("cartografia");
        assertNotNull(datasources);
        System.out.println("Cartografia");
        System.out.println("*****************************************************");
        System.out.print("xa,poolname,jndiname,enabled,jta,ccm,connection,driver,user,min-pool,max-pool");
        System.out.println();
        for (DataSource datasource : datasources) {
            writeCsvLine(datasource);
        }

        datasources = domainXmlFile.getDataSources("bob");
        assertNotNull(datasources);
        System.out.println("bob");
        System.out.println("*****************************************************");
        System.out.print("xa,poolname,jndiname,enabled,jta,ccm,connection,driver,user,min-pool,max-pool");
        System.out.println();
        for (DataSource datasource : datasources) {
            writeCsvLine(datasource);
        }

        datasources = domainXmlFile.getDataSources("negocio");
        assertNotNull(datasources);
        System.out.println("Negocio");
        System.out.println("*****************************************************");
        System.out.print("xa,poolname,jndiname,enabled,jta,ccm,connection,driver,user,min-pool,max-pool");
        System.out.println();
        for (DataSource datasource : datasources) {
            writeCsvLine(datasource);
        }

        datasources = domainXmlFile.getDataSources("ondemand");
        assertNotNull(datasources);
        System.out.println("OnDemand");
        System.out.println("*****************************************************");
        System.out.print("xa,poolname,jndiname,enabled,jta,ccm,connection,driver,user,min-pool,max-pool");
        System.out.println();
        for (DataSource datasource : datasources) {
            writeCsvLine(datasource);
        }

        datasources = domainXmlFile.getDataSources("zeregin");
        assertNotNull(datasources);
        System.out.println("Zeregin");
        System.out.println("*****************************************************");
        System.out.print("xa,poolname,jndiname,enabled,jta,ccm,connection,driver,user,min-pool,max-pool");
        System.out.println();
        for (DataSource datasource : datasources) {
            writeCsvLine(datasource);
        }

        datasources = domainXmlFile.getDataSources("euskalsarea");
        assertNotNull(datasources);
        System.out.println("euskalsarea");
        System.out.println("*****************************************************");
        System.out.print("xa,poolname,jndiname,enabled,jta,ccm,connection,driver,user,min-pool,max-pool");
        System.out.println();
        for (DataSource datasource : datasources) {
            writeCsvLine(datasource);
        }

        datasources = domainXmlFile.getDataSources("hidra");
        assertNotNull(datasources);
        System.out.println("hidra");
        System.out.println("*****************************************************");
        System.out.print("xa,poolname,jndiname,enabled,jta,ccm,connection,driver,user,min-pool,max-pool");
        System.out.println();
        for (DataSource datasource : datasources) {
            writeCsvLine(datasource);
        }

        datasources = domainXmlFile.getDataSources("test-intranet");
        assertNotNull(datasources);
        System.out.println("test-intranet");
        System.out.println("*****************************************************");
        System.out.print("xa,poolname,jndiname,enabled,jta,ccm,connection,driver,user,min-pool,max-pool");
        System.out.println();
        for (DataSource datasource : datasources) {
            writeCsvLine(datasource);
        }

        datasources = domainXmlFile.getDataSources("test-negocio");
        assertNotNull(datasources);
        System.out.println("test-negocio");
        System.out.println("*****************************************************");
        System.out.print("xa,poolname,jndiname,enabled,jta,ccm,connection,driver,user,min-pool,max-pool");
        System.out.println();
        for (DataSource datasource : datasources) {
            writeCsvLine(datasource);
        }

        // TODO Include a test case with Lantik data

    }

    private void writeCsvLine(DataSource datasource) {
        System.out.print(datasource.isXA() + ",");
        System.out.print(datasource.getPoolName() + ",");
        System.out.print(datasource.getJndiName() + ",");
        System.out.print(datasource.isEnabled() + ",");
        System.out.print(datasource.isJta() + ",");
        System.out.print(datasource.isUseCcm() + ",");
        System.out.print(datasource.getConnectionUrl() + ",");
        System.out.print(datasource.getDriver() + ",");
        System.out.print(datasource.getUser() + ",");
        System.out.print(datasource.getMinPool() + ",");
        System.out.print(datasource.getMaxPool() + ",");
        System.out.println();
    }

}
