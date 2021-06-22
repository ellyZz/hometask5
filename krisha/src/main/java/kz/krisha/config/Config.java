package kz.krisha.config;

public final class Config {
    private String startUrl;
    private String phoneNumber;
    private String password;

    public String getStartUrl() {
        return startUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setStartUrl(String startUrl) {
        this.startUrl = startUrl;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
