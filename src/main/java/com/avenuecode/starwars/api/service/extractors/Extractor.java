package com.avenuecode.starwars.api.service.extractors;

public interface Extractor<I, O> {
    public O extract(I input);
}
