/*
 * VehicleWebController
 *
 * Version 1
 *
 * Artem Beshevli
 */

package edu.besh.rentacar.controller.web;

import edu.besh.rentacar.entity.Types;
import edu.besh.rentacar.entity.Vehicle;
import edu.besh.rentacar.forms.SearchForm;
import edu.besh.rentacar.forms.VehicleForm;
import edu.besh.rentacar.service.vehicle.impls.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/web/vehicle")  // Prefix for each request
@CrossOrigin("*")          // Enabling Cross Origin Requests
@Controller    //  annotion  defines web cpntrooler
public class VehicleWebController {

    //  initialization an object of Vehicle Service
    @Autowired
    VehicleServiceImpl vehicleService;

     // The full request with prefix is "/web/vehicle/list"
    @RequestMapping("/list")
    String getAll(Model model){             // request for all the cars in a  database
        List<Vehicle> list = vehicleService.getAll();
        SearchForm searchForm = new SearchForm();
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("carset", list);  // charge a set of data for HTML page

        return "vehicleList";  //  return the name of a special html file
    }
         //  a request to delete the car with the mentioned id
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(value = "id")int id){
        vehicleService.delete(id);
        List<Vehicle> list = vehicleService.getAll();

        model.addAttribute("carset", list);

        return "vehicleList";
    }

    // GET request provides  a special empty web input form to inject data of a new car
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addVehicle(Model model){
        VehicleForm vehicleForm = new VehicleForm();
        vehicleForm.setUrl("");
        List types = Arrays.asList(Types.values());

        model.addAttribute("vehicleForm", vehicleForm);
        model.addAttribute("types", types);
        return "addVehicle";
    }

    // POST request provides return of a filled form of new car's data
    // transform data from the form into a new object ( car )
    // and save it into the MongoDB
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addVehicle(Model model,
                            @ModelAttribute("vehicleForm") VehicleForm vehicleForm){

        Types type = Types.CROSSOVER;

        String carType = vehicleForm.getType();

        //  Transform a string from input form into item from enumerated list
        switch(carType) {
            case "CROSSOVER": type = Types.CROSSOVER;
                break;
            case "SUV": type = Types.SUV;
                break;
            case "HATCHBACK": type = Types.HATCHBACK;
                break;
            case "SEDAN": type = Types.SEDAN;
                break;
            case "ELECTROMOBILE": type = Types.ELECTROMOBILE;
                break;
             case "COUPE": type = Types.COUPE;
                break;
             case "CONVERTIBLE": type = Types.CONVERTIBLE;
                break;
             case "VAN": type = Types.VAN;
                break;
            case "PICKUP": type = Types.PICKUP;
                break;

        }

        Vehicle newVehicle = new Vehicle(vehicleForm.getId(), vehicleForm.getBrand(), vehicleForm.getModel()
                ,vehicleForm.getCost(), vehicleForm.getLicensePlate(), type, vehicleForm.getYearOfIssue()
                , vehicleForm.getRentalFee(), vehicleForm.isMaintenance(), vehicleForm.isTaken());
        if(vehicleForm.getUrl().equals("") || vehicleForm.getUrl() == null) newVehicle.setUrl("/img/noimage.jpg");
        vehicleService.create(newVehicle);
        model.addAttribute("people", vehicleService.getAll());
        return "redirect:/web/vehicle/list";
    }


    // GET request privedes a form just filled by data of a chosen car
    // gives an opportunity to edit the data
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editVehicle(Model model, @PathVariable(value = "id") int id){
        VehicleForm vehicleForm = new VehicleForm();
        Vehicle vehicle = vehicleService.get(id);

        vehicleForm.setId(vehicle.getId());
        vehicleForm.setBrand(vehicle.getBrand());
        vehicleForm.setModel(vehicle.getModel());
        vehicleForm.setUrl(vehicle.getUrl());
        vehicleForm.setCost(vehicle.getCost());
        vehicleForm.setLicensePlate(vehicle.getLicensePlate());
        vehicleForm.setYearOfIssue(vehicle.getYearOfIssue());
        vehicleForm.setRentalFee(vehicle.getRentalFee());
        vehicleForm.setMaintenance(vehicle.isMaintenance());
        vehicleForm.setTaken(vehicle.isTaken());

        List types = Arrays.asList(Types.values());

        model.addAttribute("vehicleForm", vehicleForm);
        model.addAttribute("types", types);
        return "addVehicle";
    }


    // POST request returns the filled form of the acr to be modified
    // transform data from the form into the modified object ( car )
    // and save it inti rthe MongoDB
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editVehicle(Model model,
                             @ModelAttribute("vehicleForm") VehicleForm vehicleForm){

        Types type = null;
        String carType = vehicleForm.getType();

        switch(carType) {
            case "CROSSOVER": type = Types.CROSSOVER;
                break;
            case "SUV": type = Types.SUV;
                break;
            case "HATCHBACK": type = Types.HATCHBACK;
                break;
            case "SEDAN": type = Types.SEDAN;
                break;
            case "ELECTROMOBILE": type = Types.ELECTROMOBILE;
                break;
            case "COUPE": type = Types.COUPE;
                break;
            case "CONVERTIBLE": type = Types.CONVERTIBLE;
                break;
            case "VAN": type = Types.VAN;
                break;
            case "PICKUP": type = Types.PICKUP;
                break;

        }
        Vehicle newVehicle = new Vehicle(vehicleForm.getId(), vehicleForm.getBrand(), vehicleForm.getModel()
                ,vehicleForm.getCost(), vehicleForm.getLicensePlate(), type, vehicleForm.getYearOfIssue()
                , vehicleForm.getRentalFee(), vehicleForm.isMaintenance(), vehicleForm.isTaken());
        vehicleService.edit(newVehicle);
        model.addAttribute("vehicles", vehicleService.getAll());
        return "redirect:/web/vehicle/list";
    }

}
