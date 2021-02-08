package com.mockitotutorial.happyhotel.booking;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//for junit4
//@RunWith(MockitoExtension.class)
class Test13StrictStubbing {

    @InjectMocks
    private BookingService bookingService;
    @Mock
    private PaymentService paymentServiceMock;
    @Mock
    private RoomService roomServiceMock;

    @Mock
    //@Spy
    private BookingDAO bookingDAOMock;

    @Mock
    private MailSender mailSenderMock;
    @Captor
    private ArgumentCaptor<Double> doubleCaptor;
    @Captor
    private ArgumentCaptor<BookingRequest> bookingRequestCaptor;


    /*
    UnnecessaryStubbingException class.
    When you define some behaviour of your class, but then never use it in the case --> this exception is throwing

    Strict Stubbing - you need to use what you defined
     */
    @Ignore
    @Test
    public void should_InvokePayment_When_Prpepaid() {
        //given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01),
                LocalDate.of(2020, 01, 05), 2, false);

        when(paymentServiceMock.pay(any(), anyDouble())).thenReturn("1");

        //when
        bookingService.makeBooking(bookingRequest);

        //then
        //no exception is thrown

    }


    @Test
    public void avoid_UnnecessaryStubbingException_should_InvokePayment_When_Prpepaid() {
        //given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01),
                LocalDate.of(2020, 01, 05), 2, false);

       lenient().when(paymentServiceMock.pay(any(), anyDouble())).thenReturn("1");

        //when
        bookingService.makeBooking(bookingRequest);

        //then
        //no exception is thrown

    }

}

