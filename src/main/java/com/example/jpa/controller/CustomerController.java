package com.example.jpa.controller;

import com.example.jpa.model.Customer;
import com.example.jpa.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;



@RestController

public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }
    @PostMapping("/create-customer")
    private ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("customer",new Customer());
        modelAndView.addObject("messages","Thêm mới thành công");
        return modelAndView;
    }

    @GetMapping("/customer")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("/list");
        List<Customer> customerList = customerService.findAll();
        modelAndView.addObject("list",customerList);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if (customer != null) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("customer", customer);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }

    }
    @PostMapping("/edit-customer")
    public  ModelAndView updateCusmoter(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView =new ModelAndView("/edit");
        modelAndView.addObject("customer",customer);
        modelAndView.addObject("message","Sửa thành công");
        return modelAndView;
    }

     @GetMapping("/{id}/delete")
    public ModelAndView showDelete(@PathVariable Long id){
        Customer customer = customerService.findById(id);
//        if(customer != null){
//            ModelAndView modelAndView= new ModelAndView("/delete");
//            modelAndView.addObject("customer",customer);
//            return modelAndView;
//        }else {
//            ModelAndView modelAndView = new ModelAndView("/erorr.404");
//            return modelAndView;
//        }

         customerService.remove(customer.getId());
         return new ModelAndView("redirect:/customer");
     }

//     @PostMapping("/delete-customer")
//    public ModelAndView delete(@ModelAttribute("customer") Customer customer){
//        customerService.remove(customer.getId());
//        return new ModelAndView("redirect:customer");
//     }

}
