package org.csystem.app.service.rest.greeting.mapper;

import org.csystem.app.service.grpc.greeting.proto.GreetingResponse;
import org.csystem.app.service.rest.greeting.dto.GreetingResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "GreetingResponseMapperImpl", componentModel = "spring")
public interface IGreetingResponseMapper {
    @Mapping(source = "greeting", target = "message")
    @Mapping(source = "maritalStatus", target = "maritalStatus")
    GreetingResponseDTO toGreetingResponseDTO(GreetingResponse greetingResponse);
}
