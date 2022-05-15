package com.vinsguru.reactivemongo.dto;

import lombok.Data;

@Data
public class ChartObj {

    private Chart obj;

    @Data
    public static class Chart {
        private String id;
        private Instrument instrument;
        private Integer timeInterval;
        private Boolean isVisible;
        private Integer index;
        private Boolean isPlaceHolder;
        private String symbol;
        private Long timestamp;
        private String barType;
    }

    @Data
    public static class Instrument {
        private String symbol;
        private String company;
        private String exchange;
    }
}
