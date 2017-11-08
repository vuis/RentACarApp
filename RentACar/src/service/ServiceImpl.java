package service;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.DaoHibernate;
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

@Service
public class ServiceImpl implements ServiceInterface, Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private DaoHibernate daoHibernate;

	@Override
	@Transactional
	public List<Customer> fetchAllCustomers() {
		return daoHibernate.fetchAllCustomers();
	}

	@Override
	@Transactional
	public Customer fetchCustomerById(int id) {
		return daoHibernate.fetchCustomerById(id);
	}

	@Override
	@Transactional
	public List<Employee> fetchAllEmployees() {
		return daoHibernate.fetchAllEmployees();
	}

	@Override
	@Transactional
	public Employee fetchEmployeeById(int id) {
		return daoHibernate.fetchEmployeeById(id);
	}

	@Override
	@Transactional
	public Employee fetchEmployeeByUserName(String username) {
		return daoHibernate.fetchEmployeeByUserName(username);
	}

	@Override
	@Transactional
	public List<Office> fetchAllOffices() {
		return daoHibernate.fetchAllOffices();
	}

	@Override
	@Transactional
	public Office fetchOfficeById(int id) {
		return daoHibernate.fetchOfficeById(id);
	}

	@Override
	@Transactional
	public List<Car> fetchAllCars() {
		return daoHibernate.fetchAllCars();
	}

	@Override
	@Transactional
	public List<Car> fetchCarsForOffice(int officeId) {
		return daoHibernate.fetchCarsForOffice(officeId);
	}

	@Override
	@Transactional
	public List<Car> fetchIdleCars() {
		return daoHibernate.fetchIdleCars();
	}

	@Override
	@Transactional
	public List<Car> fetchIdleCarsForOffice(int officeId) {
		return daoHibernate.fetchIdleCarsForOffice(officeId);
	}

	@Override
	@Transactional
	public Car fetchCarById(int id) {
		return daoHibernate.fetchCarById(id);
	}

	@Override
	@Transactional
	public List<Reservation> fetchAllReservations() {
		return daoHibernate.fetchAllReservations();
	}

	@Override
	@Transactional
	public Reservation fetchReservationById(int id) {
		return daoHibernate.fetchReservationById(id);
	}

	@Override
	@Transactional
	public List<Reservation> fetchAllOpenedReservations() {
		return daoHibernate.fetchAllOpenedReservations();
	}

	@Override
	@Transactional
	public List<Reservation> fetchOpenedReservationsForOffice(int officeId) {
		return daoHibernate.fetchOpenedReservationsForOffice(officeId);
	}

	@Override
	@Transactional
	public List<Reservation> fetchClosedReservationsForOffice(int officeId) {
		return daoHibernate.fetchClosedReservationsForOffice(officeId);
	}

	@Override
	@Transactional
	public List<Reservation> fetchAllClosedReservations() {
		return daoHibernate.fetchAllClosedReservations();
	}

	@Override
	@Transactional
	public List<TransferCard> fetchAllOpenedTransferCards() {
		return daoHibernate.fetchAllOpenedTransferCards();
	}

	@Override
	@Transactional
	public List<TransferCard> fetchAllClosedTransferCards() {
		return daoHibernate.fetchAllClosedTransferCards();
	}

	@Override
	@Transactional
	public List<TransferCard> fetchOpenedTransferCardsForOffice(int officeId) {
		return daoHibernate.fetchOpenedTransferCardsForOffice(officeId);
	}

	@Override
	@Transactional
	public List<TransferCard> fetchClosedTransferCardsForOffice(int officeId) {
		return daoHibernate.fetchClosedTransferCardsForOffice(officeId);
	}

	@Override
	@Transactional
	public TransferCard fetchTransferCardById(int id) {
		return daoHibernate.fetchTransferCardById(id);
	}

	@Override
	@Transactional
	public CarHistory fetchCarHistoryById(int id) {
		return daoHibernate.fetchCarHistoryById(id);
	}

	@Override
	public CarHistory createCarHistoryForTransferCard(TransferCard transferCard) {
		CarHistory carHistory = new CarHistory();
		carHistory.setCar(transferCard.getCar());
		carHistory.setCarKm(transferCard.getCar().getKm());
		carHistory.setCarFuel(transferCard.getCar().getFuel());
		carHistory.setDate(new Date());
		LocalTime localTime = LocalTime.now();
		Time time = Time.valueOf(localTime);
		carHistory.setTime(time);
		if (transferCard.getStatus().equals("Zatvorena")) {
			carHistory.setOffice(transferCard.getOfficeIn());
		} else {
			carHistory.setOffice(transferCard.getOfficeOut());
		}
		carHistory.setVehicleStatus(transferCard.getCar().getStatus());
		carHistory.setMovedBy("Po transfer karti broj " + transferCard.getId());
		return carHistory;
	}

	@Override
	public CarHistory createCarHistoryForContract(Contract contract) {
		CarHistory carHistory = new CarHistory();
		if (contract.getCar2() == null) {
			carHistory.setCar(contract.getCar());
			carHistory.setCarKm(contract.getCar().getKm());
			carHistory.setCarFuel(contract.getCar().getFuel());
			carHistory.setVehicleStatus(contract.getCar().getStatus());

		} else {
			carHistory.setCar(contract.getCar2());
			carHistory.setCarKm(contract.getCar2().getKm());
			carHistory.setCarFuel(contract.getCar2().getFuel());
			carHistory.setVehicleStatus(contract.getCar2().getStatus());
		}

		LocalTime localTime = LocalTime.now();
		Time time = Time.valueOf(localTime);
		carHistory.setTime(time);
		carHistory.setDate(new Date());
		if (contract.getStatus().equals("Zatvoren")) {
			carHistory.setOffice(contract.getDropOffOffice());
		} else {
			carHistory.setOffice(contract.getPickUpOffice());
		}

		carHistory.setMovedBy("Po ugovoru broj " + contract.getId());
		return carHistory;
	}

	@Override
	@Transactional
	public List<CarHistory> fetchAllCarHistory() {
		return daoHibernate.fetchAllCarHistory();
	}

	@Override
	@Transactional
	public Contract fetchContractById(int id) {
		return daoHibernate.fetchContractById(id);

	}

	@Override
	@Transactional
	public List<Contract> fetchAllContracts() {
		return daoHibernate.fetchAllContracts();
	}

	@Override
	@Transactional
	public List<Contract> fetchAllOpenedContracts() {
		return daoHibernate.fetchAllOpenedContracts();
	}

	@Override
	@Transactional
	public List<Contract> fetchAllClosedContracts() {
		return daoHibernate.fetchAllClosedContracts();
	}

	@Override
	@Transactional
	public List<Contract> fetchClosedContractsForOffice(int officeId) {
		return daoHibernate.fetchClosedContractsForOffice(officeId);
	}

	@Override
	@Transactional
	public Insurance fetchInsuranceById(int id) {
		return daoHibernate.fetchInsuranceById(id);
	}

	@Override
	@Transactional
	public Accessories fetchAccessoriesById(int id) {
		return daoHibernate.fetchAccessoriesById(id);
	}

	@Override
	@Transactional
	public PriceAdditions fetchPriceAdditionsById(int id) {
		return daoHibernate.fetchPriceAdditionsById(id);
	}

	@Override
	@Transactional
	public List<Insurance> fetchAllInsurances() {
		return daoHibernate.fetchAllInsurances();
	}

	@Override
	@Transactional
	public List<Accessories> fetchAllAccessories() {
		return daoHibernate.fetchAllAccessories();
	}

	@Override
	@Transactional
	public List<PriceAdditions> fetchAllPriceAdditions() {
		return daoHibernate.fetchAllPriceAdditions();
	}

	@Override
	@Transactional
	public void saveReservation(Reservation reservation) throws ParseException {
		daoHibernate.saveReservation(reservation);

	}

	@Override
	@Transactional
	public void updateReservation(Reservation reservation) throws ParseException {
		daoHibernate.updateReservation(reservation);

	}

	@Override
	@Transactional
	public void updateEmployee(Employee employee) {
		daoHibernate.updateEmployee(employee);

	}

	@Override
	@Transactional
	public void saveCar(Car car) {
		daoHibernate.saveCar(car);

	}

	@Override
	@Transactional
	public void updateCar(Car car) {
		daoHibernate.updateCar(car);
	}

	@Override
	@Transactional
	public void saveTransferCard(TransferCard transferCard) {
		daoHibernate.saveTransferCard(transferCard);
	}

	@Override
	@Transactional
	public void updateTransferCard(TransferCard transferCard) {
		daoHibernate.updateTransferCard(transferCard);
	}

	List<String> vehicleGroupList;

	@Override
	public List<String> fetchVehicleGroup() {
		vehicleGroupList = new ArrayList<>();
		vehicleGroupList.add("B/ECMR");
		vehicleGroupList.add("C/EDMR");
		vehicleGroupList.add("D/CDMR");
		vehicleGroupList.add("Da/CDAR");
		vehicleGroupList.add("D/IDMR");
		vehicleGroupList.add("Ea/SDAR");
		vehicleGroupList.add("K/SWMR");
		vehicleGroupList.add("Ka/SWAR");
		vehicleGroupList.add("F/FDMR");
		vehicleGroupList.add("P/MVMR");
		vehicleGroupList.add("J/FVMR");
		vehicleGroupList.add("X/XDAR");
		return vehicleGroupList;
	}

	List<String> reservationTypes;

	@Override
	public List<String> reservationTypes() {
		reservationTypes = new ArrayList<>();
		reservationTypes.add("Booking agencija");
		reservationTypes.add("Telefon");
		reservationTypes.add("Walk in");
		return reservationTypes;
	}

	List<String> acceptanceList;

	@Override
	public List<String> acceptanceList() {
		acceptanceList = new ArrayList<>();
		acceptanceList.add("Stranka je prihvatila");
		acceptanceList.add("Stranka nije prihvatila");
		acceptanceList.add("Ukljuèeno u cijenu");
		return acceptanceList;
	}

	@Override
	@Transactional
	public void saveAccessories(Accessories accessories) {
		daoHibernate.saveAccessories(accessories);

	}

	@Override
	@Transactional
	public void updateAccessories(Accessories accessories) {
		daoHibernate.updateAccessories(accessories);

	}

	@Override
	@Transactional
	public void saveInsurance(Insurance insurance) {
		daoHibernate.saveInsurance(insurance);

	}

	@Override
	@Transactional
	public void updateInsurance(Insurance insurance) {
		daoHibernate.updateInsurance(insurance);

	}

	@Override
	@Transactional
	public void savePriceAdditions(PriceAdditions priceAdditions) {
		daoHibernate.savePriceAdditions(priceAdditions);

	}

	@Override
	@Transactional
	public void updatePriceAdditions(PriceAdditions priceAdditions) {
		daoHibernate.updatePriceAdditions(priceAdditions);

	}

	@Override
	@Transactional
	public void saveContract(Contract contract) {
		daoHibernate.saveContract(contract);
	}

	@Override
	@Transactional
	public void updateContract(Contract contract) {
		daoHibernate.updateContract(contract);

	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		daoHibernate.saveCustomer(customer);
	}

	@Override
	@Transactional
	public void updateCustomer(Customer customer) {
		daoHibernate.updateCustomer(customer);
	}

	@Override
	@Transactional
	public void saveCarHistory(CarHistory carHistory) {
		daoHibernate.saveCarHistory(carHistory);

	}

	@Override
	@Transactional
	public void createPdfForContract(int id) {
		daoHibernate.createPdfForContract(id);

	}

	@Override
	@Transactional
	public void createPdfForCarChange(int id) {
		daoHibernate.createPdfForCarChange(id);

	}

	@Override
	@Transactional
	public void createPdfForClosedContract(int id) {
		daoHibernate.createPdfForClosedContract(id);

	}

	@Override
	@Transactional
	public void createPdfForReservation(int id) {
		daoHibernate.createPdfForReservation(id);

	}

	@Override
	@Transactional
	public void createPdfForTransferCard(int id) {
		daoHibernate.createPdfForTransferCard(id);

	}

	@Override
	@Transactional
	public void createPdfForClosedTransferCard(int id) {
		daoHibernate.createPdfForClosedTransferCard(id);

	}

	@Override
	@Transactional
	public Long countReservationsForOffice(int id) {
		return daoHibernate.countReservationsForOffice(id);
	}

	@Override
	@Transactional
	public Long countReservations() {
		return daoHibernate.countReservations();
	}

	@Override
	@Transactional
	public Long countIdleCars() {
		return daoHibernate.countIdleCars();
	}

	@Override
	@Transactional
	public Long countIdleCarsForOffice(int id) {
		return daoHibernate.countIdleCarsForOffice(id);
	}

	@Override
	@Transactional
	public Long countCarsInTransfer() {
		return daoHibernate.countCarsInTransfer();
	}

	@Override
	@Transactional
	public Long countCarsInTransferForOffice(int id) {
		return daoHibernate.countCarsInTransferForOffice(id);
	}

	@Override
	@Transactional
	public Long countCarsInService() {
		return daoHibernate.countCarsInService();
	}

	@Override
	@Transactional
	public Long countCarsInServiceForOffice(int id) {
		return daoHibernate.countCarsInServiceForOffice(id);
	}

	@Override
	@Transactional
	public Long countCarsInRent() {
		return daoHibernate.countCarsInRent();
	}

	@Override
	@Transactional
	public Long countCarsInRentForOffice(int id) {
		return daoHibernate.countCarsInRentForOffice(id);
	}

	@Override
	@Transactional
	public Long countReturns() {
		return daoHibernate.countReturns();
	}

	@Override
	@Transactional
	public Long countReturnsForOffice(int id) {
		return daoHibernate.countReturnsForOffice(id);
	}

}
