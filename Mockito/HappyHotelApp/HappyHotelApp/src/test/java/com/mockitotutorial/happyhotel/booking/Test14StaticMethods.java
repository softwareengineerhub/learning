package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Test14StaticMethods {

    @InjectMocks
    private BookingService bookingService;
    @Mock
    private PaymentService paymentServiceMock;
    @Mock
    private RoomService roomServiceMock;
    @Mock
    private BookingDAO bookingDAOMock;
    @Mock
    private MailSender mailSenderMock;
    @Captor
    private ArgumentCaptor<Double> doubleCaptor;
    @Captor
    private ArgumentCaptor<BookingRequest> bookingRequestCaptor;

    @Test
    public void should_CalculateCorrectPrice() {
        try (MockedStatic<CurrencyConverter> mockedConverter = mockStatic(CurrencyConverter.class)) {

            //given
            BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01),
                    LocalDate.of(2020, 01, 05), 2, false);

            double expected = 400.0;

            mockedConverter.when(() ->
                CurrencyConverter.toEuro(anyDouble())).thenReturn(400.0);

            double actual = bookingService.calculatePriceEuro(bookingRequest);
            assertEquals(expected, actual);
        }
    }

}

