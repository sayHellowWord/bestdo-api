package com.saidian.web.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 行政区
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region {

    private String region_id;

    private String name;

    private  int sequence;

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "Region{" +
                "region_id='" + region_id + '\'' +
                ", name='" + name + '\'' +
                ", sequence=" + sequence +
                '}';
    }
}
