package com.example.intellijexercise1.Web;

import com.example.intellijexercise1.Entities.Customer;
import com.example.intellijexercise1.Repositories.Repo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionAttributes({"id"})
@Controller
@AllArgsConstructor
public class CustomerController {

    @Autowired
    private Repo repo; //rr
    List<Customer> customerList; //xx

    @GetMapping(path="/")
    public String initialPage(Model model){

        customerList = repo.initialData();
        model.addAttribute("customers", customerList);
        model.addAttribute("customer", new Customer());

        return "Main";
    }

    /*
    * - The add method will be used by both edit and add.
    * - Since it will be used by edit, and we will allow users to edit even the email address, the
    program should save the old value of the email address. This will be saved in a session
    attribute called ID
    * - For editing, the ID will not be null as the record is existing. The logic of the code is that it
    will search for the existing record delete it and add the new record. This implementation
    is reflected by the for loop.
    * - If it is a new user, the email will be null, and it will just add a new customer.
    * - The customer object is bound to the text boxes of the view.
    * - At the end of the method, in order to prepare it for a next possible edit, the id is set to null
    and the session attribute is removed.
    * - Finally the data is once again set to Main.
    */

    @PostMapping(path = "/empInsert")
    public String addCustomers(Model model, Customer customer, HttpSession session, ModelMap modelMap){
        String emailInput;
        String oldEmail = (String) session.getAttribute("id");

        if(oldEmail == null){
            customerList.add(customer);
            model.addAttribute("customers",customerList);
            return "redirect:Main";
        }

        for(int i=0; i < customerList.size(); ++i){
            emailInput = customerList.get(i).getcEmail();
            if(oldEmail.equals(emailInput)){
                customerList.remove(i);
                customerList.add(customer);
                break;
            }
        }

        //Both are needed
        modelMap.put("id",null);
        session.removeAttribute("id");
        return "redirect:Main";
    }

    @GetMapping(path = "/editCustomer")
    public String editCustomer(Model model, Customer customer, String id, HttpSession session, ModelMap modelMap){
        //Find the record in the list
        String email;
        modelMap.put("id", id);

        for(int i = 0; i < customerList.size(); ++i){
            email = customerList.get(i).getcEmail();
            if(email.equals(id)){
                model.addAttribute("customer",customerList.get(i));
                break;
            }
        }
        return "Main";
    }

    @GetMapping(path = "/deleteCustomer")
    public String deleteCustomer(Model model, String id){
        //Find the record in the list
        String email;

        for(int i = 0; i < customerList.size(); ++i){
            email = customerList.get(i).getcEmail();
            if(email.equals(id)){
                customerList.remove(i);
                break;
            }
        }
        return "redirect:Main";
    }

    @GetMapping(path = "/Main")
    public String indexPage(Model model){
        model.addAttribute("customers", customerList);
        model.addAttribute("customer", new Customer());
        return "Main";
    }
}
