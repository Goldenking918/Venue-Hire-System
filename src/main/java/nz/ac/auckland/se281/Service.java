package nz.ac.auckland.se281;

public abstract class Service {
  protected String bookingreference; 

  public Service(String bookingreference) {
    this.bookingreference = bookingreference;
  }

  public abstract String getTotalCost(); {
  }

  public String getBookingReference() {
    return bookingreference;
  }
}
