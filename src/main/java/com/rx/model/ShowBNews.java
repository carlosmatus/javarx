package com.rx.model;

public class ShowBNews implements News {
    private String news;
    public ShowBNews(String news){
        this.news=news;
    }

    @Override
    public Enum getNewsType() {
        return NewsType.SHOWBUSINESS;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "ShowBNews{" +
                "news='" + news + '\'' +
                '}';
    }
}
