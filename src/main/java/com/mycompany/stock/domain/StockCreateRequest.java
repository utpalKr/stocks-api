package com.mycompany.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.annotation.Nullable;
import com.google.auto.value.AutoValue;

@JsonDeserialize(builder = AutoValue_StockCreateRequest.Builder.class)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AutoValue
public abstract class StockCreateRequest {

    @Nullable
    public abstract String getStockName();

    @Nullable
    public abstract Float getCurrentPrice();

    @Nullable
    public abstract String getCurrency();

    public static Builder builder() {
        return new AutoValue_StockCreateRequest.Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder stockName(final String stockName);

        public abstract Builder currentPrice(final Float currentPrice);

        public abstract Builder currency(final String currency);

        public abstract StockCreateRequest build();

    }
}