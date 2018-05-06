package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import application.App;
import application.dates.ModifiableDate;
import application.offer.House;
import application.offer.Offer;
import application.offer.OfferType;
import application.users.*;
import application.users.RegisteredUser.Role;
import exceptions.InvalidDateException;
import exceptions.InvalidRolException;
import exceptions.NoRowSelectedException;
import exceptions.NoUserLoggedException;
import exceptions.NotTheOwnerException;
import exceptions.OfferAlreadyCreatedException;
import windows.MyOffersWindow;
import windows.OfferWindow;

public class MyOffersController implements ActionListener {

	private App app;
	private MyOffersWindow window;

	public MyOffersController(App app, MyOffersWindow w) {
		this.app = app;
		this.window = w;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("View offer")) {
			try {
				Offer offer = this.window.getSelection();

				OfferWindow window = new OfferWindow(offer, App.getLoggedUser().getRole());
				OfferWindowController controller = new OfferWindowController(this.app, window);
				window.setController(controller);
				window.setGoBackController(new GoBackController(this.window, window));
				window.setVisible(true);
				this.window.setVisible(false);

			} catch (NoRowSelectedException e) {
				JOptionPane.showMessageDialog(null, "You must select an offer before clicking this button", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		} else if (arg0.getActionCommand().equals("Create an offer")) {
			OfferType[] offerTypes = { OfferType.HOLIDAY, OfferType.LIVING };
			JComboBox<OfferType> typeOfOfferDropDown = new JComboBox<OfferType>(offerTypes);
			Object[] query = {
					"Select the desired type of offer", typeOfOfferDropDown
			};
			
			if (App.getLoggedUser().getRole().equals(Role.HOST)) {
				if (((Host) (App.getLoggedUser())).getHouses().stream().map(House::getZipCode).collect(Collectors.toList()).toArray(new String[0]).length < 1) {
					JOptionPane.showMessageDialog(null, "You don't have any houses. Please create one before creating offers", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
			} else if (App.getLoggedUser().getRole().equals(Role.MULTIROLE)) {
				if (((MultiRoleUser) (App.getLoggedUser())).getHouses().stream().map(House::getZipCode).collect(Collectors.toList()).toArray(new String[0]).length < 1) {
					JOptionPane.showMessageDialog(null, "You don't have any houses. Please create one before creating offers", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}

			int option = JOptionPane.showConfirmDialog(null, query, "Add a characteristic",
					JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(null, "Operation cancelled succesfully");
			} else if (option == JOptionPane.OK_OPTION) {

				switch ((OfferType) typeOfOfferDropDown.getSelectedItem()) {
				case HOLIDAY:
					// Create an offer of the type holiday

					JCalendar holidayStartingDateField = new JCalendar();
					holidayStartingDateField.setTodayButtonVisible(true);
					holidayStartingDateField.setWeekOfYearVisible(false);
					holidayStartingDateField.setDate(Date.from(ModifiableDate.getModifiableDate().atStartOfDay()
							.atZone(ZoneId.systemDefault()).toInstant()));
					JTextField holidayPriceField = new JTextField();
					JTextField holidayDepositField = new JTextField();
					JTextField holidayDescriptionField = new JTextField();
					String[] zipCodes;
					if (App.getLoggedUser().getRole().equals(Role.HOST)) {
						zipCodes = ((Host) (App.getLoggedUser())).getHouses().stream().map(House::getZipCode).collect(Collectors.toList()).toArray(new String[0]);
					} else if (App.getLoggedUser().getRole().equals(Role.MULTIROLE)) {
						zipCodes = ((MultiRoleUser) (App.getLoggedUser())).getHouses().stream().map(House::getZipCode).collect(Collectors.toList()).toArray(new String[0]);
					} else {
						zipCodes = new String[0];
					}
							
					JComboBox<String> holidayHouseField = new JComboBox<String>(zipCodes);
					JCalendar holidayFinishDateField = new JCalendar();
					holidayFinishDateField.setTodayButtonVisible(true);holidayFinishDateField.setWeekOfYearVisible(false);holidayFinishDateField.setDate(Date.from(ModifiableDate.getModifiableDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
					Object[] holidayQuery = {
							"Starting date:", holidayStartingDateField,
							"Price:", holidayPriceField,
							"Desposit:", holidayDepositField,
							"Description:", holidayDescriptionField,
							"Offered house:", holidayHouseField,
							"Finish date:", holidayFinishDateField							
					};
					
					int holidayOption = JOptionPane.showConfirmDialog(null, holidayQuery, "Create a holiday offer",
							JOptionPane.OK_CANCEL_OPTION);
					if (holidayOption == JOptionPane.CANCEL_OPTION) {
						JOptionPane.showMessageDialog(null, "Operation cancelled succesfully");
					} else if (holidayOption == JOptionPane.OK_OPTION) {
						if (holidayPriceField.getText().equals("") || holidayDepositField.getText().equals("")
								|| holidayDescriptionField.getText().equals("")) {
							JOptionPane.showMessageDialog(null,
									"Please fill in all the fields before clicking this button", "Error",
									JOptionPane.ERROR_MESSAGE);
							this.actionPerformed(arg0);
							return;
						}

						Offer createdOffer;

						try {
							LocalDate startingDate = Instant.ofEpochMilli(holidayStartingDateField.getDate().getTime())
									.atZone(ZoneId.systemDefault()).toLocalDate();
							Double price = Double.parseDouble(holidayPriceField.getText());
							Double deposit = Double.parseDouble(holidayDepositField.getText());
							String description = holidayDescriptionField.getText();
							House offeredHouse;
							if (App.getLoggedUser().getRole().equals(Role.HOST)) {
								offeredHouse = ((Host) (App.getLoggedUser())).getHouses().stream().filter(new Predicate<House>() {
									@Override
									public boolean test(House h) {
										return h.getZipCode().equals(holidayHouseField.getSelectedItem());
									}
								}).findFirst().orElse(null);
							} else if (App.getLoggedUser().getRole().equals(Role.MULTIROLE)) {
								offeredHouse = ((MultiRoleUser) (App.getLoggedUser())).getHouses().stream().filter(new Predicate<House>() {
									@Override
									public boolean test(House h) {
										return h.getZipCode().equals(holidayHouseField.getSelectedItem());
									}
								}).findFirst().orElse(null);
							} else {
								offeredHouse = new House("", "", (Host)null);
							}
									
							LocalDate finishDate = Instant.ofEpochMilli(holidayFinishDateField.getDate().getTime())
									.atZone(ZoneId.systemDefault()).toLocalDate();

							createdOffer = this.app.createHolidayOffer(startingDate, price, deposit, description,
									offeredHouse, finishDate);

						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null,
									"Please enter price and deposit in a suitable format, i.e. 0.25 or 34.21", "Error",
									JOptionPane.ERROR_MESSAGE);
							// We restart the process
							this.actionPerformed(arg0);
							return;
						} catch (InvalidRolException | NotTheOwnerException | NoUserLoggedException e) {
							JOptionPane.showMessageDialog(null,
									"You have to be logged as the host who owns the house to create this offer",
									"Error", JOptionPane.ERROR_MESSAGE);
							return;
						} catch (InvalidDateException e) {
							JOptionPane.showMessageDialog(null, "Please enter suitable dates for the offer", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						} catch (OfferAlreadyCreatedException e) {
							JOptionPane.showMessageDialog(null, "This offer has already been created", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						JOptionPane.showMessageDialog(null, "Offer created successfully");
						this.window.addOfferToTable(createdOffer);
					}
					break;
				case LIVING:
					// Create an offer of the type living

					JCalendar livingStartingDateField = new JCalendar();
					livingStartingDateField.setTodayButtonVisible(true);
					livingStartingDateField.setWeekOfYearVisible(false);
					livingStartingDateField.setDate(Date.from(ModifiableDate.getModifiableDate().atStartOfDay()
							.atZone(ZoneId.systemDefault()).toInstant()));
					JTextField livingPriceField = new JTextField();
					JTextField livingDepositField = new JTextField();
					JTextField livingDescriptionField = new JTextField();
					String[] livingZipCodes;
					if (App.getLoggedUser().getRole().equals(Role.HOST)) {
						livingZipCodes = ((Host) (App.getLoggedUser())).getHouses().stream().map(House::getZipCode).collect(Collectors.toList()).toArray(new String[0]);
					} else if (App.getLoggedUser().getRole().equals(Role.MULTIROLE)) {
						livingZipCodes = ((MultiRoleUser) (App.getLoggedUser())).getHouses().stream().map(House::getZipCode).collect(Collectors.toList()).toArray(new String[0]);
					} else {
						livingZipCodes = new String[0];
					}
							
					JComboBox<String> livingHouseField = new JComboBox<String>(livingZipCodes);
					JTextField livingDuration = new JTextField();
					Object[] livingQuery = { "Starting date:", livingStartingDateField, "Price:", livingPriceField,
							"Desposit:", livingDepositField, "Description:", livingDescriptionField, "Offered house:",
							livingHouseField, "Duration:", livingDuration };

					int livingOption = JOptionPane.showConfirmDialog(null, livingQuery, "Create a living offer",
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

						Offer createdOffer;
						
						try {
							LocalDate startingDate = Instant.ofEpochMilli(livingStartingDateField.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
							Double price = Double.parseDouble(livingPriceField.getText());
							Double deposit = Double.parseDouble(livingDepositField.getText());
							String description = livingDescriptionField.getText();
							House offeredHouse;
							if (App.getLoggedUser().getRole().equals(Role.HOST)) {
								offeredHouse = ((Host)(App.getLoggedUser())).getHouses().stream().filter(new Predicate<House>() {
									@Override
									public boolean test(House h) {
										return h.getZipCode().equals(livingHouseField.getSelectedItem());
									}
								}).findFirst().orElse(null);
							} else if (App.getLoggedUser().getRole().equals(Role.MULTIROLE)) {
								offeredHouse = ((MultiRoleUser)(App.getLoggedUser())).getHouses().stream().filter(new Predicate<House>() {
									@Override
									public boolean test(House h) {
										return h.getZipCode().equals(livingHouseField.getSelectedItem());
									}
								}).findFirst().orElse(null);
							} else {
								offeredHouse = new House("", "", (Host)null);
							}
								
							Integer duration = Integer.parseInt(livingDuration.getText());
							
							
							createdOffer = this.app.createLivingOffer(startingDate, price, deposit, description, offeredHouse, duration);
							
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Please enter price, deposit and duration in a suitable format, i.e. 0.25 or 34.21", "Error", JOptionPane.ERROR_MESSAGE);
							// We restart the process
							this.actionPerformed(arg0);
							return;
						} catch (InvalidRolException | NotTheOwnerException | NoUserLoggedException e) {
							JOptionPane.showMessageDialog(null, "You have to be logged as the host who owns the house to create this offer", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						} catch (InvalidDateException e) {
							JOptionPane.showMessageDialog(null, "Please enter suitable dates for the offer", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						} catch (OfferAlreadyCreatedException e) {
							JOptionPane.showMessageDialog(null, "This offer has already been created", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						JOptionPane.showMessageDialog(null, "Offer created successfully");
						this.window.addOfferToTable(createdOffer);
					}
					break;
				default:
					break;
				}
			}
		}
		
	}

}
