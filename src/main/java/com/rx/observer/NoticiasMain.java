package com.rx.observer;

import com.rx.model.*;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoticiasMain {
    public static void main(String[] args) {
         News polNews = new PoliticsNews("Noticia Politica ");
        News sportsNews = new SportsNews("Noticia Deportes");
        News showBNews = new ShowBNews("Noticia Espectaculos");
        ArrayList<News> newsPool = new ArrayList<>();
        newsPool.add(polNews);
        newsPool.add(showBNews);
        newsPool.add(sportsNews);

        Observable obs = Observable.from(newsPool);


        Subscriber espectSubscriber = getSubscriber(NewsType.SHOWBUSINESS);
        obs.subscribe(espectSubscriber);
        Subscriber polSubscriber = getSubscriber(NewsType.POLITICS);
        obs.subscribe(polSubscriber);
        Subscriber deportSubscriber = getSubscriber(NewsType.SPORTS);
        obs.subscribe(deportSubscriber);
        obs.subscribe(getSubscriber(NewsType.SHOWBUSINESS,NewsType.POLITICS));
        obs.subscribe(getSubscriber(NewsType.POLITICS,NewsType.SPORTS,NewsType.SHOWBUSINESS));
    }

    public static Subscriber<News> getSubscriber(final NewsType...nType){
        return new Subscriber<News>(){

            @Override
            public void onCompleted() {
                System.out.println("Completado");

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Algo Salio Mal "+throwable);
            }

            @Override
            public void onNext(News news) {
                List<NewsType> lst = Arrays.asList(nType);
                if(lst.stream().filter(type-> type.compareTo(NewsType.POLITICS)==0 ).findAny().orElse(null)!=null && news.getNewsType().compareTo(NewsType.SHOWBUSINESS)==0)
                    this.unsubscribe();
                if(lst.stream().filter(type-> type==news.getNewsType()).findAny().orElse(null)!=null)
                System.out.println("Tipo Subscriber "+nType+"  Tipo de noticias "+news);

            }
        };
    }

}
