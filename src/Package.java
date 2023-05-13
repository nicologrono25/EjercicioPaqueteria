public class Package {
    private String trackingNumber;
    private String recipientName;
    private String recipientAddress;
    private String city;
    private String state;
    private String postalCode;

    public Package(String trackingNumber, String recipientName, String recipientAddress, String city, String state, String postalCode) {
        this.trackingNumber = trackingNumber;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getNumeroSeguimiento() {
        return trackingNumber;
    }

    @Override
    public String toString() {
        return "Número de seguimiento: " + trackingNumber + "\n" +
                "Nombre del destinatario: " + recipientName + "\n" +
                "Dirección del destinatario: " + recipientAddress + "\n" +
                "Ciudad: " + city + "\n" +
                "Estado: " + state + "\n" +
                "Código postal: " + postalCode;
    }
}



