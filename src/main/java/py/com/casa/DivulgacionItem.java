package py.com.casa;

import java.util.List;

@lombok.NoArgsConstructor
@lombok.Data
public class DivulgacionItem {

    @com.fasterxml.jackson.annotation.JsonProperty("totales")
    private TotalesDTO totales;
    @com.fasterxml.jackson.annotation.JsonProperty("candidatos")
    private List<CandidatosDTO> candidatos;
    @com.fasterxml.jackson.annotation.JsonProperty("hora")
    private HoraDTO hora;
    @com.fasterxml.jackson.annotation.JsonProperty("horaFormated")
    private String horaFormated;

    @lombok.NoArgsConstructor
    @lombok.Data
    public static class TotalesDTO {
        @com.fasterxml.jackson.annotation.JsonProperty("totalMesas")
        private Integer totalMesas;
        @com.fasterxml.jackson.annotation.JsonProperty("mesasPublicadas")
        private Integer mesasPublicadas;
        @com.fasterxml.jackson.annotation.JsonProperty("blancos")
        private Integer blancos;
        @com.fasterxml.jackson.annotation.JsonProperty("nulos")
        private Integer nulos;
        @com.fasterxml.jackson.annotation.JsonProperty("totalVotos")
        private Integer totalVotos;
        @com.fasterxml.jackson.annotation.JsonProperty("canElectores")
        private Integer canElectores;
        @com.fasterxml.jackson.annotation.JsonProperty("canElectoresPublicados")
        private Integer canElectoresPublicados;
        @com.fasterxml.jackson.annotation.JsonProperty("nocomputados")
        private Integer nocomputados;
        @com.fasterxml.jackson.annotation.JsonProperty("tipCandidatura")
        private Integer tipCandidatura;
    }

    @lombok.NoArgsConstructor
    @lombok.Data
    public static class HoraDTO {
        @com.fasterxml.jackson.annotation.JsonProperty("year")
        private Integer year;
        @com.fasterxml.jackson.annotation.JsonProperty("month")
        private String month;
        @com.fasterxml.jackson.annotation.JsonProperty("hour")
        private Integer hour;
        @com.fasterxml.jackson.annotation.JsonProperty("minute")
        private Integer minute;
        @com.fasterxml.jackson.annotation.JsonProperty("second")
        private Integer second;
        @com.fasterxml.jackson.annotation.JsonProperty("dayOfYear")
        private Integer dayOfYear;
        @com.fasterxml.jackson.annotation.JsonProperty("dayOfWeek")
        private String dayOfWeek;
        @com.fasterxml.jackson.annotation.JsonProperty("dayOfMonth")
        private Integer dayOfMonth;
        @com.fasterxml.jackson.annotation.JsonProperty("monthValue")
        private Integer monthValue;
        @com.fasterxml.jackson.annotation.JsonProperty("nano")
        private Integer nano;
        @com.fasterxml.jackson.annotation.JsonProperty("chronology")
        private HoraDTO.ChronologyDTO chronology;

        @lombok.NoArgsConstructor
        @lombok.Data
        public static class ChronologyDTO {
            @com.fasterxml.jackson.annotation.JsonProperty("calendarType")
            private String calendarType;
            @com.fasterxml.jackson.annotation.JsonProperty("id")
            private String id;
        }
    }

    @lombok.NoArgsConstructor
    @lombok.Data
    public static class CandidatosDTO {
        @com.fasterxml.jackson.annotation.JsonProperty("orden")
        private Integer orden;
        @com.fasterxml.jackson.annotation.JsonProperty("numLista")
        private String numLista;
        @com.fasterxml.jackson.annotation.JsonProperty("nomCandidato")
        private String nomCandidato;
        @com.fasterxml.jackson.annotation.JsonProperty("desPartido")
        private String desPartido;
        @com.fasterxml.jackson.annotation.JsonProperty("colLista")
        private String colLista;
        @com.fasterxml.jackson.annotation.JsonProperty("votos")
        private Integer votos;
        @com.fasterxml.jackson.annotation.JsonProperty("imgCandidato")
        private String imgCandidato;
        @com.fasterxml.jackson.annotation.JsonProperty("candidatosPref")
        private List<CandidatosPrefDTO> candidatosPref;

        @lombok.NoArgsConstructor
        @lombok.Data
        public static class CandidatosPrefDTO {
            @com.fasterxml.jackson.annotation.JsonProperty("orden")
            private Integer orden;
            @com.fasterxml.jackson.annotation.JsonProperty("numLista")
            private String numLista;
            @com.fasterxml.jackson.annotation.JsonProperty("nomCandidato")
            private String nomCandidato;
            @com.fasterxml.jackson.annotation.JsonProperty("desPartido")
            private String desPartido;
            @com.fasterxml.jackson.annotation.JsonProperty("colLista")
            private String colLista;
            @com.fasterxml.jackson.annotation.JsonProperty("votos")
            private Integer votos;
            @com.fasterxml.jackson.annotation.JsonProperty("ordCandidato")
            private Integer ordCandidato;
        }
    }
}
