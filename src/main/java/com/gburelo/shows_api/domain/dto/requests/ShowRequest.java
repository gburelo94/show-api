package com.gburelo.shows_api.domain.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ShowRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private LocalDate startDate;

    @NotEmpty
    private LocalDate endDate;

    @NotEmpty
    private String hour;

    @NotEmpty
    private int maxNumberTickets;

}
