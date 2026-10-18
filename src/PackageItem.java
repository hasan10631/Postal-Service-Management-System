public class PackageItem {
    private int trackingId;
    private String recipientName;
    private double weightKg;
    private String destination;

    public PackageItem(int trackingId, String recipientName, double weightKg, String destination) {
        this.trackingId = trackingId;
        this.recipientName = recipientName;
        this.weightKg = weightKg;
        this.destination = destination;
    }

    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "PackageItem [ID=" + trackingId + ", Recipient=" + recipientName +
                ", Weight=" + weightKg + "kg, Destination=" + destination + "]";
    }
}
