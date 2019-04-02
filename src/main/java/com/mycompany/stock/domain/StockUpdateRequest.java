package com.mycompany.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;

@JsonDeserialize(builder = AutoValue_StockUpdateRequest.Builder.class)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AutoValue
public abstract class StockUpdateRequest {

    @Nullable
    public abstract String getStockId();

    @Nullable
    public abstract Float getCurrentPrice();

    @Nullable
    public abstract String getCurrency();

    public static Builder builder() {
        return new AutoValue_StockUpdateRequest.Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder stockId(final String stockId);

        public abstract Builder currentPrice(final Float currentPrice);

        public abstract Builder currency(final String currency);

        public abstract StockUpdateRequest build();

    }
}