package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  public ArrayList<Venue> venues = new ArrayList<Venue>();
  public String dateInput;
  public String bookingname;
  public Integer capacity;
  
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
      MessageCli.VENUE_ENTRY.printMessage(venues.get(i).getVenueName(), venues.get(i).getVenueCode(), venues.get(i).getCapacityInput(), venues.get(i).getHireFee(), "N/A");
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
    
    String bookingReference = BookingReferenceGenerator.generateBookingReference();
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(bookingReference, bookingname, options[1], bookingcapacity.toString());
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
