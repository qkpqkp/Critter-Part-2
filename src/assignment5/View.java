package assignment5;

import com.sun.javafx.geom.Shape;

import assignment5.Critter.CritterShape;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;

public abstract class View {
	public static void PrintCritter(GraphicsContext gc,Critter crit) {
		CritterShape s = crit.viewShape();
		if(s.equals(Critter.CritterShape.CIRCLE)) {
			gc.strokeOval(crit.getX()*8+1, crit.getY()*8+1, 6, 6);;
		}
		else if(s.equals(Critter.CritterShape.SQUARE)) {
			gc.strokeRect(crit.getX()*8+1, crit.getY()*8+1, 6, 6);
		}
		else if(s.equals(Critter.CritterShape.DIAMOND)) {
			gc.strokePolygon(new double[] {crit.getX()*8+1,crit.getX()*8+3,crit.getX()*8+5,crit.getX()*8+7,crit.getX()*8+4},
					new double[] {crit.getY()*8+3,crit.getY()*8+1,crit.getY()*8+1,crit.getY()*8+3,crit.getY()*8+7},5);
		}
		else if (s.equals(Critter.CritterShape.TRIANGLE)){
			gc.strokePolygon(new double[] {crit.getX()*8+1,crit.getX()*8+7,crit.getX()*8+4}, new double[]{crit.getY()*8+1,crit.getY()*8+1,crit.getY()*8+7},3);
		}
		else if (s.equals(Critter.CritterShape.OVAL)){
			gc.strokeOval(crit.getX()*8+1, crit.getY()*8+3, 6, 3);
		}
		else if(s.equals(Critter.CritterShape.RECTANGLE))
		{
			gc.strokeRect(crit.getX()*8+1, crit.getY()*8+3, 6, 3);
		}
		else if(s.equals(Critter.CritterShape.HEX)) {
			gc.strokePolygon(new double[] {crit.getX()*8+1,crit.getX()*8+3,crit.getX()*8+5,crit.getX()*8+7,crit.getX()*8+5,crit.getX()*8+3},
					new double[] {crit.getY()*8+4,crit.getY()*8+1,crit.getY()*8+1,crit.getY()*8+4,crit.getY()*8+7,crit.getY()*8+7},6);
		}
		else if(s.equals(Critter.CritterShape.STAR)) {
			gc.strokePolygon(new double[] {crit.getX()*8+4,crit.getX()*8+3,crit.getX()*8+1,crit.getX()*8+3,crit.getX()*8+2,crit.getX()*8+4,crit.getX()*8+6,crit.getX()*8+5,crit.getX()*8+7,crit.getX()*8+5},
					new double[] {crit.getY()*8+1,crit.getY()*8+3,crit.getY()*8+3,crit.getY()*8+4.5,crit.getY()*8+7,crit.getY()*8+5.5,crit.getY()*8+7,crit.getY()*8+4.5,crit.getY()*8+3,crit.getY()*8+3}, 10);
		}
		//More shapes3
	}
	
}
