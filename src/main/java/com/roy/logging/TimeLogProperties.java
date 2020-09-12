package com.roy.logging;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * description：
 * author：dingyawu
 * date：created in 0:00 2020/9/12
 * history:
 */
@ConfigurationProperties(prefix = "time.log")
public class TimeLogProperties {
    private Boolean enable;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
