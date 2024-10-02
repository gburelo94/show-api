package com.gburelo.shows_api.domain.dto.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ShowRequest {

    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private String hour;

    private int maxNumberTickets;

}
