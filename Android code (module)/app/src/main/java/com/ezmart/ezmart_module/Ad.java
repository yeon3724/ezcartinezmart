package com.ezmart.ezmart_module;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Ad {

    public String ad_event;
    public String ad_img;
    public String ad_name;
    public String ad_video;
    public String ad_onoff;

    public Ad() {
    }

    public Ad(String ad_event, String ad_img, String ad_name, String ad_video, String ad_onoff) {
        this.ad_event = ad_event;
        this.ad_img = ad_img;
        this.ad_name = ad_name;
        this.ad_video = ad_video;
        this.ad_onoff = ad_onoff;
    }

    public String getAd_event() {
        return ad_event;
    }

    public void setAd_event(String ad_event) {
        this.ad_event = ad_event;
    }

    public String getAd_img() {
        return ad_img;
    }

    public void setAd_img(String ad_img) {
        this.ad_img = ad_img;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getAd_video() {
        return ad_video;
    }

    public void setAd_video(String ad_video) {
        this.ad_video = ad_video;
    }

    public String getAd_onoff() {
        return ad_onoff;
    }

    public void setAd_onoff(String ad_onoff) {
        this.ad_onoff = ad_onoff;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "ad_event='" + ad_event + '\'' +
                ", ad_img='" + ad_img + '\'' +
                ", ad_name='" + ad_name + '\'' +
                ", ad_video='" + ad_video + '\'' +
                ", ad_onoff='" + ad_onoff + '\'' +
                '}';
    }

}