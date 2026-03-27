package com.stam.api.kafka.dto;

import com.stam.api.dto.GameRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class PartnerCatalogImportMessage {

    @NotNull
    private Integer schemaVersion;

    @NotNull
    private UUID eventId;

    @NotNull
    private Instant producedAt;

    @NotBlank
    private String partnerId;

    @NotBlank
    private String mode;

    @Valid
    @NotNull
    private GameRequestDTO game;
}
