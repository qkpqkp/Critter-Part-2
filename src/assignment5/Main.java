package assignment5;

import javafx.application.Application;
import javafx.application.*;

import javafx.collections.FXCollections;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.geometry.Insets;
import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
///Users/cindyvu/Documents/GitHub/Critter-Part-2/src/assignment5
public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception{
		window.setTitle("Critter Part 2");

		BorderPane bp=new BorderPane();
		GridPane left=new GridPane();
		bp.setLeft(left);
		Scene scene=new Scene(bp);

		left.setPadding(new Insets(10,20,10,20));
		left.setVgap(8);
		left.setHgap(10);

		//Make
		Text make=new Text();
		make.setText("Make Critter");
		make.setFont(Font.font("Verdana",10));
		make.setFill(Color.DARKOLIVEGREEN);
		GridPane.setConstraints(make,0,0);

		ArrayList<String> filenames=new ArrayList<>();
		File folder=new File("/Users/cindyvu/Documents/GitHub/Critter-Part-2/src/assignment5");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile()) {
				try {
					String s = file.getName();
					String[] tokens=s.split(".java");
					for (String t:tokens){
						s=t;
					}
					Class c = Class.forName("assignment5." + s);
					Class critter = Class.forName("assignment5.Critter");
					if(critter.isAssignableFrom(c) &&  !Modifier.isAbstract( c.getModifiers() ) ) {
						System.out.println(s);
						filenames.add(s);
					}
				} catch (Exception e) {

				}
			}
		}
		HBox makeControl=new HBox();
		Button makeGo=new Button("GO");
		makeGo.setOnAction(e->{

		});

		TextField numcrit=new TextField();
		ChoiceBox critter=new ChoiceBox(FXCollections.observableArrayList(filenames));

		makeControl.getChildren().addAll(numcrit, makeGo);
		GridPane.setConstraints(critter,0,2);
		GridPane.setConstraints(makeControl,0,3);

		//step
		Text step=new Text();
		step.setText("Step");
		step.setFont(Font.font ("Verdana", 10));
		step.setFill(Color.DEEPPINK);
		GridPane.setConstraints(step, 0,4);

		Button step1=new Button("Step 1");
		step1.setOnAction(e->{
			//do something
		});


		Button step10=new Button("Step 10");
		step10.setOnAction(e->{
			//do something
		});


		Button step100=new Button("Step 100");
		step100.setOnAction(e->{
			//do something
		});


		Button step1000=new Button("Step 1000");
		step1000.setOnAction(e->{
			//do something
		});



		HBox stepPreset=new HBox();
		stepPreset.getChildren().addAll(step1,step10);
		GridPane.setConstraints(stepPreset,0,5);
		HBox stepPreset2=new HBox();
		stepPreset2.getChildren().addAll(step100,step1000);
		GridPane.setConstraints(stepPreset2,0,6);


		Button stepgo=new Button("GO");
		stepgo.setOnAction(e->{
			//do something
		});


		HBox stepControl=new HBox();
		TextField stepnum=new TextField();
		stepControl.getChildren().addAll(stepnum,stepgo);
		GridPane.setConstraints(stepControl,0,7);


		//run stats

		Text runstats=new Text();
		runstats.setText("Run Stats");
		runstats.setFont(Font.font("Verdana", 10));
		runstats.setFill(Color.PURPLE);
		GridPane.setConstraints(runstats, 0,8);

		VBox rs=new VBox();

		CheckBox c1=new CheckBox("c1");
		CheckBox c2=new CheckBox("c2");
		CheckBox c3=new CheckBox("c3");
		CheckBox c4=new CheckBox("c4");
		Button runstatGo=new Button("GO");
		Button updateAll=new Button("Update All");
		runstatGo.setOnAction(e->{
			//do something
		});
		rs.getChildren().addAll(c1,c2,c3,c4,runstatGo,updateAll);
		GridPane.setConstraints(rs,0,9);


		Text seed=new Text();
		seed.setText("Seed");
		seed.setFont(Font.font("Verdana",10));
		seed.setFill(Color.MEDIUMTURQUOISE);
		GridPane.setConstraints(seed, 0,10);

		Button seedGo=new Button("GO");
		seedGo.setOnAction(e->{
			//do something
		});
		HBox seedControl=new HBox();
		TextField seednum=new TextField();
		seedControl.getChildren().addAll(seednum,seedGo);
		GridPane.setConstraints(seedControl,0,11);


		//quit
		Button quit=new Button("Quit");
		quit.setOnAction(e->{
			//do something
		});
		GridPane.setConstraints(quit, 0, 12);

		left.getChildren().addAll(stepPreset,step,quit,runstats,rs,make,seed,stepPreset2, critter,
				seedControl,stepControl,makeControl);
		int width=160;
		int height=300;

		Canvas canvas=new Canvas(width+500,height+500);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, width+500, height+500);


		// vertical lines
		gc.setStroke(Color.BLUE);
		for(int i = 10 ; i < width ; i+=5){
			gc.strokeLine(i, 10, i, height );
		}

		// horizontal lines
		// gc.setStroke(Color.RED);
		//for(int i = 30 ; i < height ; i+=30) {
		//gc.strokeLine(30, i, width, i);
		// }
		bp.setCenter(canvas);







		window.setScene(scene);
		window.show();






	}



}
