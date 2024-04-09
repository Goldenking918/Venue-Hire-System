package nz.ac.auckland.se281;

public abstract class Service {
  protected String bookingReference;

  public Service(String bookingReference) {
    this.bookingReference = bookingReference;
  }

  public abstract String getTotalCost();

  {
  }

  public String getBookingReference() {
    return bookingReference;
  }
}
