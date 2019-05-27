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
        News sportsNews1 = new SportsNews("Noticia Deportes 2");
        News polNews2 = new PoliticsNews("Noticia Politica 2");

        ArrayList<News> newsPool = new ArrayList<>();
        newsPool.add(polNews);
        newsPool.add(showBNews);
        newsPool.add(sportsNews);
        newsPool.add(sportsNews1);
        newsPool.add(polNews2);

        Observable obs = Observable.from(newsPool);


        Subscriber espectSubscriber = getSubscriber(NewsType.SHOWBUSINESS);
        obs.subscribe(espectSubscriber);
        Subscriber polSubscriber = getSubscriber(NewsType.POLITICS);
        obs.subscribe(polSubscriber);
        Subscriber deportSubscriber = getSubscriber(NewsType.SPORTS);
        obs.subscribe(getSubscriber(NewsType.SPORTS));
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

                if(Arrays.toString(nType).indexOf(NewsType.POLITICS.toString())>-1 && news.getNewsType().compareTo(NewsType.SHOWBUSINESS)==0)
                {
                    System.out.println("Unsubscribe tipo suscriptor "+Arrays.toString(nType) +" al recibir Noticia :"+news.getNewsType());
                    this.unsubscribe();
                    return;
                }

                if(Arrays.toString(nType).indexOf(news.getNewsType().toString())>-1) {

                    System.out.println("Tipo Subscriber " + Arrays.toString(nType) + "  Tipo de noticias " + news);

                }else{
                    System.out.println("No esta suscrito a este tipo de noticias, Tipo Suscriptor:"+Arrays.toString(nType)+"Tipo Noticias Recibido : "+news.getNews().toString());
                }
            }
        };
    }

}
