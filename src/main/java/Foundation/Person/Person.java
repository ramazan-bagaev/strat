package Foundation.Person;

import Foundation.Economics.Wallet;
import Foundation.Needs.Need;
import Foundation.Needs.Needs;
import Foundation.Products.EdibleProduct.EdibleProduct;
import Foundation.Works.Work;
import Utils.Broadcaster;
import Utils.Subscription;

public class Person implements Broadcaster{

    private Wallet wallet;
    private Society society;
    private String name;
    private Work work;

    private Kasta kasta;

    private boolean alive;


    private Needs essentialNeeds;
    private Needs comfortNeeds;
    private Needs luxuryNeeds;



    public enum Kasta{
        Low, Middle, High, Royal
    }

    public Person(String name, Society society, Kasta kasta){
        this.name = name;
        this.kasta = kasta;
        this.society = society;
        this.alive = true;
        this.wallet = new Wallet();
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

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public void kill() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    private void initNeeds(){
        essentialNeeds = new Needs();
        essentialNeeds.addNeed(new EdibleProduct(5) {});
    }

    public Wallet getWallet() {
        return wallet;
    }


    @Override
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
