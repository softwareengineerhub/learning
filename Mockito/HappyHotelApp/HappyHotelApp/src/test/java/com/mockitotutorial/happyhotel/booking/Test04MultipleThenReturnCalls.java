package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Test04MultipleThenReturnCalls {

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
    for 2d call will get empty list
     */
    @Test
    public void should_CountAvailablePlaces_When_CalledMultipleTimes() {
        //given
        when(this.roomServiceMock.getAvailableRooms())
                .thenReturn(Collections.singletonList(new Room("1", 5)))
                .thenReturn(Collections.emptyList());

        int expectedFirstCall = 5;
        int expectedSecondCall = 0;

        //when
        int actualFirst = bookingService.getAvailablePlaceCount();
        int actualSecond = bookingService.getAvailablePlaceCount();

        //then
        assertTrue(actualFirst==expectedFirstCall);
        assertTrue(actualSecond==expectedSecondCall);

        assertAll(
                ()->assertEquals(actualFirst, expectedFirstCall),
                ()->assertEquals(actualSecond, expectedSecondCall)
        );
    }

    @Test
    public void should_CountAvailablePlaces_When_MultipleRoomsAvailable() {
        //given
        List<Room> rooms = Arrays.asList(new Room("Room 1", 2),new Room("Room 2", 5));
        when(this.roomServiceMock.getAvailableRooms()).thenReturn(rooms);

        int expected = 7;

        //when
        int actual = bookingService.getAvailablePlaceCount();

        //then
        assertTrue(actual==expected);
    }

}
