package lk.ijse.gdse.service.util;


import lk.ijse.gdse.dto.CustomerDTO;
import lk.ijse.gdse.dto.EmployeeDTO;
import lk.ijse.gdse.dto.ItemDTO;
import lk.ijse.gdse.dto.OrderDetail;
import lk.ijse.gdse.entity.Customer;
import lk.ijse.gdse.entity.Employee;
import lk.ijse.gdse.entity.Item;
import lk.ijse.gdse.entity.Order;


public class Convertor {

    public CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress(),customer.getTelephone());
    }

    public Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getTelephone());
    }

    public EmployeeDTO fromEmployee(Employee employee){
        return new EmployeeDTO(employee.getEid(),employee.getFirst_name(),employee.getLast_name(),employee.getTelephone(),employee.getDob(),employee.getRole());

    }

    public Employee toEmployee(EmployeeDTO employeeDTO){
        return new Employee(employeeDTO.getEid(),employeeDTO.getFirst_name(),employeeDTO.getLast_name(),employeeDTO.getTelephone(),employeeDTO.getDob(),employeeDTO.getRole());

    }

    public ItemDTO fromItem(Item item){
        return new ItemDTO(item.getCode(),item.getName(),item.getBrand(), item.getCatogory(), item.getPurchase_price(), item.getSelling_price(), item.getProfit(), item.getModel_number(), item.getQty());
    }

    public Item toItem(ItemDTO itemDTO){
        return new Item(itemDTO.getCode(), itemDTO.getName(), itemDTO.getBrand(), itemDTO.getCatogory(), itemDTO.getPurchase_price(), itemDTO.getSelling_price(), itemDTO.getProfit(), itemDTO.getModel_number(), itemDTO.getQty());
    }

    public OrderDetail fromOrder(lk.ijse.gdse.entity.OrderDetail orderDetail){
        return new OrderDetail(orderDetail.getDid(),orderDetail.getItem_code(),orderDetail.getQty(), orderDetail.getUnitPrice());
    }
    public lk.ijse.gdse.entity.OrderDetail toOrder(OrderDetail orderDetail){
        return new lk.ijse.gdse.entity.OrderDetail(orderDetail.getDid(), orderDetail.getItem_code(), orderDetail.getQty(), orderDetail.getUnitPrice());

    }
}
