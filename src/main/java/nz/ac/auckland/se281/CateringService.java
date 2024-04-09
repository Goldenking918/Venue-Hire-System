package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class CateringService extends Service {

  protected CateringType cateringType;
  private Integer attendees;

  public CateringService(String bookingReference, CateringType cateringType, Integer attendees) {
    super(bookingReference);
    this.cateringType = cateringType;
    this.attendees = attendees;
  }

  @Override
  public String getTotalCost() {
    Integer totalCost = cateringType.getCostPerPerson() * attendees;
    return totalCost.toString();
  }
}
