package com.rental.demo.service;

import com.rental.demo.model.Booking;
import com.rental.demo.model.Car;
import com.rental.demo.repository.BookingRepository;
import com.rental.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BookingService {
    @Autowired private BookingRepository bookingRepo;
    @Autowired private CarRepository carRepo;

    @Transactional
    public synchronized String bookCar(Long carId, Long userId, LocalDateTime start, LocalDateTime end) {
        Car car = carRepo.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        if (!car.isAvailable()) {
            return "Car not available";
        }
        car.setAvailable(false);
        carRepo.save(car);

        Booking booking = new Booking();
        booking.setCar(car);
        booking.setUserId(userId);
        booking.setStartTime(start);
        booking.setEndTime(end);
        bookingRepo.save(booking);

        return "Booking confirmed";
    }
}

