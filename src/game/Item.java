package game;

import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;

public abstract class Item {
    protected Character player;
    protected BodyImage image;
    protected AttachedImage aImage;

    public Item(Character player){
        this.player = player;
        image = loadImage();
    }

    public abstract BodyImage loadImage();

    public abstract void action();

    public void putOn(){
        aImage = player.addImage(image);
        //if (player.getDirection){

        //}
    }

    public void takeOff(){

    }
}
