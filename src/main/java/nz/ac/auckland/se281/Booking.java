package nz.ac.auckland.se281;

public class Booking extends Venue {
  private String bookingDate;
  private String bookingEmail;
  private String bookingNumber;
  private String attendees;
  private String currentDate;

  public Booking(
      String venueName,
      String venueCode,
      String capacityInput,
      String hireFee,
      String bookingDate,
      String bookingEmail,
      String bookingNumber,
      String attendees,
      String currentDate) {
    super(venueName, venueCode, capacityInput, hireFee);
    this.bookingDate = bookingDate;
    this.bookingEmail = bookingEmail;
    this.bookingNumber = bookingNumber;
    this.attendees = attendees;
    this.currentDate = currentDate;
  }

  public String getBookingDate() {
    return bookingDate;
  }

  public String getBookingEmail() {
    return bookingEmail;
  }

  public String getBookingNumber() {
    return bookingNumber;
  }

  public String getAttendees() {
    return attendees;
  }

  public String getCurrentDate() {
    return currentDate;
  }
}
