package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;


//Spies = partial mock
class Test08Spies {

    private BookingService bookingService;
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    public void setup() {
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = spy(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);

        this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
    }


    /*
    mock = dummy object with no real logic
    spy = real object with real logic that we can modify
     */
    @Test
    public void should_InvokePayment_When_InputOK() {
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01),
                LocalDate.of(2020, 01, 05), 2, true);

        //for mock() - it will return null
        //for spy - it returns string value - not null
        String bookingId = bookingService.makeBooking(bookingRequest);


        verify(bookingDAOMock).save(bookingRequest);
        System.out.println("bookingId="+bookingId);

    }


    /*
    mocks: when(mock.method()).thenReturn()
    spies: doReturn().when(spy).method())
     */
    @Test
    public void should_CancelBooking_When_InputOK() {
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01),
                LocalDate.of(2020, 01, 05), 2, true);
        bookingRequest.setRoomId("1.3");

        String bookingId = "1";
        doReturn(bookingRequest).when(bookingDAOMock).get(bookingId);



        bookingService.cancelBooking(bookingId);


    }

}

