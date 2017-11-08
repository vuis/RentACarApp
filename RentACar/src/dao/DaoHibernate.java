package dao;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class DaoHibernate implements Dao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> fetchAllCustomers() {
		Query<Customer> query = getCurrentSession().createQuery("from Customer order by id asc");
		return query.getResultList();
	}

	@Override
	public Customer fetchCustomerById(int id) {
		return (Customer) getCurrentSession().get(Customer.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> fetchAllEmployees() {
		Query<Employee> query = getCurrentSession()
				.createQuery("from Employee where username not like :username order by id asc");
		query.setParameter("username", "rezervacije");
		return query.getResultList();
	}

	@Override
	public Employee fetchEmployeeById(int id) {
		return (Employee) getCurrentSession().get(Employee.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Employee fetchEmployeeByUserName(String username) {
		Query<Employee> query = getCurrentSession()
				.createQuery("from Employee where username=:username order by id asc");
		query.setParameter("username", username);
		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Office> fetchAllOffices() {
		return getCurrentSession().createQuery("from Office order by id asc").getResultList();
	}

	@Override
	public Office fetchOfficeById(int id) {
		return getCurrentSession().get(Office.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> fetchAllCars() {
		return getCurrentSession().createQuery("from Car order by id asc").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> fetchCarsForOffice(int officeId) {
		Query<Car> query = getCurrentSession().createQuery("from Car where officeId=:officeId order by id asc");
		query.setParameter("officeId", officeId);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> fetchIdleCars() {
		Query<Car> query = getCurrentSession().createQuery("from Car where status=:status order by id asc");
		query.setParameter("status", "Za najam");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> fetchIdleCarsForOffice(int officeId) {
		Query<Car> query = getCurrentSession()
				.createQuery("from Car where officeId=:officeId and status=:status order by id asc");
		query.setParameter("officeId", officeId);
		query.setParameter("status", "Za najam");
		return query.getResultList();

	}

	@Override
	public Car fetchCarById(int id) {
		return getCurrentSession().get(Car.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> fetchAllReservations() {
		return getCurrentSession().createQuery("from Reservation order by id asc").getResultList();
	}

	@Override
	public Reservation fetchReservationById(int id) {
		return getCurrentSession().get(Reservation.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> fetchAllOpenedReservations() {
		Query<Reservation> query = getCurrentSession()
				.createQuery("from Reservation where status=:status order by id asc");
		query.setParameter("status", "Otvorena");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> fetchAllClosedReservations() {
		Query<Reservation> query = getCurrentSession()
				.createQuery("from Reservation where status=:status order by id asc");
		query.setParameter("status", "Zatvorena");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> fetchOpenedReservationsForOffice(int officeId) {
		Query<Reservation> query = getCurrentSession()
				.createQuery("from Reservation where pickUpOfficeId=:officeId and status=:status order by id asc");
		query.setParameter("officeId", officeId);
		query.setParameter("status", "Otvorena");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> fetchClosedReservationsForOffice(int officeId) {
		Query<Reservation> query = getCurrentSession()
				.createQuery("from Reservation where pickUpOfficeId=:officeId and status=:status order by id asc");
		query.setParameter("officeId", officeId);
		query.setParameter("status", "Zatvorena");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransferCard> fetchAllOpenedTransferCards() {
		Query<TransferCard> query = getCurrentSession()
				.createQuery("from TransferCard where status=:status order by id asc");
		query.setParameter("status", "Otvorena");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransferCard> fetchAllClosedTransferCards() {
		Query<TransferCard> query = getCurrentSession()
				.createQuery("from TransferCard where status=:status order by id asc");
		query.setParameter("status", "Zatvorena");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransferCard> fetchOpenedTransferCardsForOffice(int officeId) {
		Query<TransferCard> query = getCurrentSession().createQuery(
				"from TransferCard where officeOutId=:officeId and status=:status or officeInId=:officeId and status=:status order by id asc");
		query.setParameter("officeId", officeId);
		query.setParameter("status", "Otvorena");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransferCard> fetchClosedTransferCardsForOffice(int officeId) {
		Query<TransferCard> query = getCurrentSession().createQuery(
				"from TransferCard where officeOutId=:officeId and status=:status or officeInId=:officeId and status=:status order by id asc");
		query.setParameter("officeId", officeId);
		query.setParameter("status", "Zatvorena");
		return query.getResultList();
	}

	@Override
	public TransferCard fetchTransferCardById(int id) {
		return getCurrentSession().get(TransferCard.class, id);
	}

	@Override
	public CarHistory fetchCarHistoryById(int id) {
		return getCurrentSession().get(CarHistory.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CarHistory> fetchAllCarHistory() {
		return getCurrentSession().createQuery("from CarHistory order by id asc").getResultList();
	}

	@Override
	public Contract fetchContractById(int id) {
		return getCurrentSession().get(Contract.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> fetchAllContracts() {
		return getCurrentSession().createQuery("from Contract order by id asc").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> fetchAllOpenedContracts() {
		Query<Contract> query = getCurrentSession().createQuery("from Contract where status=:status order by id asc");
		query.setParameter("status", "Otvoren");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> fetchAllClosedContracts() {
		Query<Contract> query = getCurrentSession().createQuery("from Contract where status=:status order by id asc");
		query.setParameter("status", "Zatvoren");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> fetchClosedContractsForOffice(int officeId) {
		Query<Contract> query = getCurrentSession().createQuery(
				"from Contract where pickUpOfficeId=:officeId and status=:status or dropOffOfficeId=:officeId and status=:status order by id asc");
		query.setParameter("officeId", officeId);
		query.setParameter("status", "Zatvoren");
		return query.getResultList();
	}

	@Override
	public Insurance fetchInsuranceById(int id) {
		return getCurrentSession().get(Insurance.class, id);
	}

	@Override
	public Accessories fetchAccessoriesById(int id) {
		return getCurrentSession().get(Accessories.class, id);
	}

	@Override
	public PriceAdditions fetchPriceAdditionsById(int id) {
		return getCurrentSession().get(PriceAdditions.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Insurance> fetchAllInsurances() {
		return getCurrentSession().createQuery("from Insurance").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Accessories> fetchAllAccessories() {
		return getCurrentSession().createQuery("from Accessories").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PriceAdditions> fetchAllPriceAdditions() {
		return getCurrentSession().createQuery("from PriceAdditions").getResultList();
	}

	@Override
	public void saveReservation(Reservation reservation) throws ParseException {
		getCurrentSession().save(reservation);

	}

	@Override
	public void updateReservation(Reservation reservation) throws ParseException {
		getCurrentSession().update(reservation);

	}

	@Override
	public void updateEmployee(Employee employee) {
		getCurrentSession().update(employee);

	}

	@Override
	public void saveCar(Car car) {
		getCurrentSession().save(car);
	}

	@Override
	public void updateCar(Car car) {
		getCurrentSession().update(car);

	}

	@Override
	public void saveTransferCard(TransferCard transferCard) {
		getCurrentSession().save(transferCard);
	}

	@Override
	public void updateTransferCard(TransferCard transferCard) {
		getCurrentSession().update(transferCard);
	}

	@Override
	public void saveAccessories(Accessories accessories) {
		getCurrentSession().save(accessories);

	}

	@Override
	public void updateAccessories(Accessories accessories) {
		getCurrentSession().update(accessories);

	}

	@Override
	public void saveInsurance(Insurance insurance) {
		getCurrentSession().save(insurance);

	}

	@Override
	public void updateInsurance(Insurance insurance) {
		getCurrentSession().update(insurance);

	}

	@Override
	public void savePriceAdditions(PriceAdditions priceAdditions) {
		getCurrentSession().save(priceAdditions);

	}

	@Override
	public void updatePriceAdditions(PriceAdditions priceAdditions) {
		getCurrentSession().update(priceAdditions);

	}

	@Override
	public void saveContract(Contract contract) {
		getCurrentSession().save(contract);

	}

	@Override
	public void updateContract(Contract contract) {
		getCurrentSession().update(contract);
	}

	@Override
	public void saveCustomer(Customer customer) {
		getCurrentSession().save(customer);

	}

	@Override
	public void updateCustomer(Customer customer) {
		getCurrentSession().update(customer);

	}

	@Override
	public void saveCarHistory(CarHistory carHistory) {
		getCurrentSession().save(carHistory);

	}

	@Override
	public void createPdfForContract(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		Connection conn = ((SessionImpl) getCurrentSession()).connection();
		InputStream in = getClass().getResourceAsStream("../contractJasper/Contract.jrxml");
		JasperReport report = null;
		JasperPrint jasperPrint = null;
		File folder = new File("C:/Contracts");
		if (!folder.exists()) {
			folder.mkdir();
		}
		try {
			report = JasperCompileManager.compileReport(in);
			jasperPrint = JasperFillManager.fillReport(report, parameters, conn);
			JasperExportManager.exportReportToPdfFile(jasperPrint, folder + "/Contract" + id + ".pdf");

		} catch (JRException e) {
			e.printStackTrace();
		}
		getCurrentSession().clear();
	}

	@Override
	public void createPdfForCarChange(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		Connection conn = ((SessionImpl) getCurrentSession()).connection();
		InputStream in = getClass().getResourceAsStream("../contractJasper/CarChange.jrxml");
		JasperReport report = null;
		JasperPrint jasperPrint = null;
		File folder = new File("C:/Contracts");
		if (!folder.exists()) {
			folder.mkdir();
		}
		try {
			report = JasperCompileManager.compileReport(in);
			jasperPrint = JasperFillManager.fillReport(report, parameters, conn);
			JasperExportManager.exportReportToPdfFile(jasperPrint, folder + "/Contract" + id + "-carChange.pdf");

		} catch (JRException e) {
			e.printStackTrace();
		}
		getCurrentSession().clear();
	}

	@Override
	public void createPdfForClosedContract(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		Connection conn = ((SessionImpl) getCurrentSession()).connection();
		InputStream in = getClass().getResourceAsStream("../contractJasper/CloseContract.jrxml");
		JasperReport report = null;
		JasperPrint jasperPrint = null;
		File folder = new File("C:/Contracts");
		if (!folder.exists()) {
			folder.mkdir();
		}
		try {
			report = JasperCompileManager.compileReport(in);
			jasperPrint = JasperFillManager.fillReport(report, parameters, conn);
			JasperExportManager.exportReportToPdfFile(jasperPrint, folder + "/ClosedContract" + id + ".pdf");

		} catch (JRException e) {
			e.printStackTrace();
		}
		getCurrentSession().clear();
	}

	@Override
	public void createPdfForReservation(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		Connection conn = ((SessionImpl) getCurrentSession()).connection();
		InputStream in = getClass().getResourceAsStream("../reservationJasper/Reservation.jrxml");
		JasperReport report = null;
		JasperPrint jasperPrint = null;
		File folder = new File("C:/Reservations");
		if (!folder.exists()) {
			folder.mkdir();
		}
		try {
			report = JasperCompileManager.compileReport(in);
			jasperPrint = JasperFillManager.fillReport(report, parameters, conn);
			JasperExportManager.exportReportToPdfFile(jasperPrint, folder + "/Reservation" + id + ".pdf");

		} catch (JRException e) {
			e.printStackTrace();
		}
		getCurrentSession().clear();
	}

	@Override
	public void createPdfForTransferCard(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		Connection conn = ((SessionImpl) getCurrentSession()).connection();
		InputStream in = getClass().getResourceAsStream("../transferCardJasper/TransferCard.jrxml");
		JasperReport report = null;
		JasperPrint jasperPrint = null;
		File folder = new File("C:/Transfercards");
		if (!folder.exists()) {
			folder.mkdir();
		}
		try {
			report = JasperCompileManager.compileReport(in);
			jasperPrint = JasperFillManager.fillReport(report, parameters, conn);
			JasperExportManager.exportReportToPdfFile(jasperPrint, folder + "/TransferCard" + id + ".pdf");

		} catch (JRException e) {
			e.printStackTrace();
		}
		getCurrentSession().clear();
	}

	@Override
	public void createPdfForClosedTransferCard(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		Connection conn = ((SessionImpl) getCurrentSession()).connection();
		InputStream in = getClass().getResourceAsStream("../transferCardJasper/ClosedTransferCard.jrxml");
		JasperReport report = null;
		JasperPrint jasperPrint = null;
		File folder = new File("C:/Transfercards");
		if (!folder.exists()) {
			folder.mkdir();
		}
		try {
			report = JasperCompileManager.compileReport(in);
			jasperPrint = JasperFillManager.fillReport(report, parameters, conn);
			JasperExportManager.exportReportToPdfFile(jasperPrint, folder + "/ClosedTransferCard" + id + ".pdf");

		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long countReservations() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String date = dt.format(new Date());
		Query<Long> query = getCurrentSession()
				.createQuery("Select count(*) from Reservation where status=:status and pickupdate=:date");
		query.setParameter("status", "Otvorena");
		query.setParameter("date", date);
		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long countReservationsForOffice(int id) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String date = dt.format(new Date());
		Query<Long> query = getCurrentSession().createQuery(
				"Select count(*) from Reservation where status=:status and pickupdate=:date and pickupofficeid=:officeId");
		query.setParameter("status", "Otvorena");
		query.setParameter("date", date);
		query.setParameter("officeId", id);
		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long countIdleCars() {
		Query<Long> query = getCurrentSession().createQuery("Select count(*) from Car where status=:status");
		query.setParameter("status", "Za najam");
		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long countIdleCarsForOffice(int id) {
		Query<Long> query = getCurrentSession()
				.createQuery("Select count(*) from Car where status=:status and officeId=:officeId");
		query.setParameter("status", "Za najam");
		query.setParameter("officeId", id);
		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long countCarsInTransfer() {
		Query<Long> query = getCurrentSession().createQuery("Select count(*) from Car where status=:status");
		query.setParameter("status", "U transferu");
		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long countCarsInTransferForOffice(int id) {
		Query<Long> query = getCurrentSession()
				.createQuery("Select count(*) from Car where status=:status and officeId=:officeId");
		query.setParameter("status", "U transferu");
		query.setParameter("officeId", id);
		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long countCarsInService() {
		Query<Long> query = getCurrentSession().createQuery("Select count(*) from Car where status=:status");
		query.setParameter("status", "U servisu");
		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long countCarsInServiceForOffice(int id) {
		Query<Long> query = getCurrentSession()
				.createQuery("Select count(*) from Car where status=:status and officeId=:officeId");
		query.setParameter("status", "U servisu");
		query.setParameter("officeId", id);
		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long countCarsInRent() {
		Query<Long> query = getCurrentSession().createQuery("Select count(*) from Car where status=:status");
		query.setParameter("status", "U najmu");
		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long countCarsInRentForOffice(int id) {
		Query<Long> query = getCurrentSession()
				.createQuery("Select count(*) from Car where status=:status and officeId=:officeId");
		query.setParameter("status", "U najmu");
		query.setParameter("officeId", id);
		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long countReturns() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String date = dt.format(new Date());
		Query<Long> query = getCurrentSession()
				.createQuery("Select count(*) from Contract where status=:status and dropOffdate=:date");
		query.setParameter("status", "Otvoren");
		query.setParameter("date", date);
		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long countReturnsForOffice(int id) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String date = dt.format(new Date());
		Query<Long> query = getCurrentSession().createQuery(
				"Select count(*) from Contract where status=:status and dropOffdate=:date and dropoffofficeid=:officeId");
		query.setParameter("status", "Otvorena");
		query.setParameter("date", date);
		query.setParameter("officeId", id);
		return query.getSingleResult();
	}

}
