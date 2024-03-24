package cz.czechitas.ukol06.svatky;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.MonthDay;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SvatkySluzbaTest {

    @Test
    void vyhledatSvatkyKeDni() throws IOException {
        SvatkySluzba sluzba = new SvatkySluzba();

        List<String> expected = List.of("Karina", "Vasil");
        List<String> actual = sluzba.vyhledatSvatkyKeDni(MonthDay.of(1, 2));
        assertEquals(expected, actual);
    }

    @Test
    void testSvatkyNaDatumBezSvatku() throws IOException {

        SvatkySluzba sluzba = new SvatkySluzba();

        List<String> svatky = sluzba.vyhledatSvatkyKeDni(MonthDay.of(1, 1));

        assertEquals(List.of(), svatky);

    }

    @Test
    void testSvatkyNaDatumJednoJmeno() throws IOException {
        SvatkySluzba sluzba = new SvatkySluzba();

        List<String> svatky = sluzba.vyhledatSvatkyKeDni(MonthDay.of(1, 4));


        assertEquals(List.of("Diana"), svatky);
    }
}