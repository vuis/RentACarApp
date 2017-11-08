package service;

import java.text.ParseException;
import java.util.List;

import domain.Accessories;
import domain.Car;
import domain.CarHistory;
import domain.Contract;
import domain.Customer;
import domain.Employee;
import domain.Insurance;
import domain.Office;
import domain.PriceAdditions;
import domain.Reservation;
import domain.TransferCard;

public interface ServiceInterface {

	List<Customer> fetchAllCustomers();

	Customer fetchCustomerById(int id);

	List<Employee> fetchAllEmployees();

	Employee fetchEmployeeById(int id);

	Employee fetchEmployeeByUserName(String username);

	List<Office> fetchAllOffices();

	Office fetchOfficeById(int id);

	List<Car> fetchAllCars();

	List<Car> fetchCarsForOffice(int officeId);

	List<Car> fetchIdleCars();

	List<Car> fetchIdleCarsForOffice(int officeId);

	Car fetchCarById(int id);

	List<Reservation> fetchAllReservations();

	Reservation fetchReservationById(int id);

	List<Reservation> fetchAllOpenedReservations();

	List<Reservation> fetchOpenedReservationsForOffice(int officeId);

	List<Reservation> fetchClosedReservationsForOffice(int officeId);

	List<Reservation> fetchAllClosedReservations();

	List<TransferCard> fetchAllOpenedTransferCards();

	List<TransferCard> fetchAllClosedTransferCards();

	List<TransferCard> fetchOpenedTransferCardsForOffice(int officeId);

	List<TransferCard> fetchClosedTransferCardsForOffice(int officeId);

	TransferCard fetchTransferCardById(int id);

	CarHistory fetchCarHistoryById(int id);
	
	CarHistory createCarHistoryForTransferCard(TransferCard transferCard);
	
	CarHistory createCarHistoryForContract(Contract contract);

	List<CarHistory> fetchAllCarHistory();

	Contract fetchContractById(int id);

	List<Contract> fetchAllContracts();

	List<Contract> fetchAllOpenedContracts();

	List<Contract> fetchAllClosedContracts();	

	List<Contract> fetchClosedContractsForOffice(int officeId);

	Insurance fetchInsuranceById(int id);

	Accessories fetchAccessoriesById(int id);

	PriceAdditions fetchPriceAdditionsById(int id);

	List<Insurance> fetchAllInsurances();

	List<Accessories> fetchAllAccessories();

	List<PriceAdditions> fetchAllPriceAdditions();

	void saveReservation(Reservation reservation) throws ParseException;

	void updateReservation(Reservation reservation) throws ParseException;

	void updateEmployee(Employee employee);

	void saveCar(Car car);

	void updateCar(Car car);

	void saveTransferCard(TransferCard transferCard);

	void updateTransferCard(TransferCard transferCard);

	List<String> fetchVehicleGroup();

	List<String> reservationTypes();

	List<String> acceptanceList();

	void saveAccessories(Accessories accessories);

	void updateAccessories(Accessories accessories);

	void saveInsurance(Insurance insurance);

	void updateInsurance(Insurance insurance);

	void savePriceAdditions(PriceAdditions priceAdditions);

	void updatePriceAdditions(PriceAdditions priceAdditions);

	void saveContract(Contract contract);

	void updateContract(Contract contract);

	void saveCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void saveCarHistory(CarHistory carHistory);

	void createPdfForContract(int id);

	void createPdfForCarChange(int id);

	void createPdfForClosedContract(int id);

	void createPdfForReservation(int id);

	void createPdfForTransferCard(int id);

	void createPdfForClosedTransferCard(int id);

	Long countReservations();

	Long countReservationsForOffice(int id);

	Long countIdleCars();

	Long countIdleCarsForOffice(int id);

	Long countCarsInTransfer();

	Long countCarsInTransferForOffice(int id);

	Long countCarsInService();

	Long countCarsInServiceForOffice(int id);

	Long countCarsInRent();

	Long countCarsInRentForOffice(int id);
	
	Long countReturns();
	
	Long countReturnsForOffice(int id);
}
