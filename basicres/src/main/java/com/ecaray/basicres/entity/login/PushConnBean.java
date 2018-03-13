package com.ecaray.basicres.entity.login;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/30 0030.
 */

public class PushConnBean implements Serializable {
    private String host;
    private int port;
    private String project;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
