package com.avenuecode.starwars.api.service.extractors;

public interface Extractor<I, R> {
    public R extract(I input);
}
