/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.model.Car;
import com.github.adminfaces.starter.service.CarService;
import com.github.adminfaces.starter.util.Utils;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import static com.github.adminfaces.template.util.Assert.has;

/**
 * @author rmpestano
 */
@Named
@ViewScoped
public class CarFormMB implements Serializable {


    private Integer id;
    private Car car;

    @Inject
    CarService carService;

    public void init() {
        if(Faces.isAjaxRequest()){
           return;
        }
        if (has(id)) {
            car = carService.findById(id);
        } else {
            car = new Car();
        }
    }

    @PostConstruct
    public void postConstruct() {
        Logger.getLogger(getClass().getName()).info(getClass()+": postConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        Logger.getLogger(getClass().getName()).info(getClass()+": preDestroy");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


    public void remove() throws IOException {
        if (has(car) && has(car.getId())) {
            carService.remove(car);
            Utils.addDetailMessage("Car " + car.getModel()
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("car-list.xhtml");
        }
    }

    public void save() {
        String msg;
        if (car.getId() == null) {
            carService.insert(car);
            msg = "Car " + car.getModel() + " created successfully";
        } else {
            carService.update(car);
            msg = "Car " + car.getModel() + " updated successfully";
        }
        Utils.addDetailMessage(msg);
    }

    public void clear() {
        car = new Car();
        id = null;
    }

    public boolean isNew() {
        return car == null || car.getId() == null;
    }


}
