package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private ArrayList<Venue> venues = new ArrayList<Venue>();
  private ArrayList<Booking> bookings = new ArrayList<Booking>();
  private ArrayList<Service> services = new ArrayList<Service>();
  private String dateInput;
  private String bookingName;
  private Integer capacity;
  private String hireFee;
  private String nextDate;
  private String venueName;
  private Integer cateringFees = 0;
  private Integer musicFees = 0;
  private Integer floralFees = 0;
  private Integer totalFees;

  public VenueHireSystem() {}

  public void printVenues() {
    // Changes the number and punctuation of the header message based on the number of venues.
    if (venues.size() == 0) {
      MessageCli.NO_VENUES.printMessage();
      return;
    } else if (venues.size() == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
    } else if (venues.size() >= 2 && venues.size() < 10) {
      String[] numbers = {"two", "three", "four", "five", "six", "seven", "eight", "nine"};
      MessageCli.NUMBER_VENUES.printMessage("are", numbers[venues.size() - 2], "s");
    } else {
      String size = Integer.toString(venues.size());
      MessageCli.NUMBER_VENUES.printMessage("are", size, "s");
    }
    // Checks when the next available date for each venue is.
    for (int i = 0; i < venues.size(); i++) {
      nextDate = dateInput;
      for (int j = 0; j < bookings.size(); j++) {
        if (bookings.get(j).getVenueCode().equals(venues.get(i).getVenueCode())
            && bookings.get(j).getBookingDate().equals(nextDate)) {
          String[] systemdate = nextDate.split("/");
          systemdate[0] = String.format("%02d", Integer.parseInt(systemdate[0]) + 1);
          nextDate = systemdate[0] + "/" + systemdate[1] + "/" + systemdate[2];
          j = 0;
        }
      }
      if (nextDate == null) {
        nextDate = "N/A";
      }
      // Prints the venue details.
      MessageCli.VENUE_ENTRY.printMessage(
          venues.get(i).getVenueName(),
          venues.get(i).getVenueCode(),
          venues.get(i).getCapacityInput(),
          venues.get(i).getHireFee(),
          nextDate);
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // Checks if the venue name is empty.
    if (venueName.trim() == "") {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }
    // Checks if the venue code exists.
    for (int i = 0; i < venues.size(); i++) {
      if (venues.get(i).getVenueCode().equals(venueCode)) {
        String name = venues.get(i).getVenueName();
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, name);
        return;
      }
    }
    // Checks if the hire fee and capacity are valid numbers.
    try {
      int hirefee = Integer.parseInt(hireFeeInput);
      if (hirefee < 0) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        return;
      }
    } catch (Exception e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }
    try {
      int capacity = Integer.parseInt(capacityInput);
      if (capacity < 0) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        return;
      }
    } catch (Exception e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }
    // Adds the venue to the list of venues and prints message for successful creation.
    venues.add(new Venue(venueName, venueCode, capacityInput, hireFeeInput));
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  public void setSystemDate(String dateInput) {
    // Adds inputed date to the system and prints message.
    this.dateInput = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);
  }

  public void printSystemDate() {
    // Checks if the date is set and prints it.
    if (this.dateInput == null) {
      MessageCli.CURRENT_DATE.printMessage("not set");
      return;
    }
    MessageCli.CURRENT_DATE.printMessage(this.dateInput);
  }

  public void makeBooking(String[] options) {
    // Checks if the date is set, if there are venues and if the venue exists.
    if (this.dateInput == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }
    if (venues.size() == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }
    for (int i = 0; i < venues.size(); i++) {
      if (venues.get(i).getVenueCode().equals(options[0])) {
        bookingName = venues.get(i).getVenueName();
        capacity = Integer.parseInt(venues.get(i).getCapacityInput());
        hireFee = venues.get(i).getHireFee();
        break;
      }
      if (i == venues.size() - 1) {
        MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
        return;
      }
    }
    // Adjusts the booking capacity if it is less than 25% of the venue capacity or greater than the
    // venue capacity.
    Integer bookingCapacity = Integer.parseInt(options[3]);
    if (bookingCapacity < (capacity / 4)) {
      bookingCapacity = capacity / 4;
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3], bookingCapacity.toString(), capacity.toString());
    } else if (bookingCapacity > capacity) {
      bookingCapacity = capacity;
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3], bookingCapacity.toString(), capacity.toString());
    }

    // Checks if the booking date is in the past or if the venue is already booked on that date.
    String[] systemDate = dateInput.split("/");
    String[] bookingDate = options[1].split("/");
    for (int i = 2; i >= 0; i--) {
      if (Integer.parseInt(bookingDate[i]) > Integer.parseInt(systemDate[i])) {
        break;
      } else if (Integer.parseInt(bookingDate[i]) < Integer.parseInt(systemDate[i])) {
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], dateInput);
        return;
      }
    }

    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).getVenueCode().equals(options[0])
          && bookings.get(i).getBookingDate().equals(options[1])) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(bookingName, options[1]);
        return;
      }
    }

    // Adds the booking to the list of bookings and prints message for successful creation.
    String bookingReference = BookingReferenceGenerator.generateBookingReference();
    bookings.add(
        new Booking(
            bookingName,
            options[0],
            capacity.toString(),
            hireFee,
            options[1],
            options[2],
            bookingReference,
            bookingCapacity.toString(),
            dateInput));
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        bookingReference, bookingName, options[1], bookingCapacity.toString());
  }

  public void printBookings(String venueCode) {
    // Checks if the venue exists and prints the bookings for that venue.
    for (int i = 0; i < venues.size(); i++) {
      if (venues.get(i).getVenueCode().equals(venueCode)) {
        venueName = venues.get(i).getVenueName();
        MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venues.get(i).getVenueName());
        break;
      }
      if (i == venues.size() - 1) {
        MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
        return;
      }
    }
    int j = 0;
    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).getVenueCode().equals(venueCode)) {
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
            bookings.get(i).getBookingNumber(), bookings.get(i).getBookingDate());
        j++;
      }
    }

    if (j == 0) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(venueName);
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // Checks if the booking exists and adds the catering service to the booking.
    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).getBookingNumber().equals(bookingReference)) {
        services.add(
            new CateringService(
                bookingReference, cateringType, Integer.parseInt(bookings.get(i).getAttendees())));
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
            "Catering (" + cateringType.getName() + ")", bookingReference);
        return;
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
  }

  public void addServiceMusic(String bookingReference) {
    // Checks if the booking exists and adds the music service to the booking.
    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).getBookingNumber().equals(bookingReference)) {
        services.add(new MusicService(bookingReference));
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
        return;
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // Checks if the booking exists and adds the floral service to the booking.
    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).getBookingNumber().equals(bookingReference)) {
        services.add(new FloralService(bookingReference, floralType));
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
            "Floral (" + floralType.getName() + ")", bookingReference);
        return;
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
  }

  public void viewInvoice(String bookingReference) {
    // Checks if the booking exists and prints the top half of the invoice.
    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).getBookingNumber().equals(bookingReference)) {
        MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
            bookingReference,
            bookings.get(i).getBookingEmail(),
            bookings.get(i).getCurrentDate(),
            bookings.get(i).getBookingDate(),
            bookings.get(i).getAttendees(),
            bookings.get(i).getVenueName());
        hireFee = bookings.get(i).getHireFee();
        MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(hireFee);
      }
    }
    // Prints the services and their costs.
    for (int i = 0; i < services.size(); i++) {
      if (services.get(i).getBookingReference().equals(bookingReference)) {
        if (services.get(i) instanceof CateringService) {
          cateringFees = Integer.parseInt(services.get(i).getTotalCost());
          MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
              ((CateringService) services.get(i)).cateringType.getName(),
              services.get(i).getTotalCost());
        } else if (services.get(i) instanceof MusicService) {
          musicFees = Integer.parseInt(services.get(i).getTotalCost());
          MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(services.get(i).getTotalCost());
        } else if (services.get(i) instanceof FloralService) {
          floralFees = Integer.parseInt(services.get(i).getTotalCost());
          MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
              ((FloralService) services.get(i)).floralType.getName(),
              services.get(i).getTotalCost());
        }
      }
    }
    totalFees = Integer.parseInt(hireFee) + cateringFees + musicFees + floralFees;
    MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(totalFees.toString());
  }
}
