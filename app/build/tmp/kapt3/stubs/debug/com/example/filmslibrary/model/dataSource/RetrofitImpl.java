package com.example.filmslibrary.model.dataSource;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00182\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0001\u0018B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0002J-\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00122\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lcom/example/filmslibrary/model/dataSource/RetrofitImpl;", "Lcom/example/filmslibrary/model/dataSource/InetDataSource;", "", "Lcom/example/filmslibrary/model/repository/FilmObject;", "()V", "proxy", "Ljava/net/Proxy;", "proxyHost", "", "proxyPort", "", "createHTPPClient", "Lokhttp3/OkHttpClient;", "interceptor", "Lokhttp3/Interceptor;", "createRetrofit", "Lretrofit2/Retrofit;", "getDataAsync", "Lkotlinx/coroutines/Deferred;", "apiKey", "language", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getService", "Lcom/example/filmslibrary/model/dataSource/ApiService;", "Companion", "app_debug"})
public final class RetrofitImpl implements com.example.filmslibrary.model.dataSource.InetDataSource<java.util.List<? extends com.example.filmslibrary.model.repository.FilmObject>> {
    private final java.lang.String proxyHost = "5.189.155.147";
    private final int proxyPort = 1080;
    private final java.net.Proxy proxy = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.filmslibrary.model.dataSource.RetrofitImpl.Companion Companion = null;
    private static final java.lang.String BASE_LIST_OF_FILMS_URL = "https://api.themoviedb.org/3/";
    
    public RetrofitImpl() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getDataAsync(@org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    java.lang.String language, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.Deferred<? extends java.util.List<com.example.filmslibrary.model.repository.FilmObject>>> continuation) {
        return null;
    }
    
    private final com.example.filmslibrary.model.dataSource.ApiService getService(okhttp3.Interceptor interceptor) {
        return null;
    }
    
    private final retrofit2.Retrofit createRetrofit(okhttp3.Interceptor interceptor) {
        return null;
    }
    
    private final okhttp3.OkHttpClient createHTPPClient(okhttp3.Interceptor interceptor) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/filmslibrary/model/dataSource/RetrofitImpl$Companion;", "", "()V", "BASE_LIST_OF_FILMS_URL", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}