package com.books.shelf.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Book {


    @SerializedName("title")
        private String title;
    @SerializedName("isbn")
        private String isbn;
    @SerializedName("pageCount")
        private int pageCount;
    @SerializedName("publishedDate")
        private PublishedDate publishedDate;
    @SerializedName("thumbnailUrl")
        private String thumbnailUrl;
    @SerializedName("shortDescription")
        private String shortDescription;
    @SerializedName("longDescription")
        private String longDescription;
    @SerializedName("status")
        private String status;
    @SerializedName("authors")
        private List<String> authors;
    @SerializedName("categories")
        private List<String> categories;

        public void setTitle(String title){
        this.title = title;
    }
        public String getTitle(){
        return this.title;
    }
        public void setIsbn(String isbn){
        this.isbn = isbn;
    }
        public String getIsbn(){
        return this.isbn;
    }
        public void setPageCount(int pageCount){
        this.pageCount = pageCount;
    }
        public int getPageCount(){
        return this.pageCount;
    }
        public void setPublishedDate(PublishedDate publishedDate){
        this.publishedDate = publishedDate;
    }
        public PublishedDate getPublishedDate(){
        return this.publishedDate;
    }
        public void setThumbnailUrl(String thumbnailUrl){
        this.thumbnailUrl = thumbnailUrl;
    }
        public String getThumbnailUrl(){
        return this.thumbnailUrl;
    }
        public void setShortDescription(String shortDescription){
        this.shortDescription = shortDescription;
    }
        public String getShortDescription(){
        return this.shortDescription;
    }
        public void setLongDescription(String longDescription){
        this.longDescription = longDescription;
    }
        public String getLongDescription(){
        return this.longDescription;
    }
        public void setStatus(String status){
        this.status = status;
    }
        public String getStatus(){
        return this.status;
    }
        public void setAuthors(List<String> authors){
        this.authors = authors;
    }
        public List<String> getAuthors(){
        return this.authors;
    }
        public void setCategories(List<String> categories){
        this.categories = categories;
    }
        public List<String> getCategories(){
        return this.categories;
    }

}
