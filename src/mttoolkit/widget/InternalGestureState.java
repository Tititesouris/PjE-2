package mttoolkit.widget;

import mttoolkit.mygeom.OBB;
import mttoolkit.mygeom.Vector2;

public class InternalGestureState {
	// position d'une OBB
	OBB oldOBB, currentOBB;
	// �tats du curseur pour la translation simple
	Vector2 oldPos, currentPos;

	public InternalGestureState(MTComponent c) {
		// le currentOBB sera une r�f�rence sur l'OBB de c
		// donc : lorsque currentOBB est modifi�, cela modifie l'OBB de c (pas
		// de new pour currentOBB !!!!!)

		oldPos = new Vector2(); // pour m�moriser la position pr�c�dente du
								// curseur
		currentPos = new Vector2();
		oldOBB = new OBB(); // pour m�moriser la position pr�c�dente, si besoin
		currentOBB = c.obb; // r�f�rence sur l'obb du composant
	}
	
	public void motionTranslateBegin(Vector2 cursor) {
		currentPos = cursor;
	}
	
	public void motionTranslateUpdate(Vector2 cursor) {
		oldPos = currentPos;
		currentPos = cursor;
	}
	
	public Vector2 computeTranslation() {
		Vector2 tmp = currentPos;
		tmp.sub(oldPos);
		
		return tmp;
	}
}
