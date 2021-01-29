/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entities.Arrow;
import Entities.ButtonImageSet;
import Entities.Card;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Line;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import static javafx.util.Duration.seconds;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class MainBattleController implements Initializable {

    @FXML
    AnchorPane anchorpane,loadingScreen;
    @FXML
    Pane gamepane,overlay;
    @FXML
    ImageView bg,mainCi,mainPi,p1i,p2i,p3i,p4i,p5i,c1i,c2i,c3i,c4i,c5i;
    @FXML
    Button mainC, mainP,p1,p2,p3,p4,p5,c1,c2,c3,c4,c5;

    List<Card> deck;
    List<Card> opponentDeck;
    
    List<Integer> boardCnumbers;
    List<Integer> boardPnumbers;
    
     List<Integer> boardCpositions;
    List<Integer> boardPpositions;
    
    double mouseX,mouseY;
    List<ButtonImageSet> boardP;
    List<ButtonImageSet> boardC;
    
    boolean winCard= false;
    boolean enemyWinCard= false;
    int chosenPosition = -1 ;
    int chosenEnemy = -1 ;
    boolean entringFrom = false;
    Arrow arrow;
    boolean allowDraw = true;
    boolean battleAnimation =false;
    int lost ;
    
    private Line deletedLine1 ;
    private Line deletedLine2 ;
    private Line deletedLine3;
    private Line deletedLine4;
    
    boolean animationOn =false;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         arrow = new Arrow();
        gamepane.getChildren().add(arrow);
        arrow.setVisible(false);
       
         Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(5000), e -> {
                 FadeTransition ftbg = new FadeTransition(Duration.millis(1000),loadingScreen);
                ftbg.setFromValue(1);
                ftbg.setToValue(0);
                ftbg.play();
                ftbg.setOnFinished(e3->{
                loadingScreen.setVisible(false);
                });
            })
        );
         //loadingScreen.setVisible(false);
        timeline.play();
    
        
//        deck = new ArrayList<Card>();
//          for(int i=0;i<25;i++)
//            deck.add(new Card("Archer","images/Archer.png",7));
//          
//         opponentDeck = new ArrayList<Card>();
//          for(int i=0;i<25;i++)
//            opponentDeck.add(new Card("Shield","images/shield.png",7));
        deck = new ArrayList<>(Scrimish.deck); 
        opponentDeck = new ArrayList<>(Scrimish.deck); 
//        
//        deck.set(4, new Card("Archer","images/Archer.png",7));
//        opponentDeck.set(9, new Card("Shield","images/shield.png",7));
        System.out.println("main deck :  " +deck);
           boardPpositions=  new ArrayList<Integer>(){{
              add(4);
              add(9);
              add(14);
              add(19);
              add(24);
                 }};
         
         boardCpositions =  new ArrayList<Integer>(){{
              add(4);
              add(9);
              add(14);
              add(19);
              add(24);
                 }};
          
        boardP = new ArrayList<ButtonImageSet>(){{
                add(new ButtonImageSet(p1,p1i,deck.get(boardPpositions.get(0))));
                add(new ButtonImageSet(p2,p2i,deck.get(boardPpositions.get(1))));
                add(new ButtonImageSet(p3,p3i,deck.get(boardPpositions.get(2))));
                add( new ButtonImageSet(p4,p4i,deck.get(boardPpositions.get(3))));
                add( new ButtonImageSet(p5,p5i,deck.get(boardPpositions.get(4))));
            }};
        
         boardC = new ArrayList<ButtonImageSet>(){{
                add(new ButtonImageSet(c1,c1i,opponentDeck.get(boardCpositions.get(0))));
                add(new ButtonImageSet(c2,c2i,opponentDeck.get(boardCpositions.get(1))));
                add(new ButtonImageSet(c3,c3i,opponentDeck.get(boardCpositions.get(2))));
                add( new ButtonImageSet(c4,c4i,opponentDeck.get(boardCpositions.get(3))));
                add( new ButtonImageSet(c5,c5i,opponentDeck.get(boardCpositions.get(4))));
            }};
         
         boardPnumbers =  new ArrayList<Integer>(){{
              add(5);
              add(5);
              add(5);
              add(5);
              add(5);
                 }};
         
         boardCnumbers =  new ArrayList<Integer>(){{
              add(5);
              add(5);
              add(5);
              add(5);
              add(5);
                 }};
        Random r = new Random();
        int rand = 1+r.nextInt(5);
       Scrimish.bg = new Image("images/bg"+rand+".png");
        bg.setImage(Scrimish.bg);
        
        p1.setOnMouseEntered(e->{
             enteringCard(0, p1, p1i);
        });
        
         p1.setOnMouseExited(e->{
             exitingCard(0, p1, p1i);
        });
         
          p2.setOnMouseEntered(e->{
             enteringCard(1, p2, p2i);
        });
        
         p2.setOnMouseExited(e->{
             exitingCard(1, p2, p2i);
             
        });
         
          p3.setOnMouseEntered(e->{
             enteringCard(2, p3, p3i);
        });
        
         p3.setOnMouseExited(e->{
             exitingCard(2, p3, p3i);
        });
         
          p4.setOnMouseEntered(e->{
             enteringCard(3, p4, p4i);
        });
        
         p4.setOnMouseExited(e->{
             exitingCard(3, p4, p4i);
        });
         
          p5.setOnMouseEntered(e->{
             enteringCard(4, p5, p5i);
        });
        
         p5.setOnMouseExited(e->{
             exitingCard(4, p5, p5i);
        });
         
         c1.setOnMouseEntered(e->{
           EntringEnemy(10,c1);
         });
          c1.setOnMouseExited(e->{
               allowDraw = true;
         arrow.getScene().setCursor(Cursor.DEFAULT);
         });
            c2.setOnMouseEntered(e->{
               
                EntringEnemy(10,c2);
         });
          c2.setOnMouseExited(e->{
               allowDraw = true;
         arrow.getScene().setCursor(Cursor.DEFAULT);
         });
            c3.setOnMouseEntered(e->{
               EntringEnemy(10,c3);
         });
          c3.setOnMouseExited(e->{
               allowDraw = true;
         arrow.getScene().setCursor(Cursor.DEFAULT);
         });
            c4.setOnMouseEntered(e->{
                EntringEnemy(10,c4);
         });
          c4.setOnMouseExited(e->{
              allowDraw = true;
         arrow.getScene().setCursor(Cursor.DEFAULT);
         });
            c5.setOnMouseEntered(e->{
                 EntringEnemy(10,c5);
         });
          c5.setOnMouseExited(e->{
               allowDraw = true;
         arrow.getScene().setCursor(Cursor.DEFAULT);
         });
        gamepane.setOnMouseMoved(e->{
        mouseX= e.getX();
        mouseY = e.getY();
        checkFlippedCards();
        });
          
         p1.setOnAction(e->{
            startingBattle(0,p1,p1i);
         });
          p2.setOnAction(e->{
            startingBattle(1,p2,p2i);
         });
           p3.setOnAction(e->{
            startingBattle(2,p3,p3i);
         });
            p4.setOnAction(e->{
            startingBattle(3,p4,p4i);
         });
             p5.setOnAction(e->{
            startingBattle(4,p5,p5i);
         });
             
             c1.setOnAction(e->{
             battlePhase(0,c1,c1i);
             });
         c2.setOnAction(e->{
             battlePhase(1,c2,c2i);
             });
         c3.setOnAction(e->{
             battlePhase(2,c3,c3i);
             });
         c4.setOnAction(e->{
             battlePhase(3,c4,c4i);
             });
         c5.setOnAction(e->{
             battlePhase(4,c5,c5i);
             });
             
        overlay.setOnMouseClicked(e->{
         
        if(!battleAnimation){
            System.out.println("Lost is : "+ lost);
             System.out.println("boardPPositions : " + boardPpositions);
             System.out.println("boardPnumbers : " + boardPnumbers);
            animationOn = true;
            if(deletedLine1 !=null && deletedLine2!= null){
            overlay.getChildren().remove(deletedLine1);
            overlay.getChildren().remove(deletedLine2);
             deletedLine1 = null;
            deletedLine2=null;
            }
            if(deletedLine3 !=null && deletedLine4!= null){
             overlay.getChildren().remove(deletedLine3);
            overlay.getChildren().remove(deletedLine4);
            deletedLine3 = null;
            deletedLine4 =null;
            }
            overlay.setVisible(false);
            mainCi.setImage(new Image("images/Back.png"));
            mainPi.setImage(new Image("images/Back.png"));
            arrow.setVisible(false);
            gamepane.setEffect(null);
            EventHandler<MouseEvent> handler = MouseEvent::consume;
            Scrimish.stage.getScene().addEventFilter(MouseEvent.ANY, handler);
               if(lost ==1){        
                resetEffect(chosenEnemy, true);
                FadeTransition ft = new FadeTransition(Duration.millis(600),boardC.get(chosenEnemy).getButton());
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.play();
                ft.setOnFinished(e3->{
                    FadeTransition ft2 = new FadeTransition();
                     if(boardCnumbers.get(chosenEnemy)==0){
                        boardC.get(chosenEnemy).getButton().setVisible(false);
                     }
                        boardC.get(chosenEnemy).getImageView().setImage(new Image("images/Back.png"));
                        ft2 = new FadeTransition(Duration.millis(600),boardC.get(chosenEnemy).getButton());
                        ft2.setFromValue(0);
                        ft2.setToValue(1);
                ft2.play();
                
                animationOn =false;
                int proxyEnemy = chosenEnemy;
                int proxyPosition = chosenPosition;
                 
                chosenPosition =-1;
                chosenEnemy =-1;
                 exitingCard(proxyPosition, boardP.get(proxyPosition).getButton(), boardP.get(proxyPosition).getImageView());
                ft2.setOnFinished(e4->{
                    
                //boardP.get(proxyPosition).getImageView().setImage(new Image(boardP.get(proxyPosition).getCard().getPicture()));
               
                if(boardCnumbers.get(proxyEnemy)>0)
                boardC.get(proxyEnemy).setCard(opponentDeck.get(boardCpositions.get(proxyEnemy)));
                Scrimish.stage.getScene().removeEventFilter(MouseEvent.ANY, handler);
                });
                });
               }
               else if(lost ==-1){
                resetEffect(chosenEnemy, false);
                FadeTransition ft = new FadeTransition(Duration.millis(600),boardP.get(chosenPosition).getButton());
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.play();
                ft.setOnFinished(e3->{
                    FadeTransition ft2 = new FadeTransition();
                     if(boardPnumbers.get(chosenPosition)==0){
                        boardP.get(chosenPosition).getButton().setVisible(false);
                     }
                        boardP.get(chosenPosition).getImageView().setImage(new Image("images/Back.png"));
                        ft2 = new FadeTransition(Duration.millis(600),boardP.get(chosenPosition).getButton());
                        ft2.setFromValue(0);
                        ft2.setToValue(1);
                        
                ft2.play();
                animationOn =false;
                int proxyEnemy = chosenEnemy;
                int proxyPosition = chosenPosition;
                
                
                chosenPosition =-1;
                chosenEnemy =-1;
                exitingEnemyCard(proxyEnemy, boardC.get(proxyEnemy).getButton(), boardC.get(proxyEnemy).getImageView());
                ft2.setOnFinished(e4->{
                     
                if(boardPnumbers.get(proxyPosition)>0){
                 boardP.get(proxyPosition).setCard(deck.get(boardPpositions.get(proxyPosition)));
                 boardP.get(proxyPosition).setBack(true);
                }
               
                Scrimish.stage.getScene().removeEventFilter(MouseEvent.ANY, handler);
                });
                });
               }else if(lost ==0){
                resetEffect(chosenEnemy, true);
                FadeTransition ft = new FadeTransition(Duration.millis(600),boardP.get(chosenPosition).getButton());
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.play();
                
                FadeTransition ft3 = new FadeTransition(Duration.millis(600),boardC.get(chosenEnemy).getButton());
                ft3.setFromValue(1);
                ft3.setToValue(0);
                ft3.play();
                
                ft.setOnFinished(e3->{
                    FadeTransition ft2 = new FadeTransition();
                    FadeTransition ft4 = new FadeTransition();
                     if(boardPnumbers.get(chosenPosition)==0){
                        boardP.get(chosenPosition).getButton().setVisible(false);
                     }
                     if(boardCnumbers.get(chosenEnemy)==0){
                        boardC.get(chosenEnemy).getButton().setVisible(false);
                     }
                        boardP.get(chosenPosition).getImageView().setImage(new Image("images/Back.png"));
                        ft2 = new FadeTransition(Duration.millis(600),boardP.get(chosenPosition).getButton());
                        ft2.setFromValue(0);
                        ft2.setToValue(1);
                        
                         boardC.get(chosenEnemy).getImageView().setImage(new Image("images/Back.png"));
                        ft4 = new FadeTransition(Duration.millis(600),boardC.get(chosenEnemy).getButton());
                        ft4.setFromValue(0);
                        ft4.setToValue(1);
                        
                ft2.play();
                ft4.play();
                animationOn =false;
                int proxyEnemy = chosenEnemy;
                int proxyPosition = chosenPosition;
                chosenPosition =-1;
                chosenEnemy =-1;
                ft2.setOnFinished(e4->{
                if(boardPnumbers.get(proxyPosition)>0){
                 boardP.get(proxyPosition).setCard(deck.get(boardPpositions.get(proxyPosition)));
                 boardP.get(proxyPosition).setBack(true);
                }
                
                if(boardCnumbers.get(proxyEnemy)>0){
                 boardC.get(proxyEnemy).setCard(opponentDeck.get(boardCpositions.get(proxyEnemy)));
                }
               
                Scrimish.stage.getScene().removeEventFilter(MouseEvent.ANY, handler);
                });
                });
               }else if(lost==2){
                    resetEffect(chosenEnemy, false);
               Scrimish.stage.getScene().removeEventFilter(MouseEvent.ANY, handler);
                boardP.get(chosenPosition).getImageView().setImage(new Image(boardP.get(chosenPosition).getCard().getPicture()));
                exitingEnemyCard(chosenEnemy, boardC.get(chosenEnemy).getButton(), boardC.get(chosenEnemy).getImageView());
                int proxyPosition = chosenPosition;
                chosenPosition =-1;
                exitingCard(proxyPosition, boardP.get(proxyPosition).getButton(), boardP.get(proxyPosition).getImageView());
                chosenEnemy =-1;
                animationOn =false;
                  
               }
                
        }
        });
    }    

    
    private RotateTransition createRotator(Node node, ImageView card,Image newImage, int millis,int scaleY,int scaleX) {
        node.setScaleY(scaleY);
        node.setScaleX(scaleX);
        RotateTransition rotator = new RotateTransition(Duration.millis(millis), node);
        
        
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(0);
        rotator.setToAngle(180);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);

        
                Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(millis/2), e -> {
                card.setImage(newImage);
            })
        );
        timeline.play();

        return rotator;
    }
    private void checkFlippedCards(){
    if(!(mouseX>=p1.getLayoutX()&& mouseX<=p1.getLayoutX()+p1.getWidth() && mouseY >=p1.getLayoutY() && mouseY<=p1.getLayoutY()+p1.getHeight()&& chosenPosition!=0)){
                exitingCard(0, p1, p1i);
                allowDraw=true;
    }
    if(!(mouseX>=p2.getLayoutX()&& mouseX<=p2.getLayoutX()+p2.getWidth() && mouseY >=p2.getLayoutY() && mouseY<=p2.getLayoutY()+p2.getHeight())&& chosenPosition!=1){
                exitingCard(1, p2, p2i);
                allowDraw=true;
     }
    if(!(mouseX>=p3.getLayoutX()&& mouseX<=p3.getLayoutX()+p3.getWidth() && mouseY >=p3.getLayoutY() && mouseY<=p3.getLayoutY()+p3.getHeight())&& chosenPosition!=2){
                exitingCard(2, p3, p3i);
                allowDraw=true;
    }
    if(!(mouseX>=p4.getLayoutX()&& mouseX<=p4.getLayoutX()+p4.getWidth() && mouseY >=p4.getLayoutY() && mouseY<=p4.getLayoutY()+p4.getHeight())&& chosenPosition!=3){
                exitingCard(3, p4, p4i);
                allowDraw=true;
    }
    if(!(mouseX>=p5.getLayoutX()&& mouseX<=p5.getLayoutX()+p5.getWidth() && mouseY >=p5.getLayoutY() && mouseY<=p5.getLayoutY()+p5.getHeight())&& chosenPosition!=4){
                exitingCard(4, p5, p5i);
                allowDraw=true;
    }
    }
    
    
    private void enteringCard(int position, Button p, ImageView pi){
        p.setCursor(Cursor.HAND);
     
        fixArrowEntering(position,p);
     if(!boardP.get(position).isFlipping() && boardP.get(position).isBack()){
                boardP.get(position).setFlipping(true);
                String link ="";
                if(position ==chosenPosition || entringFrom){
                link =boardP.get(position).getCard().getPicture().split("\\.")[0]+"s.png";
                
                }else{
                link = boardP.get(position).getCard().getPicture();
                }
                RotateTransition rotator = createRotator(p,pi,new Image(link),500,1,-1);
                rotator.play();
                rotator.setOnFinished(e2->{
                boardP.get(position).setFlipping(false);
                boardP.get(position).setBack(false);
                entringFrom = false;
                  allowDraw = true;
                });
            }
    }
    
    
    private void exitingCard(int position, Button p, ImageView pi){
         p.setCursor(Cursor.DEFAULT);
        if(position!=chosenPosition){
             if(!boardP.get(position).isFlipping() && !boardP.get(position).isBack()){
                  if(winCard){
                 pi.setImage(new Image("images/won.png"));
                 }
                boardP.get(position).setFlipping(true);
                RotateTransition rotator = createRotator(p,pi,new Image("images/Back.png"),500,1,1);
                rotator.play();
                rotator.setOnFinished(e2->{
                boardP.get(position).setFlipping(false);
                boardP.get(position).setBack(true);
                winCard = false;
                });
            }      
        }
    }
    
    private void enteringEnemyCard(int position, Button p, ImageView pi){
      
     if(!boardC.get(position).isFlipping() && boardC.get(position).isBack()){
                boardC.get(position).setFlipping(true);
                String link ="";
                link = boardC.get(position).getCard().getPicture();
                
                RotateTransition rotator = createRotator(p,pi,new Image(link),500,1,-1);
                rotator.play();
                rotator.setOnFinished(e2->{
                boardC.get(position).setFlipping(false);
                boardC.get(position).setBack(false);
                  allowDraw = true;
                });
            }
    }
    
    
    private void exitingEnemyCard(int position, Button p, ImageView pi){
        
        
             if(!boardC.get(position).isFlipping() && !boardC.get(position).isBack()){
                 if(enemyWinCard){
                 pi.setImage(new Image("images/won.png"));
                 }
                
                boardC.get(position).setFlipping(true);
                RotateTransition rotator = createRotator(p,pi,new Image("images/Back.png"),500,1,1);
                rotator.play();
                rotator.setOnFinished(e2->{
                boardC.get(position).setFlipping(false);
                boardC.get(position).setBack(true);
                enemyWinCard = false;
                });
            }      
        
    }
    
    
    
   
    private void startingBattle(int position,Button p,ImageView pi){
           p.setCursor(Cursor.CLOSED_HAND);
             Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(100), e2 -> {
               p.setCursor(Cursor.HAND);
            })
        );
        timeline.play();
        allowDraw = true;
    if(!boardP.get(position).isFlipping()){
    
        if(chosenPosition==position ){
                 chosenPosition =-1;
                 arrow.setVisible(false);
                 c1i.setEffect(null); 
                    c2i.setEffect(null); 
                    c3i.setEffect(null); 
                    c4i.setEffect(null); 
                    c5i.setEffect(null); 
                    c1.setEffect(null); 
                    c2.setEffect(null); 
                    c3.setEffect(null); 
                    c4.setEffect(null); 
                    c5.setEffect(null);
                    c1i.setImage(new Image("images/Back.png"));
                    c2i.setImage(new Image("images/Back.png"));
                    c3i.setImage(new Image("images/Back.png"));
                    c4i.setImage(new Image("images/Back.png"));
                    c5i.setImage(new Image("images/Back.png"));
                 exitingCard(position, p, pi);
                 
         }else{
            arrow.setVisible(true);
            Glow glow = new Glow(); 
            glow.setLevel(0.4); 
            
             DropShadow dropShadow = new DropShadow();
                dropShadow.setRadius(8.0);
                dropShadow.setOffsetX(-5.0);
                dropShadow.setOffsetY(-5.0);
                dropShadow.setColor(Color.ORANGE);  
 
                  c1.setEffect(dropShadow); 
            c2.setEffect(dropShadow); 
            c3.setEffect(dropShadow); 
            c4.setEffect(dropShadow); 
            c5.setEffect(dropShadow); 
            
            c1i.setEffect(glow); 
            c2i.setEffect(glow); 
            c3i.setEffect(glow); 
            c4i.setEffect(glow); 
            c5i.setEffect(glow); 
            
           
          c1i.setImage(new Image("images/backs.png"));
          c2i.setImage(new Image("images/backs.png"));
          c3i.setImage(new Image("images/backs.png"));
          c4i.setImage(new Image("images/backs.png"));
          c5i.setImage(new Image("images/backs.png"));
          if(chosenPosition!=-1){
              chosenPosition = position;
          }
                        
          switch(position) {
                    case 0:
                        exitingCard(1, p2, p2i);
                        exitingCard(2, p3, p3i);
                        exitingCard(3, p4, p4i);
                        exitingCard(4, p5, p5i);
                      break;
                    case 1:
                        exitingCard(0, p1, p1i);
                        exitingCard(2, p3, p3i);
                        exitingCard(3, p4, p4i);
                        exitingCard(4, p5, p5i);
                        break;
                     case 2:
                        exitingCard(1, p2, p2i);
                        exitingCard(0, p1, p1i);
                        exitingCard(3, p4, p4i);
                        exitingCard(4, p5, p5i);
                        break;
                     case 3:
                        exitingCard(1, p2, p2i);
                        exitingCard(2, p3, p3i);
                        exitingCard(0, p1, p1i);
                        exitingCard(4, p5, p5i);
                        break;
                     case 4:
                        exitingCard(1, p2, p2i);
                        exitingCard(2, p3, p3i);
                        exitingCard(3, p4, p4i);
                        exitingCard(0, p1, p1i);
                      break;
                  }
          
             
                 if(boardP.get(position).isBack()){
                     entringFrom = true;
                     enteringCard(position, p, pi);
                 }else{
                     boardP.get(position).getImageView().setImage(new Image(boardP.get(position).getCard().getPicture().split("\\.")[0]+"s.png"));
                 }
                 chosenPosition =position;
                 
                arrow.setStartX(p.getLayoutX()+(p.getWidth()/2));
                arrow.setStartY(p.getLayoutY()-5);
                arrow.setEndX(p.getLayoutX()+(p.getWidth()/2));
                arrow.setEndY(p.getLayoutY()-20);
                
               gamepane.setOnMouseMoved(e3->{
                    if(!boardP.get(position).isFlipping() && allowDraw){
                    checkFlippedCards();
                    if(!boardP.get(position).isFlipping()){
                     arrow.setEndX(e3.getX()-10);
                     arrow.setEndY(e3.getY()+20);
                    }
                    }
                    });
                
               
             }
    
    }
      }
    
    private void fixArrowEntering(int position, Button p){
        if(position!=chosenPosition){
              allowDraw = false;
        arrow.setEndX(p.getLayoutX()+p.getWidth()/2);
        arrow.setEndY(p.getLayoutY());
        }
    }
     private void fixArrowEnteringEnemy(int position, Button p){
        if(position!=chosenPosition){
              allowDraw = false;
        arrow.setEndX(p.getLayoutX()+p.getWidth()/2);
        arrow.setEndY(p.getLayoutY()+p.getHeight());
        }
     }
    private void battlePhase(int position, Button c, ImageView ci){
        System.out.println("Battle Phase !");
        if(chosenPosition!=-1){
            allowDraw = false;
            battleAnimation = true;
               chosenEnemy = position;
               overlay.setVisible(true);
                GaussianBlur blur = new GaussianBlur();
                blur.setRadius(10.5);
                gamepane.setEffect(blur);

               
                FadeTransition ft = new FadeTransition(Duration.millis(300),mainP);
                FadeTransition ft2 = new FadeTransition(Duration.millis(600),mainC);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
                ft2.setFromValue(0);
                ft2.setToValue(1);
                ft2.play();
            ft2.setOnFinished(e->{
                 Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(400), e2 -> {
                 RotateTransition rotator = createRotator(mainP,mainPi,new Image(boardP.get(chosenPosition).getCard().getPicture()),800,1,-1);
              rotator.play();
              RotateTransition rotator2 = createRotator(mainC,mainCi,new Image(boardC.get(chosenEnemy).getCard().getPicture()),1200,1,-1);
              rotator2.play();
              rotator2.setOnFinished(e3->{
              //ci.setImage(new Image(boardC.get(chosenEnemy).getCard().getPicture()));
                  enteringEnemyCard(chosenEnemy, c, ci);
                  if(boardP.get(chosenPosition).getCard().compareTo(boardC.get(chosenEnemy).getCard())==1){
                      System.out.println("Comparing 1 is true");
                    deleted(mainC,false);
                    winCard= true;
                    boardCnumbers.set(chosenEnemy, boardCnumbers.get(chosenEnemy)-1);
                    if(boardCnumbers.get(chosenEnemy)>0){
                    boardCpositions.set(chosenEnemy, boardCpositions.get(chosenEnemy)-1);
                    }
                    
                    lost =1;
                  }else if(boardP.get(chosenPosition).getCard().compareTo(boardC.get(chosenEnemy).getCard())==-1){
                      System.out.println("Comparing 2 is true");
                     deleted(mainP,false);
                     enemyWinCard = true;
                     boardPnumbers.set(chosenPosition, boardPnumbers.get(chosenPosition)-1);
                    if(boardPnumbers.get(chosenPosition)>0){
                    boardPpositions.set(chosenPosition, boardPpositions.get(chosenPosition)-1);
                    }
                    
                    lost =-1;
                  }else if(boardP.get(chosenPosition).getCard().compareTo(boardC.get(chosenEnemy).getCard())==0){
                      System.out.println("Comparing 3 is true");
                    deleted(mainC,false);
                    deleted(mainP,true);
                    
                    boardCnumbers.set(chosenEnemy, boardCnumbers.get(chosenEnemy)-1);
                    if(boardCnumbers.get(chosenEnemy)>0){
                    boardCpositions.set(chosenEnemy, boardCpositions.get(chosenEnemy)-1);
                    }
                    
                      boardPnumbers.set(chosenPosition, boardPnumbers.get(chosenPosition)-1);
                    if(boardPnumbers.get(chosenPosition)>0){
                    boardPpositions.set(chosenPosition, boardPpositions.get(chosenPosition)-1);
                    }
                    
                    lost =0;
                  }
                  else if(boardP.get(chosenPosition).getCard().compareTo(boardC.get(chosenEnemy).getCard())==2){
                    lost =2;
                    battleAnimation = false;
                     }
                  if(boardP.get(chosenPosition).getCard().getName()=="King" && lost==-1)
                      System.out.println("You lose!");
                  if(boardC.get(chosenEnemy).getCard().getName()=="King" && lost==1)
                      System.out.println("You win!");
              });
            })
                );
                timeline.play();
            
            });
            
        
              
        
        } 
    }
    
    private void EntringEnemy(int position,Button b){
      if(chosenPosition!=-1){
              fixArrowEnteringEnemy(position,b);
              arrow.getScene().setCursor(new ImageCursor(new Image("images/attack_cursor.png")));
             }
    }
    
    public void deleted( Button b, boolean secondCross){
         Line line1 = new Line();
               Line line2 = new Line();
               if(!secondCross){
                     deletedLine1 = line1;
               deletedLine2 = line2;
               }else{
                    deletedLine3 = line1;
               deletedLine4 = line2;
               }
             
                  line1.setOpacity(0);
                   line2.setOpacity(0);
    line1.setStrokeWidth(5);
                  line2.setStrokeWidth(5);
                  line1.setStroke(Color.RED);
                  line2.setStroke(Color.RED);
                  
                  overlay.getChildren().add(line1);
                   overlay.getChildren().add(line2);
                   
                   line1.setStartX(b.getLayoutX());
                   line1.setStartY(b.getLayoutY());
                   line1.setEndX(b.getLayoutX()+b.getWidth());
                   line1.setEndY(b.getLayoutY()+b.getHeight());
                
                
                    line2.setStartX(b.getLayoutX()+b.getWidth());
                   line2.setStartY(b.getLayoutY());
                   line2.setEndX(b.getLayoutX());
                   line2.setEndY(b.getLayoutY()+b.getHeight());
                   
                    FadeTransition ft1 = new FadeTransition(Duration.millis(300),line1);
                    ft1.setFromValue(0);
                    ft1.setToValue(1);
                    ft1.play();
                    
                    ft1.setOnFinished(e->{
                     FadeTransition ft2 = new FadeTransition(Duration.millis(300),line2);
                    ft2.setFromValue(0);
                    ft2.setToValue(1);
                    ft2.play();
                    ft2.setOnFinished(e3->{
                      TranslateTransition tt = new TranslateTransition(Duration.millis(50), b);
                        tt.setByX(20f);
                        tt.setByY(10f);
                        tt.setCycleCount(4);
                        tt.setAutoReverse(true);
                        tt.playFromStart();
                        tt.setOnFinished(e4->{
                        battleAnimation = false;
                        });
                    });
                    });
                   
                    
    }
    private void resetEffect(int position, boolean enemylost){
        
       switch(position) {
                    case 0:
                        if(!enemylost){
                         //c1i.setImage(new Image("images/Back.png"));   
                        }
                    c2i.setImage(new Image("images/Back.png"));
                    c3i.setImage(new Image("images/Back.png"));
                    c4i.setImage(new Image("images/Back.png"));
                    c5i.setImage(new Image("images/Back.png"));
                      break;
                    case 1:
                        if(!enemylost){
                         //c2i.setImage(new Image("images/Back.png"));   
                        }
                           c1i.setImage(new Image("images/Back.png"));
                    
                    c3i.setImage(new Image("images/Back.png"));
                    c4i.setImage(new Image("images/Back.png"));
                    c5i.setImage(new Image("images/Back.png"));
                        break;
                     case 2:
                         if(!enemylost){
                         //c3i.setImage(new Image("images/Back.png"));   
                        }
                          c1i.setImage(new Image("images/Back.png"));
                    c2i.setImage(new Image("images/Back.png"));
                    c4i.setImage(new Image("images/Back.png"));
                    c5i.setImage(new Image("images/Back.png"));
                        break;
                     case 3:
                         if(!enemylost){
                         //c4i.setImage(new Image("images/Back.png"));   
                        }
                             c1i.setImage(new Image("images/Back.png"));
                    c2i.setImage(new Image("images/Back.png"));
                    c3i.setImage(new Image("images/Back.png"));
                    c5i.setImage(new Image("images/Back.png"));
                        break;
                     case 4:
                         if(!enemylost){
                         //c5i.setImage(new Image("images/Back.png"));   
                        }
                            c1i.setImage(new Image("images/Back.png"));
                    c2i.setImage(new Image("images/Back.png"));
                    c3i.setImage(new Image("images/Back.png"));
                    c4i.setImage(new Image("images/Back.png"));
                      break;
                  }
    c1i.setEffect(null); 
    c2i.setEffect(null); 
    c3i.setEffect(null); 
    c4i.setEffect(null); 
    c5i.setEffect(null); 
    c1.setEffect(null); 
    c2.setEffect(null); 
    c3.setEffect(null); 
    c4.setEffect(null); 
    c5.setEffect(null);
    }
}
