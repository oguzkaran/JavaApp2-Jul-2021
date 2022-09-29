package org.csystem.app.api.mapper;

public interface IMapper<S, R> {
    R apply(S s);
}
