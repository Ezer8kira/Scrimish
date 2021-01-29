/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entities.Card;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class MainFXMLController implements Initializable {
    @FXML
    private Button star,selectdagger,selectsword,selectlong,selectmorning,selectharber,selectking,selectarcher,selectaxe,selectshield,auto,battlestart;
    @FXML
    private ImageView mazel,bg,selectbg,start,selectdaggeri,selectswordi,selectlongi,selectmorningi,selectharberi,selectkingi,selectarcheri,selectaxei,selectshieldi,blackbg;
    @FXML
    private Pane mainPane,deckpane,selectpane,gamepane;
    @FXML
    private Button c11,c12,c13,c14,c15,c21,c22,c23,c24,c25,c31,c32,c33,c34,c35,c41,c42,c43,c44,c45,c51,c52,c53,c54,c55;
    @FXML
    private ImageView c11i,c12i,c13i,c14i,c15i,c21i,c22i,c23i,c24i,c25i,c31i,c32i,c33i,c34i,c35i,c41i,c42i,c43i,c44i,c45i,c51i,c52i,c53i,c54i,c55i;
    @FXML
    private Label n1,n2,n3,n4,n5,n6,n7,n8,n9;
    
    private List<Button> deckb;
    private List<ImageView> decki;
    private List<Card> deck;
    private List<Card> cards;
    private List<Integer> indexes;
    private Card selectedCard ;
    private MediaPlayer mediaPlayer;
    private Map<String,Integer> maxCardList;
    private Map<String,Integer> NameToPower;
     private Map<String,String> NameToLinkList;
    private int total = 0;
    private boolean cardFromsSide = false;
    private int selectedCardNumber;
    private ImageView selectedImageView;
    Map<String,Integer> maxCopy ;
    Map<String,Integer> originalMaxCopy ;
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          cards = new ArrayList<Card>(){{
                add(new Card("Dagger","images/dagger.png",1));
                add(new Card("Sword","images/sword.png",2));
                add(new Card("Morning Star","images/morningstar.png",3));
                add(new Card("War Axe","images/waraxe.png",4));
                add(new Card("Halberd","images/halberd.png",5));
                add(new Card("Long Sword","images/Longsword.png",6));
                add(new Card("Archer","images/Archer.png",7));
                add(new Card("Shield","images/shield.png",7));
                add(new Card("King","images/king.png",0));
            }};
           indexes = new ArrayList<Integer>(){{
                add(0);
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
                add(6);
                add(7);
                add(8);
            }};
          maxCardList = new HashMap(){{
                put("Dagger", 5);
                put("Sword", 5);
                put("Morning Star", 3);
                put("War Axe", 3);
                put("Halberd", 2);
                put("Long Sword", 2);
                put("Archer", 2);
                put("Shield", 2);
                put("King", 1);
            }};
          NameToLinkList = new HashMap(){{
                put("Dagger","dagger.png");
                put("Sword", "sword.png");
                put("Morning Star", "morningstar.png");
                put("War Axe", "waraxe.png");
                put("Halberd", "halberd.png");
                put("Long Sword", "Longsword.png");
                put("Archer", "Archer.png");
                put("Shield", "shield.png");
                put("King", "king.png");
            }};
            NameToPower = new HashMap(){{
                put("Dagger",1);
                put("Sword", 2);
                put("Morning Star", 3);
                put("War Axe", 4);
                put("Halberd", 5);
                put("Long Sword", 6);
                put("Archer",7);
                put("Shield", 7);
                put("King", 0);
            }};
            
            for( Node node: getAllNodes(mainPane)) {

                    if( node instanceof Button) {
                       node.setCursor(Cursor.HAND);
                    }

                }
          deckb = new ArrayList<Button>(Arrays.asList(c11,c12,c13,c14,c15,c21,c22,c23,c24,c25,c31,c32,c33,c34,c35,c41,c42,c43,c44,c45,c51,c52,c53,c54,c55));
          decki = new ArrayList<ImageView>(Arrays.asList(c11i,c12i,c13i,c14i,c15i,c21i,c22i,c23i,c24i,c25i,c31i,c32i,c33i,c34i,c35i,c41i,c42i,c43i,c44i,c45i,c51i,c52i,c53i,c54i,c55i));
          deck = new ArrayList<Card>();
          for(int i=0;i<25;i++)
              deck.add(null);
             
          originalMaxCopy  = new HashMap<String,Integer>(maxCardList);
          maxCopy  = new HashMap<String,Integer>(maxCardList);
           
            n1.setText(maxCardList.get("Dagger")+"");
            n2.setText(maxCardList.get("Sword")+"");
            n3.setText(maxCardList.get("Morning Star")+"");
            n4.setText(maxCardList.get("War Axe")+"");
            n5.setText(maxCardList.get("Halberd")+"");
            n6.setText(maxCardList.get("Long Sword")+"");
            n7.setText(maxCardList.get("Archer")+"");
            n8.setText(maxCardList.get("Shield")+"");
            n9.setText(maxCardList.get("King")+"");
      
          ImageView copy = new ImageView();
        copy.setOpacity(0.5);
        
        
                final URL resource1 = getClass().getResource("musiques/102-hometown-sunshine.mp3");
     Media media1 = new Media(resource1.toString());
     mediaPlayer = new MediaPlayer(media1); 
     mediaPlayer.play();
     mediaPlayer.setOnEndOfMedia(new Runnable() {
       public void run() {
         mediaPlayer.seek(Duration.ZERO);
       }
   });
         selectpane.getChildren().add(copy);
  
        star.setStyle("-fx-background-color: transparent;");
         battlestart.setStyle("-fx-background-color: transparent;");
selectarcher.setStyle("-fx-background-color: transparent;");
selectaxe.setStyle("-fx-background-color: transparent;");
selectdagger.setStyle("-fx-background-color: transparent;");
selectharber.setStyle("-fx-background-color: transparent;");
selectking.setStyle("-fx-background-color: transparent;");
selectlong.setStyle("-fx-background-color: transparent;");
selectmorning.setStyle("-fx-background-color: transparent;");
selectshield.setStyle("-fx-background-color: transparent;");
selectsword.setStyle("-fx-background-color: transparent;");

for(int i =0 ; i<deckb.size();i++){
deckb.get(i).setStyle("-fx-background-color: transparent;");
}


c11.setOnAction(e->{
    if(selectedCard == null){
        getCard(0,c11,copy,c11i);
    }
    else{
          setCard(0,c11i, copy);
    }
     
  
   
});
c12.setOnAction(e->{
   if(selectedCard == null){
        getCard(1,c12,copy,c12i);
    }
    else{
          setCard(1,c12i, copy);
    }
});
c13.setOnAction(e->{
   if(selectedCard == null){
        getCard(2,c13,copy,c13i);
    }
    else{
          setCard(2,c13i, copy);
    }
});
c14.setOnAction(e->{
   if(selectedCard == null){
        getCard(3,c14,copy,c14i);
    }
    else{
          setCard(3,c14i, copy);
    }
});
c15.setOnAction(e->{
  if(selectedCard == null){
        getCard(4,c15,copy,c15i);
    }
    else{
          setCard(4,c15i, copy);
    }
});

c21.setOnAction(e->{
   if(selectedCard == null){
        getCard(5,c21,copy,c21i);
    }
    else{
          setCard(5,c21i, copy);
    }
});
c22.setOnAction(e->{
   if(selectedCard == null){
        getCard(6,c22,copy,c22i);
    }
    else{
          setCard(6,c22i, copy);
    }
});
c23.setOnAction(e->{
   if(selectedCard == null){
        getCard(7,c23,copy,c23i);
    }
    else{
          setCard(7,c23i, copy);
    }
});
c24.setOnAction(e->{
  if(selectedCard == null){
        getCard(8,c24,copy,c24i);
    }
    else{
          setCard(8,c24i, copy);
    }
});
c25.setOnAction(e->{
  if(selectedCard == null){
        getCard(9,c25,copy,c25i);
    }
    else{
          setCard(9,c25i, copy);
    }
});

c31.setOnAction(e->{
  if(selectedCard == null){
        getCard(10,c31,copy,c31i);
    }
    else{
          setCard(10,c31i, copy);
    }
});
c32.setOnAction(e->{
   if(selectedCard == null){
        getCard(11,c32,copy,c32i);
    }
    else{
          setCard(11,c32i, copy);
    }
});
c33.setOnAction(e->{
  if(selectedCard == null){
        getCard(12,c33,copy,c33i);
    }
    else{
          setCard(12,c33i, copy);
    }
});
c34.setOnAction(e->{
  if(selectedCard == null){
        getCard(13,c34,copy,c34i);
    }
    else{
          setCard(13,c34i, copy);
    }
});
c35.setOnAction(e->{
  if(selectedCard == null){
        getCard(14,c35,copy,c35i);
    }
    else{
          setCard(14,c35i, copy);
    }
});

c41.setOnAction(e->{
  if(selectedCard == null){
        getCard(15,c41,copy,c41i);
    }
    else{
          setCard(15,c41i, copy);
    }
});
c42.setOnAction(e->{
   if(selectedCard == null){
        getCard(16,c42,copy,c42i);
    }
    else{
          setCard(16,c42i, copy);
    }
});
c43.setOnAction(e->{
  if(selectedCard == null){
        getCard(17,c43,copy,c43i);
    }
    else{
          setCard(17,c43i, copy);
    }
});
c44.setOnAction(e->{
  if(selectedCard == null){
        getCard(18,c44,copy,c44i);
    }
    else{
          setCard(18,c44i, copy);
    }
});
c45.setOnAction(e->{
  if(selectedCard == null){
        getCard(19,c45,copy,c45i);
    }
    else{
          setCard(19,c45i, copy);
    }
});

c51.setOnAction(e->{
   if(selectedCard == null){
        getCard(20,c51,copy,c51i);
    }
    else{
          setCard(20,c51i, copy);
    }
});
c52.setOnAction(e->{
   if(selectedCard == null){
        getCard(21,c52,copy,c52i);
    }
    else{
          setCard(21,c52i, copy);
    }
});
c53.setOnAction(e->{
    
  if(selectedCard == null){
        getCard(22,c53,copy,c53i);
    }
    else{
          setCard(22,c53i, copy);
    }
});
c54.setOnAction(e->{
   if(selectedCard == null){
        getCard(23,c54,copy,c54i);
    }
    else{
          setCard(23,c54i, copy);
    }
});
c55.setOnAction(e->{
  if(selectedCard == null){
        getCard(24,c55,copy,c55i);
    }
    else{
          setCard(24,c55i, copy);
    }
});

auto.setOnAction(e->{
      
 randomize();
});


battlestart.setOnAction(e->{
    Scrimish.deck = this.deck;
    battlestart.setDisable(true);
           mediaPlayer.pause();
            final URL resource = getClass().getResource("musiques/Atelier Iris 3 Grand Phantasm Ost Dance of The Spirit Extended.mp3");
     Media media = new Media(resource.toString());
      mediaPlayer = new MediaPlayer(media); 
      
      
     mediaPlayer.setOnEndOfMedia(new Runnable() {
       public void run() {
         mediaPlayer.seek(Duration.ZERO);
       }
   });
    mediaPlayer.play();
    blackbg.setVisible(true);
    FadeTransition ftbg = new FadeTransition(Duration.millis(2000),blackbg);
                ftbg.setFromValue(0);
                ftbg.setToValue(1);
                ftbg.play();
    ftbg.setOnFinished(e2->{
    try {
                   Parent root = FXMLLoader.load(getClass().getResource("MainBattle.fxml"));
                  Scene scene = new Scene(root);
        
                Scrimish.stage.setScene(scene);
                Scrimish.stage.show();
              } catch (IOException ex) {
                  Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
              }
    });
              
        
        

});
selectpane.setOnMouseMoved(e->{
                    copy.setLayoutX(e.getX());
                    copy.setLayoutY(e.getY());
                });

        star.setOnAction(a->{
             Random r = new Random();
        int rand = 1+r.nextInt(5);
       bg.setImage(new Image("images/bg"+rand+".png"));
       Scrimish.bg = new Image("images/bg"+rand+".png");
       mediaPlayer.pause();
            final URL resource = getClass().getResource("musiques/104-about-worlds-and-legends-part-two-.mp3");
     Media media = new Media(resource.toString());
      mediaPlayer = new MediaPlayer(media); 
     mediaPlayer.setOnEndOfMedia(new Runnable() {
       public void run() {
         mediaPlayer.seek(Duration.ZERO);
       }
   });
    mediaPlayer.play();
         star.setVisible(false);
         gamepane.setVisible(false);
         selectpane.setVisible(true);
         
          FadeTransition ft = new FadeTransition(Duration.millis(500),selectdagger);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
            ft.setOnFinished(b->{
             FadeTransition ft2 = new FadeTransition(Duration.millis(500),selectsword);
            ft2.setFromValue(0);
            ft2.setToValue(1);
            ft2.play();
            ft2.setOnFinished(c->{FadeTransition ft3 = new FadeTransition(Duration.millis(500),selectmorning);
            ft3.setFromValue(0);
            ft3.setToValue(1);
            ft3.play();
            ft3.setOnFinished(d->{FadeTransition ft4 = new FadeTransition(Duration.millis(500),selectaxe);
            ft4.setFromValue(0);
            ft4.setToValue(1);
            ft4.play();
             ft4.setOnFinished(e->{FadeTransition ft5 = new FadeTransition(Duration.millis(500),selectharber);
            ft5.setFromValue(0);
            ft5.setToValue(1);
            ft5.play();
             ft5.setOnFinished(f->{FadeTransition ft6 = new FadeTransition(Duration.millis(500),selectlong);
            ft6.setFromValue(0);
            ft6.setToValue(1);
            ft6.play();
             ft6.setOnFinished(p->{FadeTransition ft7 = new FadeTransition(Duration.millis(500),selectarcher);
            ft7.setFromValue(0);
            ft7.setToValue(1);
            ft7.play();
             ft7.setOnFinished(j->{FadeTransition ft8 = new FadeTransition(Duration.millis(500),selectshield);
            ft8.setFromValue(0);
            ft8.setToValue(1);
            ft8.play();
             ft8.setOnFinished(k->{FadeTransition ft9 = new FadeTransition(Duration.millis(500),selectking);
            ft9.setFromValue(0);
            ft9.setToValue(1);
            ft9.play();
            ft9.setOnFinished(l->{
                FadeTransition ft12 = new FadeTransition(Duration.millis(500),mazel);
                FadeTransition ft10 = new FadeTransition(Duration.millis(500),selectbg);
                
            ft10.setFromValue(0);
            ft10.setToValue(1);
            ft12.setFromValue(0);
            ft12.setToValue(1);
            ft12.play();
            ft10.play();
         
            
            
               ft10.setOnFinished(m->{
                n1.setVisible(true);
                n2.setVisible(true);
                n3.setVisible(true);
                n4.setVisible(true);
                n5.setVisible(true);
                n6.setVisible(true);
                n7.setVisible(true);
                n8.setVisible(true);
                n9.setVisible(true);
                auto.setVisible(true);
                
                FadeTransition ft11 = new FadeTransition(Duration.millis(500),deckpane);
            ft11.setFromValue(0);
            ft11.setToValue(1);
            ft11.play();
            ft11.setOnFinished(v->{
            
            selectdagger.setOnMouseEntered(e1->{selectdaggeri.setImage(new Image("images/daggers.png"));});
                selectsword.setOnMouseEntered(e1->{selectswordi.setImage(new Image("images/swords.png"));});
                selectmorning.setOnMouseEntered(e1->{selectmorningi.setImage(new Image("images/morningstars.png"));});
                selectharber.setOnMouseEntered(e1->{selectharberi.setImage(new Image("images/halberds.png"));});
                selectaxe.setOnMouseEntered(e1->{selectaxei.setImage(new Image("images/waraxes.png"));});
                selectlong.setOnMouseEntered(e1->{selectlongi.setImage(new Image("images/Longswords.png"));});
                selectarcher.setOnMouseEntered(e1->{selectarcheri.setImage(new Image("images/Archers.png"));});
                selectshield.setOnMouseEntered(e1->{selectshieldi.setImage(new Image("images/shields.png"));});
                selectking.setOnMouseEntered(e1->{selectkingi.setImage(new Image("images/kings.png"));});
                
                selectdagger.setOnMouseExited(e1->{selectdaggeri.setImage(new Image("images/dagger.png"));});
                selectsword.setOnMouseExited(e1->{selectswordi.setImage(new Image("images/sword.png"));});
                selectmorning.setOnMouseExited(e1->{selectmorningi.setImage(new Image("images/morningstar.png"));});
                selectharber.setOnMouseExited(e1->{selectharberi.setImage(new Image("images/halberd.png"));});
                selectaxe.setOnMouseExited(e1->{selectaxei.setImage(new Image("images/waraxe.png"));});
                selectlong.setOnMouseExited(e1->{selectlongi.setImage(new Image("images/Longsword.png"));});
                selectarcher.setOnMouseExited(e1->{selectarcheri.setImage(new Image("images/Archer.png"));});
                selectshield.setOnMouseExited(e1->{selectshieldi.setImage(new Image("images/shield.png"));});
                selectking.setOnMouseExited(e1->{selectkingi.setImage(new Image("images/king.png"));});
                
                selectdagger.setOnAction(e1->{System.out.println("dragged");
                selectpane.getChildren().remove(copy);
                selectpane.getChildren().add(copy); 
                copy.setImage(new Image("images/dagger.png"));
                copy.setFitHeight(selectdaggeri.getFitHeight()-20);
                copy.setFitWidth(selectdaggeri.getFitWidth()-20);
                copy.setLayoutX(selectdagger.getLayoutX());
                copy.setLayoutY(selectdagger.getLayoutY());
                selectedCard = new Card("Dagger","images/dagger.png",1);
                selectpane.setOnMouseMoved(e3->{
                    copy.setLayoutX(e3.getX());
                    copy.setLayoutY(e3.getY());
                });
                selectpane.setOnMouseClicked(e2->{selectpane.getChildren().remove(copy);});
                });
                
                selectsword.setOnAction(e1->{
                selectpane.getChildren().remove(copy);
                copy.setImage(new Image("images/sword.png"));
                selectpane.getChildren().add(copy);
                copy.setFitHeight(selectdaggeri.getFitHeight()-20);
                copy.setFitWidth(selectdaggeri.getFitWidth()-20);
                copy.setLayoutX(selectsword.getLayoutX());
                copy.setLayoutY(selectsword.getLayoutY()); 
                selectedCard = new Card("Sword","images/sword.png",2);
                selectpane.setOnMouseMoved(e3->{
                    copy.setLayoutX(e3.getX());
                    copy.setLayoutY(e3.getY());}); 
                selectpane.setOnMouseClicked(e2->{selectpane.getChildren().remove(copy);});
                });
                
                selectmorning.setOnAction(e1->{
                selectpane.getChildren().remove(copy);
                copy.setImage(new Image("images/morningstar.png"));
                selectpane.getChildren().add(copy);
                copy.setFitHeight(selectdaggeri.getFitHeight()-20);
                copy.setFitWidth(selectdaggeri.getFitWidth()-20);
                copy.setLayoutX(selectmorning.getLayoutX());
                copy.setLayoutY(selectmorning.getLayoutY()); 
                selectedCard = new Card("Morning Star","images/morningstar.png",3);
                selectpane.setOnMouseMoved(e3->{
                    copy.setLayoutX(e3.getX());
                    copy.setLayoutY(e3.getY());}); 
                selectpane.setOnMouseClicked(e2->{selectpane.getChildren().remove(copy);});
                });
            
                selectaxe.setOnAction(e1->{
                selectpane.getChildren().remove(copy);
                copy.setImage(new Image("images/waraxe.png"));
                selectpane.getChildren().add(copy);
                copy.setFitHeight(selectdaggeri.getFitHeight()-20);
                copy.setFitWidth(selectdaggeri.getFitWidth()-20);
                copy.setLayoutX(selectaxe.getLayoutX());
                copy.setLayoutY(selectaxe.getLayoutY()); 
                selectedCard = new Card("War Axe","images/waraxe.png",4);
                selectpane.setOnMouseMoved(e3->{
                    copy.setLayoutX(e3.getX());
                    copy.setLayoutY(e3.getY());}); 
                selectpane.setOnMouseClicked(e2->{selectpane.getChildren().remove(copy);});
                });
            
                selectharber.setOnAction(e1->{
                selectpane.getChildren().remove(copy);
                copy.setImage(new Image("images/halberd.png"));
                selectpane.getChildren().add(copy);
                copy.setFitHeight(selectdaggeri.getFitHeight()-20);
                copy.setFitWidth(selectdaggeri.getFitWidth()-20);
                copy.setLayoutX(selectharber.getLayoutX());
                copy.setLayoutY(selectharber.getLayoutY()); 
                selectedCard = new Card("Halberd","images/halberd.png",5);
                selectpane.setOnMouseMoved(e3->{
                    copy.setLayoutX(e3.getX());
                    copy.setLayoutY(e3.getY());}); 
                selectpane.setOnMouseClicked(e2->{selectpane.getChildren().remove(copy);});
                });
                
                  selectlong.setOnAction(e1->{
                selectpane.getChildren().remove(copy);
                copy.setImage(new Image("images/Longsword.png"));
                selectpane.getChildren().add(copy);
                copy.setFitHeight(selectdaggeri.getFitHeight()-20);
                copy.setFitWidth(selectdaggeri.getFitWidth()-20);
                copy.setLayoutX(selectlong.getLayoutX());
                copy.setLayoutY(selectlong.getLayoutY()); 
                selectedCard = new Card("Long Sword","images/Longsword.png",6);
                selectpane.setOnMouseMoved(e3->{
                    copy.setLayoutX(e3.getX());
                    copy.setLayoutY(e3.getY());}); 
                selectpane.setOnMouseClicked(e2->{selectpane.getChildren().remove(copy);});
                });
                  
                    selectarcher.setOnAction(e1->{
                selectpane.getChildren().remove(copy);
                copy.setImage(new Image("images/Archer.png"));
                selectpane.getChildren().add(copy);
                copy.setFitHeight(selectdaggeri.getFitHeight()-20);
                copy.setFitWidth(selectdaggeri.getFitWidth()-20);
                copy.setLayoutX(selectarcher.getLayoutX());
                copy.setLayoutY(selectarcher.getLayoutY()); 
                selectedCard = new Card("Archer","images/Archer.png",7);
                selectpane.setOnMouseMoved(e3->{
                    copy.setLayoutX(e3.getX());
                    copy.setLayoutY(e3.getY());}); 
                selectpane.setOnMouseClicked(e2->{selectpane.getChildren().remove(copy);});
                });
                
                      selectshield.setOnAction(e1->{
                selectpane.getChildren().remove(copy);
                copy.setImage(new Image("images/shield.png"));
                selectpane.getChildren().add(copy);
                copy.setFitHeight(selectdaggeri.getFitHeight()-20);
                copy.setFitWidth(selectdaggeri.getFitWidth()-20);
                copy.setLayoutX(selectshield.getLayoutX());
                copy.setLayoutY(selectshield.getLayoutY()); 
                selectedCard = new Card("Shield","images/shield.png",7);
                selectpane.setOnMouseMoved(e3->{
                    copy.setLayoutX(e3.getX());
                    copy.setLayoutY(e3.getY());}); 
                selectpane.setOnMouseClicked(e2->{selectpane.getChildren().remove(copy);});
                });
                
                selectking.setOnAction(e1->{
                selectpane.getChildren().remove(copy);
                copy.setImage(new Image("images/king.png"));
                selectpane.getChildren().add(copy);
                copy.setFitHeight(selectdaggeri.getFitHeight()-20);
                copy.setFitWidth(selectdaggeri.getFitWidth()-20);
                copy.setLayoutX(selectking.getLayoutX());
                copy.setLayoutY(selectking.getLayoutY()); 
                selectedCard = new Card("King","images/king.png",0);
                selectpane.setOnMouseMoved(e3->{
                    copy.setLayoutX(e3.getX());
                    copy.setLayoutY(e3.getY());}); 
                selectpane.setOnMouseClicked(e2->{selectpane.getChildren().remove(copy);});
                });
            });
                
                
                
            });    
                
                
            });
            
            });
            
            
            });
            
            
            });
            
            
            });
            
            
            });
            
            });
            
            
            });
            
            });
            
         });
    }    
    
    public boolean testFull(){
    for(Card c : deck)
    {
    if (c== null)
        return false;
    }
    return true;
    }
    
    public void Full(){
    if(testFull()){}
    
    }

    private boolean checkCardLimit(String c) {
        
        System.out.println("max is : "+ maxCardList.get(c));
        if(maxCardList.get(c)>0)
        return true;
        return false;
        
    }
    
    private void setCard(int cardNumber,ImageView ci, ImageView copy){
        if(selectedCard != null){
            
                selectpane.getChildren().remove(copy);
                if(checkCardLimit(selectedCard.getName()) || cardFromsSide)
                {
                    Image proxyCiImage = ci.getImage();
                    ci.setImage(new Image(selectedCard.getPicture()));
                    if(deck.get(cardNumber)!=null){
                         if(!cardFromsSide)
                        maxCardList.put(deck.get(cardNumber).getName(), maxCardList.get(deck.get(cardNumber).getName()) + 1);
                    } else{
                        if(!cardFromsSide)
                        total++;
                    }
                    Card proxyCard = deck.get(cardNumber);
                    deck.set(cardNumber,selectedCard);
                    
                       if(!cardFromsSide)
                            maxCardList.put(selectedCard.getName(), maxCardList.get(selectedCard.getName()) - 1);
                          else
                       {
                            deck.set(selectedCardNumber,proxyCard);
                             selectedImageView.setImage(proxyCiImage);
                       }
                       
                             refreshNumbers();
                                battlestart.setVisible(checkdeck());
                    
                }
            selectedCard = null;
            selectedCardNumber = 0;
            cardFromsSide = false;
            System.out.println(deck);
                Full();}
    
    }
    public void getCard(int cardNumber, Button c, ImageView copy, ImageView original){
      
        if(deck.get(cardNumber)!=null){
        
                cardFromsSide = true;
                selectedCardNumber = cardNumber;
                selectedImageView = original;
                selectpane.getChildren().remove(copy);
                selectpane.getChildren().add(copy); 
                copy.setImage(new Image("images/"+NameToLinkList.get(deck.get(cardNumber).getName())));
                copy.setFitHeight(selectdaggeri.getFitHeight()-20);
                copy.setFitWidth(selectdaggeri.getFitWidth()-20);
                copy.setLayoutX(c.getLayoutX());
                copy.setLayoutY(c.getLayoutY());
                selectedCard = new Card(deck.get(cardNumber).getName(),"images/"+NameToLinkList.get(deck.get(cardNumber).getName()),NameToPower.get(deck.get(cardNumber).getName()));
                System.out.println(selectedCard);
                System.out.println(selectedCardNumber);
                
                }
    
    }
    private void refreshNumbers(){
         n1.setText(maxCardList.get("Dagger")+"");
            n2.setText(maxCardList.get("Sword")+"");
            n3.setText(maxCardList.get("Morning Star")+"");
            n4.setText(maxCardList.get("War Axe")+"");
            n5.setText(maxCardList.get("Halberd")+"");
            n6.setText(maxCardList.get("Long Sword")+"");
            n7.setText(maxCardList.get("Archer")+"");
            n8.setText(maxCardList.get("Shield")+"");
            n9.setText(maxCardList.get("King")+"");
    }
    
    private boolean checkdeck(){
        if(total == 25)
        return true;
        return false;
    }
    
    private void randomize(){
        if(!deck.contains(null)){
            deck = new ArrayList<Card>();
          for(int i=0;i<25;i++)
              deck.add(null);
          
           maxCopy  = new HashMap<String,Integer>(originalMaxCopy);
        }else{
         maxCopy  = new HashMap<String,Integer>(maxCardList);
        }
        
       
        indexes = new ArrayList<Integer>(){{
                add(0);
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
                add(6);
                add(7);
                add(8);
            }};
           selectpane.getChildren().remove(maxCopy);
         for(int i=0; i< 25; i++){
           Random rand = new Random();
    int n = rand.nextInt(indexes.size());
            Card selectedCard  = cards.get(indexes.get(n)) ;
  
      
         
                if(deck.get(i)==null){
                      deck.set(i,selectedCard);
                      switch(i) {
                            case 0:
                              c11i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 1:
                              c12i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 2:
                              c13i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 3:
                              c14i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 4:
                              c15i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 5:
                              c21i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 6:
                              c22i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 7:
                              c23i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 8:
                              c24i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 9:
                              c25i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 10:
                              c31i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 11:
                              c32i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 12:
                              c33i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 13:
                              c34i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 14:
                              c35i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 15:
                              c41i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 16:
                              c42i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 17:
                              c43i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 18:
                              c44i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 19:
                              c45i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 20:
                              c51i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 21:
                              c52i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 22:
                              c53i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 23:
                              c54i.setImage(new Image(selectedCard.getPicture()));
                              break;
                            case 24:
                              c55i.setImage(new Image(selectedCard.getPicture()));
                              break;
                          }
                     if( maxCopy.get(selectedCard.getName())>0){
                        maxCopy.put(selectedCard.getName(),maxCopy.get(selectedCard.getName())-1);
                     }
                      
                    if( maxCopy.get(selectedCard.getName())==0){
                        indexes.remove(n);
                    }
                    
                   
                    Full();
                }
                    
                    
                  
      }
         total = 25;
         battlestart.setVisible(checkdeck());
         for (Map.Entry<String, Integer> entry : maxCardList.entrySet()) {
          maxCardList.put(entry.getKey(), 0);
      }
         refreshNumbers();
    }
    private void start(){
        
    }
    
    public static ArrayList<Node> getAllNodes(Parent root) {
    ArrayList<Node> nodes = new ArrayList<Node>();
    addAllDescendents(root, nodes);
    return nodes;
}

private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
    for (Node node : parent.getChildrenUnmodifiable()) {
        nodes.add(node);
        if (node instanceof Parent)
            addAllDescendents((Parent)node, nodes);
    }
}
}
