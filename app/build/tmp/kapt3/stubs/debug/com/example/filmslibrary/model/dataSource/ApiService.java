package com.example.filmslibrary.model.dataSource;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\'\u00a8\u0006\t"}, d2 = {"Lcom/example/filmslibrary/model/dataSource/ApiService;", "", "getListOfFilmsAsync", "Lkotlinx/coroutines/Deferred;", "", "Lcom/example/filmslibrary/model/repository/FilmObject;", "apiKey", "", "language", "app_debug"})
public abstract interface ApiService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "trending/movie/day")
    public abstract kotlinx.coroutines.Deferred<java.util.List<com.example.filmslibrary.model.repository.FilmObject>> getListOfFilmsAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "day")
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    java.lang.String language);
}