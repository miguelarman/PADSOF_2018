package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import es.uam.eps.padsof.telecard.InvalidCardNumberException;

import application.App;
import application.dates.ModifiableDate;
import application.offer.*;
import exceptions.*;
import windows.*;

/**
 * Controller for the OfferWindow. This controller waits for the user to click
 * any buttons regarding actions to be applied to the offer:
 * <ul>
 * <li><b>View house: </b> The controller creates a window to visualize the
 * content of that house</li>
 * <li><b>View opinions: </b> The controller creates a window to visualize the
 * opinions of this offer</li>
 * <li><b>Book this offer: </b> The controller books this offer</li>
 * <li><b>Purchase this offer: </b> The controller purchases this offer. Is the
 * user is banned, it automatically logs out</li>
 * <li><b>View suggestions: </b> The controller creates a window to visualize
 * the suggestions for the offer to be approved</li>
 * <li><b>Modify offer: </b> The controller creates a dialog to modify the
 * offer</li>
 * </ul>
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez
 *         (alberto.gonzalezk@estudiante.uam.es)
 */
public class OfferWindowController implements ActionListener {
	
	/**
	 * View field for the controller. Contains the OfferWindow
	 */
	private OfferWindow window;
	/**
	 * Model field for the controller. Contains our app in its current state
	 */
	private App app;
	/**
	 * Previous window when accesing from the MyOffersWindow. Used to refresh the table
	 */
	private MyOffersWindow previousMyOffersWindow;
	/**
	 * Previous window when accesing from the SearchResultWindow. Used to refresh the table
	 */
	private SearchResultWindow previousSearchWindow;
	
	/**
	 * Constructor of the OfferWindowController class when accessing from MyOffersWindow
	 * 
	 * @param app Model for the controller
	 * @param window View for the controller
	 * @param mow Previous window
	 */
	public OfferWindowController(App app, OfferWindow window, MyOffersWindow mow) {
		this.app = app;
		this.window = window;
		this.previousMyOffersWindow = mow;
	}
	
	/**
	 * Constructor of the OfferWindowController class when accessing from SearchResultWindow
	 * 
	 * @param app Model for the controller
	 * @param window View for the controller
	 * @param srw Previous window
	 */
	public OfferWindowController(App app, OfferWindow window, SearchResultWindow srw) {
		this.app = app;
		this.window = window;
		this.previousSearchWindow = srw;
	}
	
	/**
	 * General constructor of this class, used mainly for testing purposes
	 * 
	 * @param app Model for the controller
	 * @param window View for the controller
	 */
	public OfferWindowController(App app, OfferWindow window) {
		this(app, window, (SearchResultWindow)null);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()) {
		case("View house"):
			HouseWindow newWindow = new HouseWindow(this.window.getOffer().getHouse());
			HouseWindowController h = new HouseWindowController(this.app, newWindow, this.window.getOffer().getHouse());
			newWindow.setController(h);
			newWindow.setGoBackController(new GoBackController(this.window, newWindow));
			newWindow.setVisible(true);
			break;
		case("View opinions"):
			OfferOpinionsWindow w = new OfferOpinionsWindow(this.window.getOffer());
			OfferOpinionsWindowController o = new OfferOpinionsWindowController(this.app, w, this.window.getOffer(), this.window);
			w.setController(o);
			w.setVisible(true);
			break;
		case("Book this offer"):
			Offer selectedOffer2 = this.window.getOffer();
			try {
				this.app.addReservation(selectedOffer2);
				JOptionPane.showMessageDialog(null, "The offer has been booked successfully!");
				this.window.refreshLabels();
				this.window.hideBookButton();
				if (this.previousSearchWindow != null) {
					this.previousSearchWindow.removeOffer(selectedOffer2);
				}
				if (this.previousMyOffersWindow != null) {
					this.previousMyOffersWindow.refreshStatus();
				}
			} catch (InvalidRolException e2) {
				JOptionPane.showMessageDialog(null, "Invalid role detected on our system. Please reboot", "Warning", JOptionPane.WARNING_MESSAGE);
			} catch (RestrictedUserException e2) {
				JOptionPane.showMessageDialog(null, "You cannot book this offer", "Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case("Purchase this offer"):
			for(Reservation r: App.getLoggedUser().getReservedOffers()) {
				if(r.getBookedOffer().equals(this.window.getOffer())) {
					Offer selectedOffer = this.window.getOffer();

					Object[] content = { "This operation will cost " + selectedOffer.getAmount() + "\nThe operation will be executed with the credit card " + App.getLoggedUser().getCreditCard() + "\n" + "Do you want to buy the offer?"};
					
					int option = JOptionPane.showConfirmDialog(null, content, "Payment", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						try {
							app.payReservation(r);
							JOptionPane.showMessageDialog(null, "The offer has been paid successfully!");
							this.window.refreshLabels();
							this.window.hideBookButton();
							this.window.hidePayButton();
							if (this.previousSearchWindow != null) {
								this.previousSearchWindow.removeOffer(r.getBookedOffer());
							}
							if (this.previousMyOffersWindow != null) {
								this.previousMyOffersWindow.refreshStatus();
							}
						} catch (InvalidCardNumberException e1) {
							app.logout();
							app.closeApp();
							
							app = App.openApp();
							LoginWindow newWindow2 = new LoginWindow();
							newWindow2.setController(new LoginController(this.app, newWindow2));
							this.window.setVisible(false);
							newWindow2.setVisible(true);
							JOptionPane.showMessageDialog(null, "Your credit card number was not valid. You are now banned from the system", "Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotTheReserverException e1) {
							JOptionPane.showMessageDialog(null, "Error detected", "Error", JOptionPane.ERROR_MESSAGE);
						} catch (TimeIsUpException e1) {
							JOptionPane.showMessageDialog(null, "The 5-day period to pay this offer has finished.\nYou cannot book or pay this offer again", "Error", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						} catch (RestrictedUserException e) {
							JOptionPane.showMessageDialog(null, "You cannot purchase this offer", "Error", JOptionPane.ERROR_MESSAGE);
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "Operation cancelled correctly");
					}
					return;
				}
			}
			Offer selectedOffer = this.window.getOffer();

			Object[] content = { "This operation will cost " + selectedOffer.getAmount() + "\n" + "The operation will be executed with the creditcard " + App.getLoggedUser().getCreditCard() + "\n" + "Do you want to buy the offer?"};
			
			int option = JOptionPane.showConfirmDialog(null, content, "Payment", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				try {
					app.payOffer(selectedOffer);
					JOptionPane.showMessageDialog(null, "The offer has been paid successfully!");
					this.window.refreshLabels();
					this.window.refreshLabels();
					this.window.hideBookButton();
					this.window.hidePayButton();
					if (this.previousSearchWindow != null) {
						this.previousSearchWindow.removeOffer(selectedOffer);
					}
					if (this.previousMyOffersWindow != null) {
						this.previousMyOffersWindow.refreshStatus();
					}
				} catch (InvalidCardNumberException e1) {
					app.logout();
					app.closeApp();
					
					app = App.openApp();
					LoginWindow newWindow2 = new LoginWindow();
					newWindow2.setController(new LoginController(this.app, newWindow2));
					this.window.setVisible(false);
					newWindow2.setVisible(true);
					JOptionPane.showMessageDialog(null, "Your credit card number was not valid. You are now banned from the system", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (RestrictedUserException e) {
					JOptionPane.showMessageDialog(null, "You cannot purchase this offer", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Operation cancelled correctly");
			}

			break;
		case("View suggestions"):
			SuggestionsWindow window2 = new SuggestionsWindow(this.app.getRequests(this.window.getOffer()));
			window2.setGoBackController(new GoBackController(this.window, window2));
			window2.setVisible(true);
			break;
		case("Modify offer"):
			Offer o1 = this.window.getOffer();
		
			if (o1.getType().equals(OfferType.HOLIDAY)) {
				HolidayOffer ho = (HolidayOffer)o1;
				
				JCalendar holidayStartingDateField = new JCalendar();
				holidayStartingDateField.setTodayButtonVisible(true);
				holidayStartingDateField.setWeekOfYearVisible(false);
				JTextField holidayPriceField = new JTextField();
				JTextField holidayDepositField = new JTextField();
				JTextField holidayDescriptionField = new JTextField();
				JCalendar holidayFinishDateField = new JCalendar();
				holidayFinishDateField.setTodayButtonVisible(true);
				holidayFinishDateField.setWeekOfYearVisible(false);
				
				// Setting all the fields
				holidayStartingDateField.setDate(Date.from(ho.getDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				holidayPriceField.setText("" + (ho.getAmount() - ho.getDeposit()));
				holidayDepositField.setText("" + ho.getDeposit());
				holidayDescriptionField.setText(ho.getDescription());
				holidayFinishDateField.setDate(Date.from(
						ho.getFinishLocalDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				
				Object[] holidayQuery = { "Starting date:", holidayStartingDateField, "Price:", holidayPriceField,
						"Desposit:", holidayDepositField, "Description:", holidayDescriptionField, "Finish date:", holidayFinishDateField };

				int holidayOption = JOptionPane.showConfirmDialog(null, holidayQuery, "Modify a holiday offer",
						JOptionPane.OK_CANCEL_OPTION);
				
				if (holidayOption == JOptionPane.CANCEL_OPTION) {
					JOptionPane.showMessageDialog(null, "Operation cancelled succesfully");
				} else if (holidayOption == JOptionPane.OK_OPTION) {
					if (holidayPriceField.getText().equals("") || holidayDepositField.getText().equals("")
							|| holidayDescriptionField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please fill in all the fields before clicking this button",
								"Error", JOptionPane.ERROR_MESSAGE);
						this.actionPerformed(arg0);
						return;
					}

					try {
						LocalDate startingDate = Instant.ofEpochMilli(holidayStartingDateField.getDate().getTime())
								.atZone(ZoneId.systemDefault()).toLocalDate();
						Double price = Double.parseDouble(holidayPriceField.getText());
						Double deposit = Double.parseDouble(holidayDepositField.getText());
						String description = holidayDescriptionField.getText();
						LocalDate finishDate = Instant.ofEpochMilli(holidayFinishDateField.getDate().getTime())
								.atZone(ZoneId.systemDefault()).toLocalDate();

						// Modify the offer
						ho.modifyOffer(startingDate, finishDate);
						ho.modifyOffer(description);
						ho.modifyOffer(price, deposit);
						ho.modifyOffer(OfferStatus.PENDING_FOR_APPROVAL);
						
						this.window.refreshLabels();
						
						if (previousMyOffersWindow != null) {
							this.previousMyOffersWindow.refreshStatus();
						}

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null,
								"Please enter price and deposit in a suitable format, i.e. 0.25 or 34.21", "Error",
								JOptionPane.ERROR_MESSAGE);
						// We restart the process
						this.actionPerformed(arg0);
						return;
					} catch (NotTheOwnerException e) {
						JOptionPane.showMessageDialog(null,
								"You have to be logged as the host who owns the house to modify this offer", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					} catch (InvalidOfferStatusException e) {
						JOptionPane.showMessageDialog(null, "You cannot modify an offer unless it is pending for changes", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					JOptionPane.showMessageDialog(null, "Offer modified successfully");
					this.window.refreshLabels();
					this.window.hideModifyButton();
				}
			} if (o1.getType().equals(OfferType.LIVING)) {
				
				LivingOffer lo = (LivingOffer)o1;
				
				JCalendar livingStartingDateField = new JCalendar();
				livingStartingDateField.setTodayButtonVisible(true);
				livingStartingDateField.setWeekOfYearVisible(false);
				livingStartingDateField.setDate(Date.from(ModifiableDate.getModifiableDate().atStartOfDay()
						.atZone(ZoneId.systemDefault()).toInstant()));
				JTextField livingPriceField = new JTextField();
				JTextField livingDepositField = new JTextField();
				JTextField livingDescriptionField = new JTextField();
				JTextField livingDuration = new JTextField();
				
				// Setting all the fields
				livingStartingDateField.setDate(Date.from(lo.getDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				livingPriceField.setText("" + (lo.getAmount() - lo.getDeposit()));
				livingDepositField.setText("" + lo.getDeposit());
				livingDescriptionField.setText(lo.getDescription());
				livingDuration.setText("" + lo.getNumberOfMonths());
				
				Object[] livingQuery = { "Starting date:", livingStartingDateField, "Price:", livingPriceField,
						"Desposit:", livingDepositField, "Description:", livingDescriptionField, "Duration:", livingDuration };

				int livingOption = JOptionPane.showConfirmDialog(null, livingQuery, "Modify a living offer",
						JOptionPane.OK_CANCEL_OPTION);
				
				if (livingOption == JOptionPane.CANCEL_OPTION) {
					JOptionPane.showMessageDialog(null, "Operation cancelled succesfully");
				} else if (livingOption == JOptionPane.OK_OPTION) {
					if (livingPriceField.getText().equals("") || livingDepositField.getText().equals("")
							|| livingDescriptionField.getText().equals("") || livingDuration.getText().equals("")) {
						JOptionPane.showMessageDialog(null,
								"Please fill in all the fields before clicking this button", "Error",
								JOptionPane.ERROR_MESSAGE);
						this.actionPerformed(arg0);
						return;
					}

					try {
						LocalDate startingDate = Instant.ofEpochMilli(livingStartingDateField.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
						Double price = Double.parseDouble(livingPriceField.getText());
						Double deposit = Double.parseDouble(livingDepositField.getText());
						String description = livingDescriptionField.getText();
						Integer duration = Integer.parseInt(livingDuration.getText());
						
						// Modify the offer
						lo.modifyOffer(startingDate);
						lo.modifyOffer(description);
						lo.modifyOffer(price, deposit);
						lo.modifyOffer(duration);
						lo.modifyOffer(OfferStatus.PENDING_FOR_APPROVAL);
						
						this.window.refreshLabels();
						if (previousMyOffersWindow != null) {
							this.previousMyOffersWindow.refreshStatus();
						}
						this.window.hideModifyButton();
						
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Please enter price, deposit and duration in a suitable format, i.e. 0.25 or 34.21", "Error", JOptionPane.ERROR_MESSAGE);
						// We restart the process
						this.actionPerformed(arg0);
						return;
					} catch (NotTheOwnerException e) {
						JOptionPane.showMessageDialog(null, "You have to be logged as the host who owns the house to create this offer", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					} catch (InvalidOfferStatusException e) {
						JOptionPane.showMessageDialog(null, "You cannot modify an offer unless it is pending for changes", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					JOptionPane.showMessageDialog(null, "Offer created successfully");
					this.window.refreshLabels();
				}
			}
			
			break;
		default:
			break;
		}
	}

}
