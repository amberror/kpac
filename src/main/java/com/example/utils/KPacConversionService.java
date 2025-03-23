package com.example.utils;

import org.springframework.core.convert.support.ConfigurableConversionService;

import java.util.List;

public interface KPacConversionService extends ConfigurableConversionService {
    <S, T> List<T> convertAll(List<S> source, Class<T> targetType);
}
