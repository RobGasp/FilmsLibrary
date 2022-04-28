package com.example.filmslibrary.model.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J-\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fR\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lcom/example/filmslibrary/model/repository/FilmsRepository;", "Lcom/example/filmslibrary/model/repository/FilmsRepositoryInterface;", "", "Lcom/example/filmslibrary/model/repository/FilmObject;", "dataSource", "Lcom/example/filmslibrary/model/dataSource/DataSource;", "(Lcom/example/filmslibrary/model/dataSource/DataSource;)V", "getListOfFilmsFromInternetAsync", "Lkotlinx/coroutines/Deferred;", "apiKey", "", "language", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class FilmsRepository implements com.example.filmslibrary.model.repository.FilmsRepositoryInterface<java.util.List<? extends com.example.filmslibrary.model.repository.FilmObject>> {
    private final com.example.filmslibrary.model.dataSource.DataSource<java.util.List<com.example.filmslibrary.model.repository.FilmObject>> dataSource = null;
    
    public FilmsRepository(@org.jetbrains.annotations.NotNull()
    com.example.filmslibrary.model.dataSource.DataSource<java.util.List<com.example.filmslibrary.model.repository.FilmObject>> dataSource) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getListOfFilmsFromInternetAsync(@org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    java.lang.String language, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.Deferred<? extends java.util.List<com.example.filmslibrary.model.repository.FilmObject>>> continuation) {
        return null;
    }
}