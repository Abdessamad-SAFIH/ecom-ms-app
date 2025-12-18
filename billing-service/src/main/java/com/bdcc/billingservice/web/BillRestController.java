package com.bdcc.billingservice.web;

import com.bdcc.billingservice.entities.Bill;
import com.bdcc.billingservice.feign.CustomerServiceRestClient;
import com.bdcc.billingservice.feign.InventoryServiceRestClient;
import com.bdcc.billingservice.model.Customer;
import com.bdcc.billingservice.model.Product;
import com.bdcc.billingservice.repository.BillRepository;
import com.bdcc.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerServiceRestClient customerServiceRestClient;
    @Autowired
    private InventoryServiceRestClient inventoryServiceRestClient;

    @GetMapping("/bills/{id}")
    public Bill getBillById(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerServiceRestClient.findCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(pi -> {
            pi.setProduct(inventoryServiceRestClient.getProduct(pi.getProductId()));
        });
        return bill;
    }

}
