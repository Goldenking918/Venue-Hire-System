package nz.ac.auckland.se281;

public class Booking extends Venue{
  private String bookingdate;
  private String bookingemail;
  private String bookingnumber;
  private String attendees;

  public Booking(String venueName, String venueCode, String capacityInput, String hireFee, String bookingdate, String bookingemail, String bookingnumber, String attendees) {
    super(venueName, venueCode, capacityInput, hireFee);
    this.bookingdate = bookingdate;
    this.bookingemail = bookingemail;
    this.bookingnumber = bookingnumber;
    this.attendees = attendees;
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

}
