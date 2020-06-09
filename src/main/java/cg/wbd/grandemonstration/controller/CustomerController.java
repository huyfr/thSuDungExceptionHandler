package cg.wbd.grandemonstration.controller;

import cg.wbd.grandemonstration.model.CustomersEntity;
import cg.wbd.grandemonstration.model.ProvincesEntity;
import cg.wbd.grandemonstration.service.CustomerService;
import cg.wbd.grandemonstration.service.ProvinceService;
import cg.wbd.grandemonstration.service.exception.DuplicateEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<ProvincesEntity> allProvinces() {
        return provinceService.findAll();
    }

    @GetMapping
    public ModelAndView showList(Optional<String> s, Pageable pageInfo) {
        ModelAndView modelAndView = new ModelAndView("customers/list");
        Page<CustomersEntity> customers = s.isPresent() ? search(s, pageInfo) : getPage(pageInfo);
        modelAndView.addObject("keyword", s.orElse(null));
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("{id}")
    public ModelAndView showInformation(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("customers/info");
        CustomersEntity customer = customerService.findOne(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

/*    @PostMapping
    public String updateCustomer(Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }*/

    @GetMapping("/create_customer")
    public ModelAndView loadCreateForm() {
        ModelAndView createForm;
        try {
            createForm = new ModelAndView("customers/create");
            createForm.addObject("customer", new CustomersEntity());
        } catch (Exception ex) {
            createForm = new ModelAndView("customers/inputs-not-acceptable");
        }
        return createForm;
    }

/*    @PostMapping("/create_customer")
    public ModelAndView updateCustomer(CustomersEntity customer) {
        try {
            customerService.save(customer);
            return new ModelAndView("redirect:/customers");
        } catch (DuplicateEmailException ex) {
            return new ModelAndView("customers/inputs-not-acceptable");
        }
    }*/

    @PostMapping("/create_customer")
    public ModelAndView updateCustomer(CustomersEntity customer) throws DuplicateEmailException {
            customerService.save(customer);
            return new ModelAndView("redirect:/customers");
    }

    private Page<CustomersEntity> getPage(Pageable pageInfo) {
        return customerService.findAll(pageInfo);
    }

    private Page<CustomersEntity> search(Optional<String> s, Pageable pageInfo) {
        return customerService.search(s.get(), pageInfo);
    }

    @ExceptionHandler (DuplicateEmailException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("customers/inputs-not-acceptable");
    }
}
