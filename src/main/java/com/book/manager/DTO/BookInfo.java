package com.book.manager.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookInfo {

    private String title;
    private String img;
    private String author;
    private String isbn;
    private String isbn10;
    private String publisher;
    private String pubdate;
    private String pubplace;
    private String cip;
    private String pages;
    private String price;
    private String binding;
    private String keyword;
    private String edition;
    private String impression;
    private String language;
    private String format;
    @JsonProperty("class")
    private String classId;
    private String summary;

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getPubplace() {
        return pubplace;
    }

    public void setPubplace(String pubplace) {
        this.pubplace = pubplace;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    //...

    // Nested Data class to match JSON structure
    public static class BookData {
        private BookInfo data;

        public BookInfo getData() {
            return data;
        }

        public void setData(BookInfo data) {
            this.data = data;
        }
    }

    @JsonProperty("data")
    private BookData bookData;

    public BookData getBookData() {
        return bookData;
    }

    public void setBookData(BookData bookData) {
        this.bookData = bookData;
    }
}
