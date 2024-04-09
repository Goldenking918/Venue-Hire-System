package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  public ArrayList<Venue> venues = new ArrayList<Venue>();
  public ArrayList<Booking> bookings = new ArrayList<Booking>();
  public ArrayList <Service> services = new ArrayList<Service>();
  public String dateInput;
  public String bookingname;
  public Integer capacity;
  public String hirefee;
  public String nextdate;
  public String venuename;

  
  public VenueHireSystem() {}

  public void printVenues() {
    if (venues.size() == 0) {
      MessageCli.NO_VENUES.printMessage();
      return;
    }
    else if (venues.size() == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
    }
    else if (venues.size() >= 2 && venues.size() < 10) {
      String[] numbers = {"two", "three", "four", "five", "six", "seven", "eight", "nine"};
      MessageCli.NUMBER_VENUES.printMessage("are", numbers[venues.size() - 2], "s");
    }
    else {
      String size = Integer.toString(venues.size());
      MessageCli.NUMBER_VENUES.printMessage("are", size, "s");
    }
    for (int i = 0; i < venues.size(); i++) {
      nextdate = dateInput;
      for (int j = 0; j < bookings.size(); j++) {
        if (bookings.get(j).getVenueCode().equals(venues.get(i).getVenueCode()) && bookings.get(j).getBookingDate().equals(nextdate)) {
          String[] systemdate = nextdate.split("/");
          systemdate[0] = String.format("%02d", Integer.parseInt(systemdate[0]) + 1);
          nextdate = systemdate[0] + "/" + systemdate[1] + "/" + systemdate[2];
          j = 0;
        }
      }

      MessageCli.VENUE_ENTRY.printMessage(venues.get(i).getVenueName(), venues.get(i).getVenueCode(), venues.get(i).getCapacityInput(), venues.get(i).getHireFee(), nextdate);
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

        if (venueName.trim() == "") {
          MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
          return;
        }
        for (int i = 0; i < venues.size(); i++) {
          if (venues.get(i).getVenueCode().equals(venueCode)) {
            String name = venues.get(i).getVenueName();
            MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, name);
            return;
          }
        }
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

        venues.add(new Venue(venueName, venueCode, capacityInput, hireFeeInput));
        MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
    
  }

  public void setSystemDate(String dateInput) {
    this.dateInput = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);
  }

  public void printSystemDate() {
    if (this.dateInput == null) {
      MessageCli.CURRENT_DATE.printMessage("not set");
      return;
    }
    MessageCli.CURRENT_DATE.printMessage(this.dateInput);
  }

  public void makeBooking(String[] options) {
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
        bookingname = venues.get(i).getVenueName();
        capacity = Integer.parseInt(venues.get(i).getCapacityInput());
        hirefee = venues.get(i).getHireFee();
        break;
      }
      if (i == venues.size() - 1) {
        MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
        return;
      }
      
    }
    Integer bookingcapacity = Integer.parseInt(options[3]);
    if (bookingcapacity < (capacity/4)) {
      bookingcapacity = capacity/4;
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(options[3], bookingcapacity.toString(), capacity.toString());
    }
    else if (bookingcapacity > capacity) {
      bookingcapacity = capacity;
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(options[3], bookingcapacity.toString(), capacity.toString());
    }

// options[0] = "FFH"
// options[1] = "26/02/2024"
// options[2] = "client001@email.com"
// options[3] = "70"
// String day = dateParts[0];   // "26"
// String month = dateParts[1]; // "02"
// String year = dateParts[2];  // "2024"
    String[] systemdate = dateInput.split("/");
    String[] bookingdate = options[1].split("/");
    for (int i = 2; i >= 0; i--) {
      if (Integer.parseInt(bookingdate[i]) > Integer.parseInt(systemdate[i])) {
        break;
      }
      else
      if (Integer.parseInt(bookingdate[i]) < Integer.parseInt(systemdate[i])) {
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], dateInput);
        return;
      }
    }

    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).getVenueCode().equals(options[0]) && bookings.get(i).getBookingDate().equals(options[1])) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(bookingname, options[1]);
        return;
      }
    }
    
    String bookingReference = BookingReferenceGenerator.generateBookingReference();
    bookings.add(new Booking(bookingname, options[0], capacity.toString(), hirefee, options[1], options[2], bookingReference, bookingcapacity.toString()));
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(bookingReference, bookingname, options[1], bookingcapacity.toString());
  }

  public void printBookings(String venueCode) {
    for (int i = 0; i < venues.size(); i++) {
      if (venues.get(i).getVenueCode().equals(venueCode)) {
        venuename = venues.get(i).getVenueName();
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
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(bookings.get(i).getBookingNumber(), bookings.get(i).getBookingDate());
        j++;
        }
      }

    if (j == 0) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(venuename);
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).getBookingNumber().equals(bookingReference)) {
        services.add(new Catering(bookingReference, cateringType, Integer.parseInt(bookings.get(i).getAttendees())));
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Catering ("+cateringType.getName()+")", bookingReference);
        return;
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
  }

  public void addServiceMusic(String bookingReference) {
    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).getBookingNumber().equals(bookingReference)) {
        services.add(new Music(bookingReference));
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
        return;
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).getBookingNumber().equals(bookingReference)) {
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Floral ("+ floralType.getName()+")", bookingReference);
        return;
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
