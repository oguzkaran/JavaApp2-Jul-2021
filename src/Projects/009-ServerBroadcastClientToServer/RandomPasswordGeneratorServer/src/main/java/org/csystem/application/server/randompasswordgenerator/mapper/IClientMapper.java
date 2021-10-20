package org.csystem.application.server.randompasswordgenerator.mapper;

import org.csystem.application.server.randompasswordgenerator.data.dto.ClientDTO;
import org.csystem.application.server.randompasswordgenerator.data.entity.Client;
import org.mapstruct.Mapper;

@Mapper(implementationName = "ClientMapper", componentModel = "spring")
public interface IClientMapper {
    ClientDTO toClientDTO(Client client);
    Client toClient(ClientDTO clientDTO);
}
