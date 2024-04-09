package nz.ac.auckland.se281;

public class Music extends Service{

  public Music(String bookingreference) {
    super(bookingreference);

  }

  @Override
  public String getTotalCost() {
    return "500";
  }

}
