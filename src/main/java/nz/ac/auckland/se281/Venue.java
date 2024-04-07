package nz.ac.auckland.se281;

public class Venue {
  
  private String venueName;
  private String venueCode;
  private String capacityInput;
  private String hireFee;

  public Venue(String venueName, String venueCode, String capacityInput, String hireFee) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacityInput = capacityInput;
    this.hireFee = hireFee;
  }

  public String getVenueName() {
    return venueName;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public String getCapacityInput() {
    return capacityInput;
  }

  public String getHireFee() {
    return hireFee;
  }
}
