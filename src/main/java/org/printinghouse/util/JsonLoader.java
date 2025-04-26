package org.printinghouse.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.printinghouse.model.Order;
import org.printinghouse.model.Publication;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class JsonLoader {
    private static final ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static Set<Publication> loadPublications(String filePath) {
        try {
            return objectMapper.readValue(new File(filePath), new TypeReference<Set<Publication>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Order> loadOrders(String filePath) {
        try {
            return objectMapper.readValue(new File(filePath), new TypeReference<List<Order>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}