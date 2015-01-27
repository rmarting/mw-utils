package com.redhat.mw.eap.configuration.model;

public class DataSource {

    protected Boolean xa = Boolean.FALSE;

    protected String jndiName;

    protected String poolName;

    protected Boolean enabled;

    protected Boolean useCcm;

    protected Boolean jta;

    protected String connectionUrl;

    protected String driverClass;

    protected String driver;

    protected String user;

    protected Integer minPool;

    protected Integer maxPool;

    public Boolean isXA() {
        return this.xa;
    }

    public void setXA(Boolean xa) {
        this.xa = xa;
    }

    public String getJndiName() {
        return this.jndiName;
    }

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }

    public String getPoolName() {
        return this.poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean isUseCcm() {
        return this.useCcm;
    }

    public void setUseCcm(Boolean useCcm) {
        this.useCcm = useCcm;
    }

    public Boolean isJta() {
        return this.jta;
    }

    public void setJta(Boolean jta) {
        this.jta = jta;
    }

    public String getConnectionUrl() {
        return this.connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getDriverClass() {
        return this.driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getDriver() {
        return this.driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getMinPool() {
        return this.minPool;
    }

    public void setMinPool(Integer minPool) {
        this.minPool = minPool;
    }

    public Integer getMaxPool() {
        return this.maxPool;
    }

    public void setMaxPool(Integer maxPool) {
        this.maxPool = maxPool;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DataSource [jndiName=").append(this.jndiName).append(", poolName=").append(this.poolName).append(", enabled=")
                .append(this.enabled).append(", useCcm=").append(this.useCcm).append(", jta=").append(this.jta).append(", connectionUrl=")
                .append(this.connectionUrl).append(", driverClass=").append(this.driverClass).append(", driver=").append(this.driver)
                .append(", user=").append(this.user).append(", minPool=").append(this.minPool).append(", maxPool=").append(this.maxPool)
                .append("]");
        return builder.toString();
    }

}
