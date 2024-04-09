package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class Catering extends Service{

  CateringType cateringType;
  private Integer attendees;

  public Catering(String bookingreference, CateringType cateringType, Integer attendees) {
    super(bookingreference);
    this.cateringType = cateringType;
    this.attendees = attendees;
  }

  @Override
  public String getTotalCost() {
    Integer TotalCost = cateringType.getCostPerPerson() * attendees;
    return TotalCost.toString();
  }

}
