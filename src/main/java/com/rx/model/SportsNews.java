package com.rx.model;

public class SportsNews implements News {
    private String news;

    public SportsNews(String news){
        this.news=news;
    }

    @Override
    public Enum getNewsType() {
        return NewsType.SPORTS;
    }
    @Override
    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "SportsNews{" +
                "news='" + news + '\'' +
                '}';
    }
}
