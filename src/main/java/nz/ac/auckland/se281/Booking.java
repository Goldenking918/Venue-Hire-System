package nz.ac.auckland.se281;

public class Booking extends Venue{
  private String bookingdate;
  private String bookingemail;
  private String bookingnumber;
  private String attendees;
  private String currentdate;

  public Booking(String venueName, String venueCode, String capacityInput, String hireFee, String bookingdate, String bookingemail, String bookingnumber, String attendees, String currentdate) {
    super(venueName, venueCode, capacityInput, hireFee);
    this.bookingdate = bookingdate;
    this.bookingemail = bookingemail;
    this.bookingnumber = bookingnumber;
    this.attendees = attendees;
    this.currentdate = currentdate;
  }

  public String getBookingDate() {
    return bookingdate;
  }

  public String getBookingEmail() {
    return bookingemail;
  }

  public String getBookingNumber() {
    return bookingnumber;
  }

  public String getAttendees() {
    return attendees;
  }

  public String getCurrentDate() {
    return currentdate;
  }

}
