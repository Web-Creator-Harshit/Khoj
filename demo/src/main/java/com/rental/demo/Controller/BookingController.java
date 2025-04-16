package com.rental.demo.Controller;

import com.rental.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<String> bookCar(@RequestBody BookingRequest request) {
        String status = bookingService.bookCar(
            request.getCarId(),
            request.getUserId(),
            request.getStart(),
            request.getEnd()
        );
        return ResponseEntity.ok(status);
    }

    public static class BookingRequest {
        private Long carId;
        private Long userId;
        private LocalDateTime start;
        private LocalDateTime end;

        public Long getCarId() { return carId; }
        public void setCarId(Long carId) { this.carId = carId; }
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public LocalDateTime getStart() { return start; }
        public void setStart(LocalDateTime start) { this.start = start; }
        public LocalDateTime getEnd() { return end; }
        public void setEnd(LocalDateTime end) { this.end = end; }
    }
}

