package com.gburelo.shows_api.utilities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

    /* Tamanio de la hoja */
    int size = 5;

    /* Numero de pagina solicitada */
    int page = 1;

    /* Direccion de ordenado
    * 0 para Ascendente o ASC
    * 1 para Descendente o DESC
    * */
    int sort = 1;

    String field = "updatedAt";
}
