package com.gburelo.shows_api.utilities;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Messages {

    public static final String SHOW_CREATED_SUCCESFULLY = "Evento registrado exitosamente.";

    public static final String SHOW_UPDATED_SUCCESFULLY = "Evento acutalizado exitosamente.";

    public static final String SHOW_NOT_FOUND = "No se encontro ningun evento con los parametros dados.";

    public static final String DATES_INVALID = "No se pudo crear el evento porque una de las fechas no es valida.";

    public static final String NUMBER_TICKETS_INVALID = "No se puedo crear el evento porque el numero minimo de boletos por es 1 y maximo 300.";

    public static final String PURCHASE_SUCCESSFUL = "Compra realizada exitosamente.";

    public static final String SHOW_FINISHED = "El boleto no puede ser canjeado porque el evento ha finalizado.";

}
