package model;

public class SubscriptionModel {
    private String subscriberName;
    private String subscriberEmail;

    public SubscriptionModel() {
    }

    public SubscriptionModel(String subscriberName, String subscriberEmail) {
        this.subscriberName = subscriberName;
        this.subscriberEmail = subscriberEmail;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public String getSubscriberEmail() {
        return subscriberEmail;
    }

    public void setSubscriberEmail(String subscriberEmail) {
        this.subscriberEmail = subscriberEmail;
    }
}
