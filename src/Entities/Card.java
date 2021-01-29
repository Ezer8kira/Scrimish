/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author Asus
 */
public class Card {
    private String name;
    private String picture;
    private int power;
    private boolean life;

    public Card(String name, String picture, int power) {
        this.name = name;
        this.picture = picture;
        this.power = power;
        this.life= true;
    }
    
    public Card(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isLife() {
        return life;
    }

    public void setLife(boolean life) {
        this.life = life;
    }

  
    public int compareTo(Card c) {
        System.out.println(this +"VS" + c+ " START !");
     if (c.getName().equals("Shield"))
     { 
         if (this.getName().equals("Archer"))
                return 2;
               return 0;
     }else if(c.getName().equals("Archer")){
        if (this.getName().equals("King"))
            return -1;
        return 1;
     }else if(c.getName().equals("King")&&this.getName().equals("King"))
         return 1;
     else if(this.getPower()>c.getPower())
         return 1;
     else if(this.getPower()<c.getPower())
         return -1;
      else if(this.getPower()==c.getPower() && !this.getName().equals("Archer"))     
     return 0; 
        
     return -10;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        return true;
    }
    
    

    @Override
    public String toString() {
        return "Card{" + "name=" + name + ", picture=" + picture + ", power=" + power + ", life=" + life + '}';
    }
    
    
    
}
