package com.codecool.ehotel.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record Buffet (Map<MealType, List<LocalDateTime>> portions) {

    //The original parameter types will be converted and available as a Hashmap
//    public Buffet() {
//        this(new HashMap<>());
//    }
}
