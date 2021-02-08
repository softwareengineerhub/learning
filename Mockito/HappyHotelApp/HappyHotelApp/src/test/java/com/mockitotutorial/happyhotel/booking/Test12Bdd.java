package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//for junit4
//@RunWith(MockitoExtension.class)
class Test12Bdd {

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
    1st test form Test03
    when..thenReturn
    given..willReturn
     */
    @Test
    public void should_CountAvailablePlaces_When_OneRoomAvailable() {
        //given
        given(this.roomServiceMock.getAvailableRooms()).willReturn(Collections.singletonList(new Room("1", 5)));

        int expected = 5;

        //when
        int actual = bookingService.getAvailablePlaceCount();

        //then
        assertTrue(actual==expected);
    }

    /*
    1st test form Test07
    verify
    then..should
     */
    @Test
    public void should_InvokePayment_When_Prpepaid() {
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01),
                LocalDate.of(2020, 01, 05), 2, true);

        bookingService.makeBooking(bookingRequest);

        //check that method was called with concrete parameters
        verify(paymentServiceMock).pay(bookingRequest, 400.0);

        //check that method was called with concrete parameters and once
        //verify(paymentServiceMock, times(1)).pay(bookingRequest, 400.0);
        then(paymentServiceMock).should(times(1)).pay(bookingRequest, 400);

        //checks that methos was not called 2d time; exactly once
        verifyNoMoreInteractions(paymentServiceMock);


    }

}

