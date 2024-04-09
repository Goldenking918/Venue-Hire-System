package nz.ac.auckland.se281;

public class Music extends Service{

  public Music(String bookingReference) {
    super(bookingReference);

  }

  @Override
  public String getTotalCost() {
    return "500";
  }

}
