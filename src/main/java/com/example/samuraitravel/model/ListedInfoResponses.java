package com.example.samuraitravel.model;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ListedInfoResponses {

    @JsonProperty("info")
    private List<Info> info;

    public static class Info {
        @JsonProperty("Date")
        private String date;

        @JsonProperty("Code")
        private String code;

        @JsonProperty("CompanyName")
        private String companyName;

        @JsonProperty("CompanyNameEnglish")
        private String companyNameEnglish;

        @JsonProperty("Sector17Code")
        private String sector17Code;

        @JsonProperty("Sector17CodeName")
        private String sector17CodeName;

        @JsonProperty("Sector33Code")
        private String sector33Code;

        @JsonProperty("Sector33CodeName")
        private String sector33CodeName;

        @JsonProperty("ScaleCategory")
        private String scaleCategory;

        @JsonProperty("MarketCode")
        private String marketCode;

        @JsonProperty("MarketCodeName")
        private String marketCodeName;

        @JsonProperty("MarginCode")
        private String marginCode;
        
        @JsonProperty("MarginCodeName")
        private String marginCodeName;

        public String getDate() {
            return date;
        }

        public String getCode() {
            return code;
        }

        public String getCompanyName() {
            return companyName;
        }

        public String getCompanyNameEnglish() {
            return companyNameEnglish;
        }

        public String getSector17Code() {
            return sector17Code;
        }

        public String getSector17CodeName() {
            return sector17CodeName;
        }

        public String getSector33Code() {
            return sector33Code;
        }

        public String getSector33CodeName() {
            return sector33CodeName;
        }

        public String getScaleCategory() {
            return scaleCategory;
        }

        public String getMarketCode() {
            return marketCode;
        }

        public String getMarketCodeName() {
            return marketCodeName;
        }

        public String getMarginCode() {
            return marginCode;
        }

        public String getMarginCodeName() {
            return marginCodeName;
        }

    }

}
