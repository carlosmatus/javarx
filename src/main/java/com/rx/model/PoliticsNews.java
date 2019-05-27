package com.rx.model;

public class PoliticsNews implements News {
    private String news;

    public PoliticsNews(String news){
        this.news=news;
    }

   public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    @Override
    public Enum getNewsType() {
        return NewsType.POLITICS;
    }

    @Override
    public String toString() {
        return "PoliticsNews{" +
                "news='" + news + '\'' +
                '}';
    }
}
