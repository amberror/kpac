package com.example.utils.impl;

import com.example.utils.KPacConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KPacConversionServiceImpl extends DefaultConversionService implements KPacConversionService {

    private final ConversionService conversionService;

    @Autowired
    public KPacConversionServiceImpl(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public <S, T> List<T> convertAll(List<S> source, Class<T> targetType) {
        return source.stream().map(elem -> conversionService.convert(elem, targetType)).toList();
    }

    @Override
    public <T> T convert(@Nullable Object source, Class<T> targetType) {
        return conversionService.convert(source, targetType);
    }

}
