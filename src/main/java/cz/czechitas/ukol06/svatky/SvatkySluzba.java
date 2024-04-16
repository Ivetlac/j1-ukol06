package cz.czechitas.ukol06.svatky;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SvatkySluzba {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final Path cestaKDatum = Path.of("data/svatky.json");
    private final SeznamSvatku seznamSvatku;

    public SvatkySluzba() {
        SeznamSvatku tempSeznamSvatku = null;
        try {
            String json = Files.readString(cestaKDatum);
            tempSeznamSvatku = objectMapper.readValue(json, SeznamSvatku.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        seznamSvatku = tempSeznamSvatku;
    }

    public List<String> vyhledatSvatkyDnes() {
        return vyhledatSvatkyKeDni(MonthDay.now());
    }

    public List<String> vyhledatSvatkyKeDni(MonthDay day) {
        return seznamSvatku.getSvatky().stream()
                .filter(svatek -> svatek.getDen().equals(day))
                .map(Svatek::getJmeno).toList();
    }
}
