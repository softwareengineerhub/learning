package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class Test15Answer {

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

            double expected = 320.0;

            mockedConverter.when(() ->
                CurrencyConverter.toEuro(anyDouble())).thenAnswer(inv -> (double)inv.getArgument(0)*0.8);

            double actual = bookingService.calculatePriceEuro(bookingRequest);
            assertEquals(expected, actual);
        }
    }

}

