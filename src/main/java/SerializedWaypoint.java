import java.io.Serializable;

import jaci.pathfinder.Waypoint;

public class SerializedWaypoint implements Serializable{
	public double[] x, y, angle;
	public int length;
	
	public SerializedWaypoint(Waypoint waypoint) {
		x = new double[1];
		y = new double[1];
		angle = new double[1];
		length = 1;
		
		x[0] = waypoint.x;
		y[0] = waypoint.y;
		angle[0] = waypoint.angle;
	}

	public SerializedWaypoint(Waypoint[] waypoint) {
		x = new double[waypoint.length];
		y = new double[waypoint.length];
		angle = new double[waypoint.length];
		length = waypoint.length;
		
		for(int i = 1; i < waypoint.length; i++) {
			x[i] = waypoint[i].x;
			y[i] = waypoint[i].y;
			angle[i] = waypoint[i].angle;
		}
	}
	
	public boolean equals(Object obj){
		if(obj.getClass() != this.getClass())
			return false;
		SerializedWaypoint pathCmp = (SerializedWaypoint) obj;
		if(pathCmp.length != this.length)
			return false;
		for(int i = 0; i < this.length; i++){
			if(pathCmp.x[i] != this.x[i] || pathCmp.y[i] != this.y[i] || pathCmp.angle[i] != this.angle[i]) {
				return false;
			}
		}
		return true;
	}

}
