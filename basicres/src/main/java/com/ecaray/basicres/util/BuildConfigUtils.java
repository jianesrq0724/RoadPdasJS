package com.ecaray.basicres.util;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/11
 */

public class BuildConfigUtils {

    private boolean debug;
    private String buildType;
    private String appId;
    private String requestKey;
    private String flavor;
    private int urlType;
    private String urlCommon;
    private String urlDwonUp;


    private static final BuildConfigUtils ourInstance = new BuildConfigUtils();

    public static BuildConfigUtils getInstance() {
        return ourInstance;
    }

    private BuildConfigUtils() {
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(String requestKey) {
        this.requestKey = requestKey;
    }

    public static BuildConfigUtils getOurInstance() {
        return ourInstance;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getUrlType() {
        return urlType;
    }

    public void setUrlType(int urlType) {
        this.urlType = urlType;
    }

    public String getUrlCommon() {
        return urlCommon;
    }

    public void setUrlCommon(String urlCommon) {
        this.urlCommon = urlCommon;
    }

    public String getUrlDwonUp() {
        return urlDwonUp;
    }

    public void setUrlDwonUp(String urlDwonUp) {
        this.urlDwonUp = urlDwonUp;
    }
}
