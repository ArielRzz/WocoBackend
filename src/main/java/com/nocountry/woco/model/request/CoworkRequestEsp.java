package com.nocountry.woco.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoworkRequestEsp {

        private boolean betters;
        private boolean closer;
        private boolean room;
        private Double minPrice;
        private Double maxPrice;
        private boolean sWifi;
        private boolean sPrinter;
        private boolean sChairs;
        private boolean sParking;
        private boolean sLockers;
        private boolean sCalefaction;

}
