package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.List;

public interface CarService {
    public Car create(Car car);

    public List<Car> findAll();

    public Car findById(String id);

    public Car update(String id, Car car);

    public void deleteByCarId(String id);

    public void deleteAll();
}
