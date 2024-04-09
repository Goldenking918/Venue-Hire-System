package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.FloralType;

public class Floral extends Service{
  protected FloralType floralType;

  public Floral(String bookingReference, FloralType floralType) {
    super(bookingReference);
    this.floralType = floralType;
  }

  @Override
  public String getTotalCost() {
    return Integer.toString(floralType.getCost());
  }

}
