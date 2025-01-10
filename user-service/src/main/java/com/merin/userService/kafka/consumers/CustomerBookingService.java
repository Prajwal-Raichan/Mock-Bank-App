//package com.mbms.userService.kafka.consumers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class CustomerBookingService {
//
//    @Autowired
//    private ICustomerRepository customerRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper = new ObjectMapper();;
//
//    //@KafkaListener(topics = "booking-events", groupId = "CUSTOMER-G1")
//    @Transactional
//    public void consumeBookingEvent(String metaData) throws Exception {
//
//        CustomerBookingEvent bookingEvent = objectMapper.readValue(metaData, CustomerBookingEvent.class);
//        int customerId = bookingEvent.getCustomerId();
//        int bookingId = bookingEvent.getBookingId();
//
//        customerRepository.findById(customerId).ifPresent(customer -> {
//            customer.getBookingIds().add(bookingId);
//            customerRepository.save(customer);
//        });
//    }
//
//
//}
