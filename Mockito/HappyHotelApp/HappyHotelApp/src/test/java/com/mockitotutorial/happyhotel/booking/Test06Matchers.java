package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Test06Matchers {

    private BookingService bookingService;
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    public void setup() {
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = mock(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);

        this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
    }


    /*
    for any time of BookingRequest (for different instances of the class)
     */
    @Test
    public void should_NotCompleteBooking_When_PriceTooHigh() {
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01),
                LocalDate.of(2020, 01, 05), 2, true);


        //when(paymentServiceMock.pay(bookingRequest, price)).thenThrow(BusinessException.class);
        when(paymentServiceMock.pay(any(), anyDouble())).thenThrow(BusinessException.class);
        //when(paymentServiceMock.pay(any(), eq(400.0))).thenThrow(BusinessException.class);

        //anyString() does not match a null String
        // for null - use any()
        // for primitives - use primitive specific any like anyDouble()

        //when
        double actual = bookingService.calculatePrice(bookingRequest);

        Executable executable = () -> bookingService.makeBooking(bookingRequest);
        //then
        assertThrows(BusinessException.class, executable);
    }

}

