package Foundation.Person;

import Utils.Broadcaster;
import Foundation.WorksP.Work;
import Utils.Subscription;

public class Person implements Broadcaster{

    private Society society;
    private String name;
    private Work work;

    private Kasta kasta;

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public enum Kasta{
        Low, Middle, High, Royal
    }

    public Person(String name, Society society, Kasta kasta){
        this.name = name;
        this.kasta = kasta;
        this.society = society;
    }



    public String getName() {
        return name;
    }


    public Kasta getKasta() {
        return kasta;
    }



    public Society getSociety() {
        return society;
    }

    public void setSociety(Society society){
        this.society = society;
    }



    public String getValue(String key) {
        switch (key){
            case "name":
                return name;
            case "kasta":
                switch (kasta){

                    case Low:
                        return "low";
                    case Middle:
                        return "middle";
                    case High:
                        return "high";
                    case Royal:
                        return "royal";
                }
        }
        return noResult;
    }

    @Override
    public void subscribe(String key, Subscription subscription) {

    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {

    }
}
