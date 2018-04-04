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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import assignment5.Critter;
///Users/cindyvu/Documents/GitHub/Critter-Part-2/src/assignment5
public class Main extends Application{
	static GraphicsContext gc;
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
		TextField numcrit=new TextField();
		ChoiceBox critter=new ChoiceBox(FXCollections.observableArrayList(filenames));
		Button makeGo=new Button("GO");
		makeGo.setOnAction(e->{
			for(int i =0;i < Integer.parseInt(numcrit.getText().toString());i++) {
				try {
					Critter.makeCritter(critter.getValue().toString());
				} catch (InvalidCritterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

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
			Critter.worldTimeStep();
		});


		Button step10=new Button("Step 10");
		step10.setOnAction(e->{
			for(int i = 0;i<10;i++) {
				Critter.worldTimeStep();
			}
		});


		Button step100=new Button("Step 100");
		step100.setOnAction(e->{
			for(int i = 0;i<100;i++) {
				Critter.worldTimeStep();
			}
		});


		Button step1000=new Button("Step 1000");
		step1000.setOnAction(e->{
			for(int i = 0;i<1000;i++) {
				Critter.worldTimeStep();
			}
		});



		HBox stepPreset=new HBox();
		stepPreset.getChildren().addAll(step1,step10);
		GridPane.setConstraints(stepPreset,0,5);
		HBox stepPreset2=new HBox();
		stepPreset2.getChildren().addAll(step100,step1000);
		GridPane.setConstraints(stepPreset2,0,6);

		TextField stepnum=new TextField();
		Button stepgo=new Button("GO");
		stepgo.setOnAction(e->{
			for(int i = 0;i < Integer.parseInt(stepnum.getText().toString());i++) {
				Critter.worldTimeStep();
			}
		});


		HBox stepControl=new HBox();
		stepControl.getChildren().addAll(stepnum,stepgo);
		GridPane.setConstraints(stepControl,0,7);


		//run stats

		Text runstats=new Text();
		runstats.setText("Run Stats");
		runstats.setFont(Font.font("Verdana", 10));
		runstats.setFill(Color.PURPLE);
		GridPane.setConstraints(runstats, 0,8);

		VBox rs=new VBox();

		ArrayList<CheckBox> c1 = new ArrayList<CheckBox>();
		for(int i = 0;i<filenames.size();i++) {
			c1.add(new CheckBox(filenames.get(i)));
			rs.getChildren().add(c1.get(i));
		}
		Button runstatGo=new Button("GO");
		Button updateAll=new Button("Update All");

		runstatGo.setOnAction(e->{
			for(int i = 0;i<filenames.size();i++) {
				if(c1.get(i).isSelected()) {
					Class<?> c;
					try {
						c = Class.forName("assignment5." + filenames.get(i));
						try {
							c.getMethod("runStats", java.util.List.class).invoke(null,Critter.getInstances(filenames.get(i)));
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
								| NoSuchMethodException | SecurityException | InvalidCritterException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		updateAll.setOnAction(e->{
			for (int i=0;i<filenames.size();i++){
				Class<?> c;
				try {
					c = Class.forName("assignment5." + filenames.get(i));
					try {
						c.getMethod("runStats", java.util.List.class).invoke(null,Critter.getInstances(filenames.get(i)));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| NoSuchMethodException | SecurityException | InvalidCritterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		rs.getChildren().addAll(runstatGo,updateAll);
		GridPane.setConstraints(rs,0,9);


		Text seed=new Text();
		seed.setText("Seed");
		seed.setFont(Font.font("Verdana",10));
		seed.setFill(Color.MEDIUMTURQUOISE);
		GridPane.setConstraints(seed, 0,10);
		TextField seednum=new TextField();
		Button seedGo=new Button("GO");
		seedGo.setOnAction(e->{
			Critter.setSeed(Integer.parseInt(seednum.getText().toString()));
		});
		HBox seedControl=new HBox();
		
		seedControl.getChildren().addAll(seednum,seedGo);
		GridPane.setConstraints(seedControl,0,11);

		//quit
		Button quit=new Button("Quit");
		quit.setOnAction(e->{
			System.exit(0);
		});
		GridPane.setConstraints(quit, 0, 13);
		left.getChildren().addAll(stepPreset,step,quit,runstats,rs,make,seed,stepPreset2, critter,
				seedControl,stepControl,makeControl);
		int width = Params.world_width*8;
		int height = Params.world_height*8;
		Canvas canvas=new Canvas(width,height);
		gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, width,height);
		

		
		// gc.setStroke(Color.RED);
		//for(int i = 30 ; i < height ; i+=30) {
		//gc.strokeLine(30, i, width, i);
		// }
		bp.setCenter(canvas);
		//show
		Button show = new Button("Show");
		show.setOnAction(e->{
			Critter.displayWorld(gc);
		});
		GridPane.setConstraints(show,0,12);
		left.getChildren().add(show);
		//Animate
		Button animate = new Button("Simulate");
		Button stop = new Button("Stop");
		Animate A1 = new Animate();
		stop.setOnAction(e->{
			A1.stop();
		});
		animate.setOnAction(e->{
			A1.start();
		});
		GridPane.setConstraints(animate,0,14);
		GridPane.setConstraints(stop,1,14);
		left.getChildren().addAll(animate,stop);
		window.setScene(scene);
		window.show();


	}



}
