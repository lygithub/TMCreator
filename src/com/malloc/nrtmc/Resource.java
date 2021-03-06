package com.malloc.nrtmc;

import net.ontopia.topicmaps.core.TopicIF;

public class Resource extends BaseResource {
    // resource attribute
    private String resourceName;
    private String resourceCreator;
    private String resourceDate;
    private String resourceSize;
    private String resourceSuiffx;
    private String resourceFileName;

    // private String resourcePath;
    // public TopicIF resourceTopic;

    public Resource() {
        resourceName = null;
        resourceCreator = null;
        resourceDate = null;
        resourceSize = null;
        resourceSuiffx = null;
        resourceFileName = null;
        // resourcePath = null;
        // resourceTopic = null;
    }

    // ��FileName�ֽ�
    public Resource(String sFileName) {
        setResourceFileName(sFileName);
        String[] ss = new String[5];
        ss = sFileName.split("-", 5);
        setResourceName(ss[0]);
        setResourceCreator(ss[1]);
        setResourceDate(ss[2]);
        setResourceSize(ss[3]);
        setResourceSuffix(ss[4]);
        // resourceTopic = null;
    }

    //
    public Resource(String sName, String sCreator, String sDate, String sSize,
            String sSuiffx) {
        setResourceName(sName);
        setResourceCreator(sCreator);
        setResourceDate(sDate);
        setResourceSize(sSize);
        setResourceSuffix(sSuiffx);
        setResourceFileName(sName + "-" + sCreator + "-" + sDate + "-" + sSize
                + "-" + sSuiffx);
        // resourceTopic = null;
    }

    // get&set resourceName
    public String getResourceName() {
        return resourceName;
    }

    private void setResourceName(String rcName) {
        resourceName = rcName;
    }

    // get&set resourceCreator
    public String getResourceCreator() {
        return resourceCreator;
    }

    private void setResourceCreator(String rcCreator) {
        resourceCreator = rcCreator;
    }

    // get&set resourceDate
    public String getResourceDate() {
        return resourceDate;
    }

    private void setResourceDate(String rcDate) {
        resourceDate = rcDate;
    }

    // get&set resourceSize
    public String getResourceSize() {
        return resourceSize;
    }

    private void setResourceSize(String rcSize) {
        resourceSize = rcSize;
    }

    // get&set resourceSuiffx
    public String getResourceSuffix() {
        return resourceSuiffx;
    }

    private void setResourceSuffix(String rcSuffix) {
        resourceSuiffx = rcSuffix;
    }

    // get&set resourceFileName
    public String getResourceFileName() {
        return resourceFileName;
    }

    private void setResourceFileName(String rcFileName) {
        resourceFileName = rcFileName;
    }

    // get&set resourceTopic
    public TopicIF getResourceTopic() {
        return this.resourceTopic;
    }
}
