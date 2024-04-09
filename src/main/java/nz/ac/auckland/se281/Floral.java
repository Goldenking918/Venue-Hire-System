package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.FloralType;

public class Floral extends Service{
  FloralType floralType;

  public Floral(String bookingreference, FloralType floralType) {
    super(bookingreference);
    this.floralType = floralType;
  }

  @Override
  public String getTotalCost() {
    return Integer.toString(floralType.getCost());
  }

}
