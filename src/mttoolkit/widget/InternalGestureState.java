package mttoolkit.widget;

import java.awt.Dimension;

import mttoolkit.mygeom.OBB;
import mttoolkit.mygeom.Vector2;

public class InternalGestureState {
	// position d'une OBB
	OBB oldOBB, currentOBB;
	// états du curseur pour la translation simple
	Vector2 oldPos_A, oldPos_B, currentPos_A, currentPos_B;

	public InternalGestureState(MTComponent c) {
		// le currentOBB sera une référence sur l'OBB de c
		// donc : lorsque currentOBB est modifié, cela modifie l'OBB de c (pas
		// de new pour currentOBB !!!!!)

		oldPos_A = new Vector2(); 	// pour mémoriser la position précédente du
		oldPos_B = new Vector2();	// curseur
		
		currentPos_A = new Vector2();
		currentPos_B = new Vector2();
		
		oldOBB = new OBB(); // pour mémoriser la position précédente, si besoin
		currentOBB = c.obb; // référence sur l'obb du composant
	}
	
	public void motionTranslateBegin(Vector2 cursor) {
		currentPos_A = cursor;
	}
	
	public void motionTranslateUpdate(Vector2 cursor) {
		oldPos_A = currentPos_A ;
		currentPos_A  = cursor;
	}
	
	public Vector2 computeTranslation() {
		Vector2 tmp = new Vector2();
		tmp.set(oldPos_A , currentPos_A );		
		return tmp;
	}
	
	public void motionTRSBegin(Vector2 cursorA, Vector2 cursorB) {
		currentPos_A = cursorA;
		currentPos_B = cursorB;
	}
	
	public void motionTRSUpdate(Vector2 cursorA, Vector2 cursorB) {
		oldPos_A = currentPos_A ;
		currentPos_A  = cursorA;
		oldPos_B = currentPos_B ;
		currentPos_B  = cursorB;
	}
	
	public Vector2 computeTRS() {
		Vector2 tmp = new Vector2();
		tmp.set(oldPos_A , currentPos_A );		
		return tmp;
	}

}
