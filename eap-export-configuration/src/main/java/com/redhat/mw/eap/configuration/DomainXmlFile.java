package com.redhat.mw.eap.configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.redhat.mw.eap.configuration.model.DataSource;
import com.redhat.mw.eap.configuration.model.XADataSource;

public class DomainXmlFile {

    /** File (with path) with Domain Configuration */
    private String file = null;

    /** Document with Domain Configuration */
    private Document document = null;

    public DomainXmlFile(String file) throws ParserConfigurationException, SAXException, IOException {
        this.file = file;

        // Parsing document
        parseFile();
    }

    private void parseFile() throws ParserConfigurationException, SAXException, IOException {
        File fXmlFile = new File(this.file);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        this.document = dBuilder.parse(fXmlFile);

        // optional, but recommended
        // read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        this.document.getDocumentElement().normalize();
    }

    public List<DataSource> getDataSources(String profileName) {
        // List of DataSources in profile
        List<DataSource> datasources = new ArrayList();

        // Profiles defined in Domain
        NodeList nodeListProfiles = this.document.getElementsByTagName("profile");

        boolean notFound = true;
        for (int p = 0; notFound && p < nodeListProfiles.getLength(); p++) {
            Node nodeProfile = nodeListProfiles.item(p);
            if (nodeProfile.getNodeType() == Node.ELEMENT_NODE) {
                // Looking for the profile
                Element elementProfile = (Element) nodeProfile;

                if (profileName.equals(elementProfile.getAttribute("name"))) {
                    // We are in the right profile
                    notFound = false;

                    // List of DataSources defined in profile
                    NodeList nodeListDataSources = elementProfile.getElementsByTagName("datasource");
                    for (int d = 0; d < nodeListDataSources.getLength(); d++) {
                        Node nodeDataSource = nodeListDataSources.item(d);

                        if (nodeDataSource.getNodeType() == Node.ELEMENT_NODE) {
                            // DataSource Element
                            Element elementDataSource = (Element) nodeDataSource;

                            // New DataSource
                            DataSource dataSource = new DataSource();

                            // Getting DataSource Configuration (Attributes)
                            dataSource.setJndiName(elementDataSource.getAttribute("jndi-name"));
                            dataSource.setPoolName(elementDataSource.getAttribute("pool-name"));
                            dataSource.setEnabled(Boolean.valueOf(elementDataSource.getAttribute("enabled")));
                            dataSource.setUseCcm(Boolean.valueOf(elementDataSource.getAttribute("use-ccm")));
                            dataSource.setJta(Boolean.valueOf(elementDataSource.getAttribute("jta")));

                            // Other configuration
                            NodeList childNodes = elementDataSource.getChildNodes();

                            for (int c = 0; c < childNodes.getLength(); c++) {
                                Node childNode = childNodes.item(c);

                                // JDBC Connection Configuration
                                if ("connection-url".equals(childNode.getNodeName())) {
                                    dataSource.setConnectionUrl(childNode.getTextContent());
                                } else if ("driver-class".equals(childNode.getNodeName())) {
                                    dataSource.setDriverClass(childNode.getTextContent());
                                } else if ("driver".equals(childNode.getNodeName())) {
                                    dataSource.setDriver(childNode.getTextContent());
                                } else if ("pool".equals(childNode.getNodeName())) {
                                    // Pool Configuration
                                    NodeList poolConfiguration = childNode.getChildNodes();

                                    for (int pc = 0; pc < poolConfiguration.getLength(); pc++) {
                                        Node poolNode = poolConfiguration.item(pc);

                                        if ("min-pool-size".equals(poolNode.getNodeName()) && !"".equals(poolNode.getTextContent())) {
                                            dataSource.setMinPool(Integer.valueOf(poolNode.getTextContent()));
                                        } else if ("max-pool-size".equals(poolNode.getNodeName()) && !"".equals(poolNode.getTextContent())) {
                                            dataSource.setMaxPool(Integer.valueOf(poolNode.getTextContent()));
                                        }
                                    }
                                } else if ("security".equals(childNode.getNodeName())) {
                                    NodeList securityConfiguration = childNode.getChildNodes();

                                    for (int pc = 0; pc < securityConfiguration.getLength(); pc++) {
                                        Node securityNode = securityConfiguration.item(pc);

                                        if ("user-name".equals(securityNode.getNodeName())) {
                                            dataSource.setUser(securityNode.getTextContent());
                                        }
                                    }
                                } else if ("validation".equals(childNode.getNodeName())) {
                                    // // Validation
                                    // <validation>
                                    // <check-valid-connection-sql>select 1 from SYSIBM.SYSDUMMY1</check-valid-connection-sql>
                                    // <validate-on-match>false</validate-on-match>
                                    // <background-validation>false</background-validation>
                                    // </validation>

                                } else if ("statement".equals(childNode.getNodeName())) {
                                    // // Statemente
                                    // <statement>
                                    // <share-prepared-statements>false</share-prepared-statements>
                                    // </statement>
                                }
                            }

                            // Adding new DataSource
                            datasources.add(dataSource);
                        }
                    }

                    // TODO Centralize in standalone methods

                    // List of DataSources defined in profile
                    NodeList nodeListXADataSources = elementProfile.getElementsByTagName("xa-datasource");
                    for (int d = 0; d < nodeListXADataSources.getLength(); d++) {
                        Node nodeXADataSource = nodeListXADataSources.item(d);

                        if (nodeXADataSource.getNodeType() == Node.ELEMENT_NODE) {
                            // DataSource Element
                            Element elementDataSource = (Element) nodeXADataSource;

                            // New DataSource
                            XADataSource dataSource = new XADataSource();

                            // Getting DataSource Configuration (Attributes)
                            dataSource.setJndiName(elementDataSource.getAttribute("jndi-name"));
                            dataSource.setPoolName(elementDataSource.getAttribute("pool-name"));
                            dataSource.setEnabled(Boolean.valueOf(elementDataSource.getAttribute("enabled")));
                            dataSource.setUseCcm(Boolean.valueOf(elementDataSource.getAttribute("use-ccm")));

                            // Other configuration
                            NodeList childNodes = elementDataSource.getChildNodes();

                            for (int c = 0; c < childNodes.getLength(); c++) {
                                Node childNode = childNodes.item(c);

                                // TODO Properties

                                // JDBC Connection Configuration
                                if ("xa-datasource-class".equals(childNode.getNodeName())) {
                                    dataSource.setXaDatasourceClass(childNode.getTextContent());
                                } else if ("driver".equals(childNode.getNodeName())) {
                                    dataSource.setDriver(childNode.getTextContent());
                                } else if ("xa-pool".equals(childNode.getNodeName())) {
                                    // Pool Configuration
                                    NodeList poolConfiguration = childNode.getChildNodes();

                                    for (int pc = 0; pc < poolConfiguration.getLength(); pc++) {
                                        Node poolNode = poolConfiguration.item(pc);

                                        if ("min-pool-size".equals(poolNode.getNodeName()) && !"".equals(poolNode.getTextContent())) {
                                            dataSource.setMinPool(Integer.valueOf(poolNode.getTextContent()));
                                        } else if ("max-pool-size".equals(poolNode.getNodeName()) && !"".equals(poolNode.getTextContent())) {
                                            dataSource.setMaxPool(Integer.valueOf(poolNode.getTextContent()));
                                        }
                                    }
                                } else if ("security".equals(childNode.getNodeName())) {
                                    NodeList securityConfiguration = childNode.getChildNodes();

                                    for (int pc = 0; pc < securityConfiguration.getLength(); pc++) {
                                        Node securityNode = securityConfiguration.item(pc);

                                        if ("user-name".equals(securityNode.getNodeName())) {
                                            dataSource.setUser(securityNode.getTextContent());
                                        }
                                    }
                                } else if ("validation".equals(childNode.getNodeName())) {
                                    // // Validation
                                    // <validation>
                                    // <check-valid-connection-sql>select 1 from SYSIBM.SYSDUMMY1</check-valid-connection-sql>
                                    // <validate-on-match>false</validate-on-match>
                                    // <background-validation>false</background-validation>
                                    // </validation>

                                } else if ("statement".equals(childNode.getNodeName())) {
                                    // // Statemente
                                    // <statement>
                                    // <share-prepared-statements>false</share-prepared-statements>
                                    // </statement>
                                }
                            }

                            // Adding new DataSource
                            datasources.add(dataSource);
                        }
                    }
                }
            }
        }

        return datasources;

    }
}
