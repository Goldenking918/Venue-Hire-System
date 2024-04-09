package nz.ac.auckland.se281;

public class MusicService extends Service {

  public MusicService(String bookingReference) {
    super(bookingReference);
  }

  @Override
  public String getTotalCost() {
    return "500";
  }
}
