package com.codecool.ehotel.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record Buffet (Map<MealType, List<LocalDateTime>> portions) {

}
