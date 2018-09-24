import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class OldWaypoints {
	public static Waypoint[] rightStartToRightSwitch = {
		new Waypoint(0, 0, 0),
		new Waypoint(3.3333, -0.5, 0),
		new Waypoint(4.3333, 0.5, Pathfinder.d2r(90))
	};
	
	public static Waypoint[] rightStartToRightScale = {
		new Waypoint(0, 0, 0),
		new Waypoint(3.3333, -0.4, 0),
		new Waypoint(5.3333, -0.4, 0),
		new Waypoint(7.1, 0.8889, 0),
	};
	
	public static Waypoint[] rightStartToLeftScale = {
		new Waypoint(0, 0, 0),
		new Waypoint(3.3333, -0.4, 0),
		new Waypoint(5.3333, -0.4, 0),
		new Waypoint(6.5, 0.5, Pathfinder.d2r(90)),
		new Waypoint(6.5, 4.5, Pathfinder.d2r(90)),
		new Waypoint(7.5, 5.8, 0),						// new Waypoint(7.3333, 5.3889, 0),
		new Waypoint(8, 5.8, 0),						// new Waypoint(7.3333, 5.3889, 0),
		
		/*
		new Waypoint(0, 0, 0),
		new Waypoint(3.3333, -0.5, 0),
		new Waypoint(5, -0.5, 0),
		new Waypoint(6, 0.5, Pathfinder.d2r(90)),
		new Waypoint(6, 4.7778, Pathfinder.d2r(90)),
		new Waypoint(7.1, 5.25, 0),					// new Waypoint(7.3333, 5.3889, 0),
		//new Waypoint(7.3333, 5.3889, 0),
		*/
	};
	
	/*
	public static Waypoint[] rightStartToLeftSwitch = {
		new Waypoint(0, 0, 0),
		new Waypoint(3.3333, -0.5, 0),
		new Waypoint(5.3333, -0.5, 0),
		new Waypoint(6.3333, 0.5, Pathfinder.d2r(90)),
		new Waypoint(6.3333, 4.7778, Pathfinder.d2r(90)),
	};
	*/
	
	public static Waypoint[] leftStartToLeftSwitch = {
		new Waypoint(0, 6.2778, 0),
		new Waypoint(3.3333, 6.7778, 0),
		new Waypoint(4.3333, 5.7778, Pathfinder.d2r(90)),
	};
	
	/*
	public static Waypoint[] leftStartToRightSwitch = {
		new Waypoint(0, 0, 0),
		new Waypoint(5, 0, 0)
	};
	
	public static Waypoint[] leftStartToRightScale = {
		new Waypoint(0, 0, 0),
		new Waypoint(5, 0, 0)
	};
	*/
}
