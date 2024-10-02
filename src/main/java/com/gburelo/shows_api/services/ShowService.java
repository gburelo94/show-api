package com.gburelo.shows_api.services;

import com.gburelo.shows_api.domain.dto.requests.ShowRequest;
import com.gburelo.shows_api.domain.entities.Show;
import com.gburelo.shows_api.domain.entities.Ticket;
import com.gburelo.shows_api.domain.repositories.ShowRepository;
import com.gburelo.shows_api.domain.repositories.TicketRepository;
import com.gburelo.shows_api.utilities.ApiResponse;
import com.gburelo.shows_api.utilities.Pagination;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.gburelo.shows_api.utilities.Messages.*;

@Service
public class ShowService {

    @Autowired private ShowRepository showRepository;
    @Autowired private TicketRepository ticketRepository;

    public ApiResponse getShows(Pagination request){

        Page<Show> shows = showRepository.findAll(
                PageRequest.of(
                        request.getPage(),
                        request.getSize(),
                        Sort.by(
                                Sort.Direction.DESC,
                                "updated_at")));

        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .body(shows.getContent())
                .build();
    }

    /**
     * Metodo para crear un nuevo evento.
     * @param request Parametros necesarios para la creacion de un nuevo evento.
     * @return Respuesta del servidor si fue o no posible crear el evento.
     */
    public ApiResponse createShow(ShowRequest request) {

        boolean dates = areDatesValid(
                request.getStartDate(),
                request.getEndDate());

        if (!dates) {
            return ApiResponse.builder()
                    .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                    .error(DATES_INVALID)
                    .build();
        }

        boolean numberTickets = isNumberTicketsValid(
                request.getMaxNumberTickets());

        if(!numberTickets) {
            return ApiResponse.builder()
                    .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                    .error(NUMBER_TICKETS_INVALID)
                    .build();
        }

        Show show = new Show();
        BeanUtils.copyProperties(request, show);

        show.setStatus("En preparacion");
        show.setRedeemedTickets(0);
        show.setAvailableTickets(request.getMaxNumberTickets());

        this.showRepository.save(show);

        return ApiResponse.builder()
                .message(SHOW_CREATED_SUCCESFULLY)
                .status(HttpStatus.CREATED.value())
                .build();
    }

    /**
     * Metodo que se encarga de actualizar un evento.
     * @param request Parametros necesarios para la actualizacion del evento.
     * @return Respuesta del servidor sobre si se pudo o no actualizar el evento.
     */
    public ApiResponse updateShow(int showId, ShowRequest request) {

        Optional<Show> optional = showRepository.findById(showId);

        if (optional.isPresent()) {
            Show source = optional.get();

            List<Ticket> sold = ticketRepository
                    .findByShowIdAndStatus(source.getId(), "Vendido");

            Show target = new Show();
            BeanUtils.copyProperties(source, target);
            showRepository.save(target);
        } else {
            return ApiResponse.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .error(SHOW_NOT_FOUND)
                    .build();
        }

        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message(SHOW_UPDATED_SUCCESFULLY)
                .build();
    }

    /**
     * Metodo que se encarga de validar la coherencia entre las fechas del evento.
     * @param start Fecha propuesta de inicio del evento.
     * @param end Fecha propuesta de finalizacion del evento.
     * @return Veredicto de si son o no validas las fechas propuestas.
     */
    private boolean areDatesValid(LocalDate start, LocalDate end) {
        boolean areDatesValid = true;

        LocalDate today = LocalDate.now();

        if (start.isBefore(today)) {
            areDatesValid = false;
        } else if (end.isBefore(today)) {
            areDatesValid = false;
        } else if(end.isBefore(start)) {
            areDatesValid = false;
        }

        return areDatesValid;
    }

    /**
     * Metodo que se encarga de validar si el numero de boletos del evento esta en el rango aceptado.
     * @param numberTickets Numero de boletos que se desea asignar al evento.
     * @return Veredicto si es o no valido el numero de boletos.
     */
    private boolean isNumberTicketsValid(int numberTickets) {
        return numberTickets > 0 && numberTickets <= 300;
    }
}
