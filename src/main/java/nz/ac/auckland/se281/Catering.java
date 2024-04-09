package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class Catering extends Service{

  protected CateringType cateringType;
  private Integer attendees;

  public Catering(String bookingReference, CateringType cateringType, Integer attendees) {
    super(bookingReference);
    this.cateringType = cateringType;
    this.attendees = attendees;
  }

  @Override
  public String getTotalCost() {
    Integer TotalCost = cateringType.getCostPerPerson() * attendees;
    return TotalCost.toString();
  }

}
