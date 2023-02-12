package lk.ijse.phone.service.util;

import lk.ijse.phone.dto.*;
import lk.ijse.phone.entity.*;

public class Convertor {

    public CustomerDTO fromCustomer(Customer customer) {
        return new CustomerDTO(customer.getCustomerId(), customer.getCustomerName(), customer.getAddress(), customer.getPhoneNumber());
    }

    public Customer toCustomer(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getCustomerId(), customerDTO.getCustomerName(), customerDTO.getAddress(), customerDTO.getPhoneNumber());
    }

    public EmployeeDTO fromEmployee(Employee employee) {
        return new EmployeeDTO(employee.getEmployeeId(), employee.getEmployeeName(), employee.getAddress(), employee.getSalary());
    }

    public Employee toEmployee(EmployeeDTO employeeDTO) {
        return new Employee(employeeDTO.getEmployeeId(), employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getSalary());
    }

    public EmployeeAttendanceDTO fromEmployeeAttendance(EmployeeAttendance employeeAttendance) {
        return new EmployeeAttendanceDTO(employeeAttendance.getEmployeeId(), employeeAttendance.getEmployeeName(), employeeAttendance.getDate(),
                employeeAttendance.getInTime(), employeeAttendance.getOutTime(), employeeAttendance.getStatus());
    }

    public EmployeeAttendance toEmployeeAttendance(EmployeeAttendanceDTO employeeAttendanceDTO) {
        return new EmployeeAttendance(employeeAttendanceDTO.getEmployeeId(), employeeAttendanceDTO.getEmployeename(), employeeAttendanceDTO.getDate(),
                employeeAttendanceDTO.getInTime(), employeeAttendanceDTO.getOutTime(), employeeAttendanceDTO.getStatus());
    }

    public ItemDTO fromItem(Item item) {
        return new ItemDTO(item.getItemCode(), item.getItemName(), item.getQtyOnHand(), item.getUnitPrice());
    }

    public Item toItem(ItemDTO itemDTO) {
        return new Item(itemDTO.getItemCode(), itemDTO.getItemName(), itemDTO.getQtyOnHand(), itemDTO.getUnitPrice());
    }

    public OrderDetailsDTO fromOrderDetails(OrderDetails orderDetails) {
        return new OrderDetailsDTO(orderDetails.getOrderId(), orderDetails.getItemCode(), orderDetails.getQty(), orderDetails.getUnitPrice(), orderDetails.getPrice());
    }

    public OrderDetails toOrderDetails(OrderDetailsDTO orderDetailsDTO) {
        return new OrderDetails(orderDetailsDTO.getOrderId(), orderDetailsDTO.getItemCode(), orderDetailsDTO.getQty(), orderDetailsDTO.getUnitPrice(), orderDetailsDTO.getPrice());
    }

    public OrderDTO fromOrder(Orders orders) {
        return new OrderDTO(orders.getOrderId(), orders.getDate(), orders.getCustomerId(), orders.getSalesmen(), orders.getStatus());
    }

    public Orders toOrders(OrderDTO orderDTO) {
        return new Orders(orderDTO.getOrderId(), orderDTO.getDate(), orderDTO.getCustomerId(), orderDTO.getSalesmen(), orderDTO.getStatus());
    }

    public PaymentDTO fromPayment(Payment payment) {
        return new PaymentDTO(payment.getPaymentId(), payment.getOrderId(), payment.getTotal(), payment.getTotalBalance());
    }

    public Payment toPayment(PaymentDTO paymentDTO) {
        return new Payment(paymentDTO.getPaymentId(), paymentDTO.getOrderid(), paymentDTO.getTotal(), paymentDTO.getTotalBalance());
    }

    public PaymentDetailsDTO fromPaymentDetails(PaymentDetails paymentDetails) {
        return new PaymentDetailsDTO(paymentDetails.getOrderId(), paymentDetails.getCustomerId(), paymentDetails.getBalance(), paymentDetails.getDate(), paymentDetails.getTime());
    }

    public PaymentDetails toPaymentDetails(PaymentDetailsDTO paymentDetailsDTO) {
        return new PaymentDetails(paymentDetailsDTO.getOrderId(), paymentDetailsDTO.getCustomerId(), paymentDetailsDTO.getBalance(), paymentDetailsDTO.getDate(), paymentDetailsDTO.getTime());
    }

    public RackDetailsDTO fromRackDetails(RackDetails rackDetails) {
        return new RackDetailsDTO(rackDetails.getRackNumber(), rackDetails.getItemCode(), rackDetails.getItemName());
    }

    public RackDetails toRackDetails(RackDetailsDTO rackDetailsDTO) {
        return new RackDetails(rackDetailsDTO.getItemCode(), rackDetailsDTO.getItemCode(), rackDetailsDTO.getItemName());
    }

    public StockArrievdDTO fromStockArrived(StockArrived stockArrived) {
        return new StockArrievdDTO(stockArrived.getDateOfArrived(), stockArrived.getItemCode(), stockArrived.getItemName(), stockArrived.getQty(), stockArrived.getSupplierId(), stockArrived.getSupplierName());
    }

    public StockArrived toStockArrived(StockArrievdDTO stockArrievdDTO){
        return new StockArrived(stockArrievdDTO.getDateOfArrrievd(), stockArrievdDTO.getItemCode(), stockArrievdDTO.getItemName(), stockArrievdDTO.getQty(),
                stockArrievdDTO.getSupplierId(), stockArrievdDTO.getSupplierName());
    }

    public SupplierDTO fromSupplier(Supplier supplier){
        return new SupplierDTO(supplier.getSupplierId(), supplier.getName(), supplier.getBrand(), supplier.getAddress(), supplier.getContact());
    }

    public Supplier toSupplier(SupplierDTO supplierDTO){
        return new Supplier(supplierDTO.getSid(), supplierDTO.getName(), supplierDTO.getBrand(), supplierDTO.getAddress(), supplierDTO.getContactNo());
    }

    public SystemUsersDTO fromSystemUser(SystemUsers systemUsers){
        return new SystemUsersDTO(systemUsers.getId(), systemUsers.getName(), systemUsers.getEmail(), systemUsers.getUserRank(), systemUsers.getPassword());
    }

    public SystemUsers toSystemUsers(SystemUsersDTO systemUsersDTO){
        return new SystemUsers(systemUsersDTO.getId(), systemUsersDTO.getName(), systemUsersDTO.getEmail(), systemUsersDTO.getRank(), systemUsersDTO.getPassword());
    }
}
