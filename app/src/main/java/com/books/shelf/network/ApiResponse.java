package com.books.shelf.network;

import java.util.List;

public class ApiResponse {
    public List<Book> posts;
    private Throwable error;

    public ApiResponse(List<Book> posts) {
        this.posts = posts;
        this.error = null;
    }

    public ApiResponse(Throwable error) {
        this.error = error;
        this.posts = null;
    }

    public List<Book> getPosts() {
        return posts;
    }

    public void setPosts(List<Book> posts) {
        this.posts = posts;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
