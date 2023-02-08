package com.chadwick.GoBankDB.dto;

import com.chadwick.GoBankDB.model.Address;
import com.chadwick.GoBankDB.model.Birthday;
import com.chadwick.GoBankDB.model.Name;
import com.chadwick.GoBankDB.model.Recipient;

import java.util.List;
import java.util.UUID;

public record UserDTO(
        UUID userId,
        String email,
        Name name,
        String gender,
        Address address,
        Birthday birthday,
        List<Recipient> recipientList,
        List<UUID> accountIDs
) {
}
