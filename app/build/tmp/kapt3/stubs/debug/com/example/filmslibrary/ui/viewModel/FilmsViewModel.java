package com.example.filmslibrary.ui.viewModel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00050\u0004\u00a2\u0006\u0002\u0010\bJ!\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\b\u0010\u0013\u001a\u00020\rH\u0014R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/example/filmslibrary/ui/viewModel/FilmsViewModel;", "Landroidx/lifecycle/ViewModel;", "Landroidx/lifecycle/LifecycleObserver;", "repositoryInterface", "Lcom/example/filmslibrary/model/repository/FilmsRepositoryInterface;", "Lcom/example/filmslibrary/model/dataSource/InetDataSource;", "", "Lcom/example/filmslibrary/model/repository/FilmObject;", "(Lcom/example/filmslibrary/model/repository/FilmsRepositoryInterface;)V", "myLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/filmslibrary/model/data/AppState;", "getFilms", "", "apiKey", "", "language", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMyLiveData", "onCleared", "app_debug"})
public final class FilmsViewModel extends androidx.lifecycle.ViewModel implements androidx.lifecycle.LifecycleObserver {
    private final com.example.filmslibrary.model.repository.FilmsRepositoryInterface<com.example.filmslibrary.model.dataSource.InetDataSource<java.util.List<com.example.filmslibrary.model.repository.FilmObject>>> repositoryInterface = null;
    private final androidx.lifecycle.MutableLiveData<com.example.filmslibrary.model.data.AppState> myLiveData = null;
    
    public FilmsViewModel(@org.jetbrains.annotations.NotNull()
    com.example.filmslibrary.model.repository.FilmsRepositoryInterface<com.example.filmslibrary.model.dataSource.InetDataSource<java.util.List<com.example.filmslibrary.model.repository.FilmObject>>> repositoryInterface) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.example.filmslibrary.model.data.AppState> getMyLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getFilms(@org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    java.lang.String language, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}