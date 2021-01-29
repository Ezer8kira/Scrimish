/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author LENOVO
 */
public class ButtonImageSet {
    private Button button;
    private ImageView imageView;
    private Card card;
    private boolean flipping = false;
    private boolean back = true;

    public ButtonImageSet(Button button, ImageView imageView, Card card) {
        this.button = button;
        this.imageView = imageView;
        this.card = card;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean isFlipping() {
        return flipping;
    }

    public void setFlipping(boolean flipping) {
        this.flipping = flipping;
    }

    public boolean isBack() {
        return back;
    }

    public void setBack(boolean back) {
        this.back = back;
    }

  
    
    
}
