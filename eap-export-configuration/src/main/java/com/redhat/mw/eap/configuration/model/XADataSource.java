package com.redhat.mw.eap.configuration.model;

public class XADataSource extends DataSource {

    private String xaDatasourceClass;

    public XADataSource() {
        this.xa = Boolean.TRUE;
    }

    public String getXaDatasourceClass() {
        return this.xaDatasourceClass;
    }

    public void setXaDatasourceClass(String xaDatasourceClass) {
        this.xaDatasourceClass = xaDatasourceClass;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("XADataSource [xaDatasourceClass=").append(xaDatasourceClass).append(", xa=").append(xa).append(", jndiName=")
                .append(jndiName).append(", poolName=").append(poolName).append(", enabled=").append(enabled).append(", useCcm=")
                .append(useCcm).append(", jta=").append(jta).append(", connectionUrl=").append(connectionUrl).append(", driverClass=")
                .append(driverClass).append(", driver=").append(driver).append(", user=").append(user).append(", minPool=").append(minPool)
                .append(", maxPool=").append(maxPool).append("]");
        return builder.toString();
    }

}
