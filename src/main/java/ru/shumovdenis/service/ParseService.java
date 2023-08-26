package ru.shumovdenis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.shumovdenis.model.Data;
import ru.shumovdenis.model.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ParseService {
    public List<Ticket> parseTickets(String path) {
        File file = new File(path);
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        try {
            Data data = objectMapper.readerFor(Data.class).readValue(file);
            return data.getTickets();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
