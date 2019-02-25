import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class Waypoints {
	
	// Drive Straight
	public static Waypoint[] driveStraight = {
		new Waypoint(0, 0, 0),
		new Waypoint(5, 0, 0)
	};
	
	public static Waypoint[] driveCalibration = {
		new Waypoint(0, 0, 0),
		new Waypoint(10, 0, 0)
	};

	public static Waypoint[] getOffLevel1 = {
		new Waypoint(0, 0, 0),
		new Waypoint(4, 0, 0)
	};

	// Left Paths
	// leftLevel1ToRocket
	public static Waypoint[] leftToRocket = {
		new Waypoint(4, 0, 0),
		new Waypoint(12, 8, 30)
	};
}
