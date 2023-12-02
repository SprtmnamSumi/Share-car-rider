package cz.muni.fi.pv168.project.storage.sql.entity;

public class TemplateEntity extends Entity {

    private String title;
    private String description;
    private Double distance;
    private double fuelConsumption;
    private double costOfFuelPerLitre;
    private int numberOfPassengers;
    private double commission;

    private String categoryGuid;
    private String currencyGuid;
    private double newestConversionRate;

    public TemplateEntity(Long id, String guid, String title, String description, Double distance, double fuelConsumption,
                          double costOfFuelPerLitre, int numberOfPassengers, double commission,
                          String categoryGuid, String currencyID, double newestConversionRate) {
        super(id, guid);

        this.title = title;
        this.description = description;
        this.distance = distance;
        this.fuelConsumption = fuelConsumption;
        this.costOfFuelPerLitre = costOfFuelPerLitre;
        this.numberOfPassengers = numberOfPassengers;
        this.commission = commission;

        this.categoryGuid = categoryGuid;
        this.currencyGuid = currencyID;
        this.newestConversionRate = newestConversionRate;
    }

    // Add getters and setters for each field


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getCostOfFuelPerLitre() {
        return costOfFuelPerLitre;
    }

    public void setCostOfFuelPerLitre(double costOfFuelPerLitre) {
        this.costOfFuelPerLitre = costOfFuelPerLitre;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String getCategoryGuid() {
        return categoryGuid;
    }

    public void setCategoryGuid(String categoryGuid) {
        this.categoryGuid = categoryGuid;
    }

    public String getCurrencyGuid() {
        return currencyGuid;
    }

    public void setCurrencyGuid(String currencyGuid) {
        this.currencyGuid = currencyGuid;
    }

    public double getNewestConversionRate() {
        return newestConversionRate;
    }

    public void setNewestConversionRate(double newestConversionRate) {
        this.newestConversionRate = newestConversionRate;
    }
}

