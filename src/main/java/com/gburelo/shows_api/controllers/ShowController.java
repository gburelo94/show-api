package com.gburelo.shows_api.controllers;

import com.gburelo.shows_api.domain.dto.requests.ShowRequest;
import com.gburelo.shows_api.services.ShowService;
import com.gburelo.shows_api.utilities.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/shows")
public class ShowController {

    @Autowired private ShowService showService;

    @Valid
    @PostMapping
    public ApiResponse createShow(@RequestBody ShowRequest request) {
        return showService.createShow(request);
    }

    @Valid
    @PutMapping("/{id}")
    public ApiResponse updateShow(
            @PathVariable(value = "id") int showId,
            @RequestBody ShowRequest request) {
        return showService.updateShow(showId, request);
    }
}
