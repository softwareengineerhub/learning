package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class Test02DefaultReturnValues {

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
        System.out.println("List returned "+roomServiceMock.getAvailableRooms());
        System.out.println("Object returned "+roomServiceMock.findAvailableRoomId(null));
        System.out.println("Primitive returned "+roomServiceMock.getRoomCount());
    }

/*
By default mockito provides nice defualt value.
For list - it is empty list

 */
    @Test
    public void should_CountAvailablePlaces() {
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01),
                LocalDate.of(2020, 01, 05), 2, false);

        //given
        double expected = 0;

        //when
        double actual = bookingService.getAvailablePlaceCount();

        //then
        assertEquals(expected, actual);

    }

}
