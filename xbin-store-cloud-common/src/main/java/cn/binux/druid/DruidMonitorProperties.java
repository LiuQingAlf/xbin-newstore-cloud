package cn.binux.druid;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="druid.monitor")
public class DruidMonitorProperties
{
    private String DruidStatView;
    private String DruidWebStatFilter;
    private String allow;
    private String deny;
    private String loginUsername;
    private String loginPassword;
    private String exclusions;
    private String resetEnable;

    public String getDruidStatView()
    {
        return this.DruidStatView;
    }

    public void setDruidStatView(String druidStatView) {
        this.DruidStatView = druidStatView;
    }

    public String getDruidWebStatFilter() {
        return this.DruidWebStatFilter;
    }

    public void setDruidWebStatFilter(String druidWebStatFilter) {
        this.DruidWebStatFilter = druidWebStatFilter;
    }

    public String getAllow() {
        return this.allow;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }

    public String getDeny() {
        return this.deny;
    }

    public void setDeny(String deny) {
        this.deny = deny;
    }

    public String getLoginUsername() {
        return this.loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginPassword() {
        return this.loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getExclusions() {
        return this.exclusions;
    }

    public void setExclusions(String exclusions) {
        this.exclusions = exclusions;
    }

    public String getResetEnable() {
        return this.resetEnable;
    }

    public void setResetEnable(String resetEnable) {
        this.resetEnable = resetEnable;
    }
}