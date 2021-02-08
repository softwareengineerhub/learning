package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//for junit4
//@RunWith(MockitoExtension.class)
class Test11Annotations {

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

   /* @BeforeEach
    public void setup() {
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = mock(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);

        this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

        this.doubleCaptor = ArgumentCaptor.forClass(Double.class);
    }*/


    @Test
    public void should_PayCorrectPrice_When_Input() {
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01),
                LocalDate.of(2020, 01, 05), 2, true);

        //when
        bookingService.makeBooking(bookingRequest);


        //check that method was called with concrete parameters
        verify(paymentServiceMock).pay(bookingRequest, 400.0);

        //check that method was called with concrete parameters and once
        verify(paymentServiceMock, times(1)).pay(eq(bookingRequest), doubleCaptor.capture());
        double capturedArgument = this.doubleCaptor.getValue();

        System.out.println(capturedArgument);
        assertEquals(400, capturedArgument);
    }

    @Test
    public void should_PayCorrectPrice_When_MultipleCalls() {
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01),
                LocalDate.of(2020, 01, 05), 2, true);

        BookingRequest bookingRequest2 = new BookingRequest("1", LocalDate.of(2020, 01, 01),
                LocalDate.of(2020, 01, 02), 2, true);

        List<Double> expectedValues = Arrays.asList(400.0, 100.0);

        //when
        bookingService.makeBooking(bookingRequest);
        bookingService.makeBooking(bookingRequest2);

        //check that method was called with concrete parameters and once
        verify(paymentServiceMock, times(2)).pay(any(), doubleCaptor.capture());
        List<Double> capturedArguments = this.doubleCaptor.getAllValues();

        assertEquals(expectedValues, capturedArguments);
    }

}

