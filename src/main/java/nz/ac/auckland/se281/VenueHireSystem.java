package nz.ac.auckland.se281;

import java.lang.reflect.Array;
import java.util.ArrayList;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  public ArrayList<String> venueNames = new ArrayList<String>();
  public ArrayList<String> venueCodes = new ArrayList<String>();
  public ArrayList<String> capacities = new ArrayList<String>();
  public ArrayList<String> hirefees = new ArrayList<String>();
  public String dateInput;
  


  public VenueHireSystem() {}

  public void printVenues() {
    if (venueNames.size() == 0) {
      MessageCli.NO_VENUES.printMessage();
      return;
    }
    else if (venueNames.size() == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
    }
    else if (venueNames.size() >= 2 && venueNames.size() < 10) {
      String[] numbers = {"two", "three", "four", "five", "six", "seven", "eight", "nine"};
      MessageCli.NUMBER_VENUES.printMessage("are", numbers[venueNames.size() - 2], "s");
    }
    else {
      String size = Integer.toString(venueNames.size());
      MessageCli.NUMBER_VENUES.printMessage("are", size, "s");
    }
    for (int i = 0; i < venueNames.size(); i++) {
      MessageCli.VENUE_ENTRY.printMessage(venueNames.get(i), venueCodes.get(i), capacities.get(i), hirefees.get(i), "N/A");
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

        if (venueName.trim() == "") {
          MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
          return;
        }
        for (String a: venueCodes) {
          if (a.equals(venueCode)) {
            String name = venueNames.get(venueCodes.indexOf(a));
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
        venueNames.add(venueName);
        venueCodes.add(venueCode);
        capacities.add(capacityInput);
        hirefees.add(hireFeeInput);
        MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
    
  }

  public void setSystemDate(String dateInput) {
    this.dateInput = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);
  }

  public void printSystemDate() {
    MessageCli.CURRENT_DATE.printMessage(this.dateInput);
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
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
