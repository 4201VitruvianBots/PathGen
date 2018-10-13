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
		new Waypoint(3, 0, 0)
	};
	
	public static Waypoint[] scaleToEdgeCubeLeft = {
		new Waypoint(5.5, 0, 0),
		new Waypoint(7.8, 0, 0),
	};
	
	public static Waypoint[] scaleToEdgeCubeRight = {
		new Waypoint(5.5, 0, 0),
		new Waypoint(7.6, 0, 0),
	};
	
	public static Waypoint[] edgeCubeToScaleDrop = {
		new Waypoint(5.5, 0.8889, 0),
		new Waypoint(7.4, 0.8889, 0),
	};
	
	public static Waypoint[] edgeCubeToScaleShoot = {
		new Waypoint(5.5, 0.8889, 0),
		new Waypoint(7.6, 0.8889, 0),
	};
		
	public static Waypoint[] edgeCubeToSwitch = {
		new Waypoint(5.5, 0.8889, 0),
		new Waypoint(5.8, 0.8889, 0),
	};
	
	public static Waypoint[] leftScaleToEdgeCubeFar = {
		new Waypoint(5.7, 0, 0),
		new Waypoint(8, -0.2, 0),
	};
	
	public static Waypoint[] rightScaleToEdgeCubeFar = {
		new Waypoint(5.7, 0, 0),
		new Waypoint(8, 0.2, 0),
	};
	
	public static Waypoint[] edgeCubeFarToScale = {
		new Waypoint(5.7, 0.8889, 0),
		new Waypoint(7.6, 0.8889, 0),
	};
	
	// Center Autos
	public static Waypoint[] centerAutoLeft = {
		new Waypoint(0, 2.8333, 0),
		new Waypoint(3.05, 4.3, 0)				//new Waypoint(3.1, 4.5, 0)
	};
	
	public static Waypoint[] centerAutoRight = {
		new Waypoint(0, 2.8333, 0),
		new Waypoint(3.05, 1.4, 0)				//new Waypoint(3.1, 1.5, 0)
	};
		
	public static Waypoint[] switchBackLeft = {
		new Waypoint(0, 2.8333, 0),
		new Waypoint(3.05, 4.3, 0)				//new Waypoint(3.1, 4.5, 0)
	};
	
	public static Waypoint[] switchBackRight = {
		new Waypoint(0, 2.8333, 0),
		new Waypoint(3.05, 1.4, 0)	//new Waypoint(3.1, 1.5, 0)
	};
	
	public static Waypoint[] centerAutoGrabCube = {
		new Waypoint(0, 0, 0),
		new Waypoint(2.4, 0, 0)				//new Waypoint(3.1, 1.5, 0)
	};
	
	// Right Autos
	public static Waypoint[] rightStartToRightScale = {
		new Waypoint(0, 0, 0),
		new Waypoint(5.25, 0, 0),
		new Waypoint(7.6, 1, 0),
	};
	
	public static Waypoint[] rightStartToLeftScale = {
		new Waypoint(0, 0, 0),
		new Waypoint(3.3333, -0.4, 0),
		new Waypoint(5.3333, -0.4, 0),
		new Waypoint(6.3, 0.5, Pathfinder.d2r(90)),
		new Waypoint(6.3, 4, Pathfinder.d2r(90)),
		new Waypoint(7.5, 5, 0),						
		new Waypoint(8.4, 5, 0),		
	};
	
	// 
	public static Waypoint[] rightStartToRightSwitchReverseOne = {
		new Waypoint(0, 0, 0),
		new Waypoint(5.3333, -0.4, 0),
	};
	public static Waypoint[] rightStartToRightSwitchReverseTwo = {
		new Waypoint(0, 0, 0),
		new Waypoint(1.75, -0.75, Pathfinder.d2r(-90)),
	};
	
	//
	public static Waypoint[] rightStartToLeftSwitchNearReverseOne = {
		new Waypoint(0, 0, 0),
		new Waypoint(3, 0, 0),
	};
	public static Waypoint[] rightStartToLeftSwitchNearReverseTwo = {
		new Waypoint(0, 0, 0),
		new Waypoint(2.2, -1, Pathfinder.d2r(-90)),
		new Waypoint(2.2, -3.75, Pathfinder.d2r(-90)),
		new Waypoint(0.9, -5.1, Pathfinder.d2r(-180)),
	};
	
	// 
	public static Waypoint[] rightStartToLeftSwitchFarReverseOne = {
		new Waypoint(0, 0, 0),
		new Waypoint(7, 0, 0),
	};
	public static Waypoint[] rightStartToLeftSwitchFarReverseTwo = {
		new Waypoint(0, 0, 0),
		new Waypoint(1.3, -1, Pathfinder.d2r(-90)),
		new Waypoint(1.3, -5.4, Pathfinder.d2r(-90)),
		new Waypoint(2.2, -6.4, 0),
		new Waypoint(4.7, -5.7, Pathfinder.d2r(90)),
	};
	
	//
	public static Waypoint[] rightScaleToCubeSix = {
		new Waypoint(0, 0, 0),
		new Waypoint(1.3, -1, Pathfinder.d2r(-90)),
		new Waypoint(1.3, -3.3, Pathfinder.d2r(-90)),
		new Waypoint(2.4, -3.9, 0),
	};

	// Left Autos
	// 4/2/2018 5:01 PM
	public static Waypoint[] leftStartToLeftScale = {
		new Waypoint(0, 0, 0),
		new Waypoint(5.25, 0, 0),
		new Waypoint(7.8, -1, 0),
	};
	
	public static Waypoint[] leftStartToRightScale = {
		new Waypoint(0, 0, 0),
		new Waypoint(3.3333, 0.4, 0),
		new Waypoint(5.3333, 0.4, 0),
		new Waypoint(6.3, -0.5, Pathfinder.d2r(-90)),
		new Waypoint(6.3, -4, Pathfinder.d2r(-90)),
		new Waypoint(7.5, -5, 0),							
		new Waypoint(8.4, -5, 0),
	};
	
	// 4/2/2018 5:12 PM
	public static Waypoint[] leftStartToLeftSwitchReverseOne = {
		new Waypoint(0, 0, 0),
		new Waypoint(5.3333, 0.4, 0),
	};
	public static Waypoint[] leftStartToLeftSwitchReverseTwo = {
		new Waypoint(0, 0, 0),
		new Waypoint(1.75, 0.75, Pathfinder.d2r(90)),
	};
	
	// 4/2/2018 5:?? PM
	public static Waypoint[] leftStartToRightSwitchNearReverseOne = {
		new Waypoint(0, 0, 0),
		new Waypoint(3, 0, 0),
	};
	public static Waypoint[] leftStartToRightSwitchNearReverseTwo = {
		new Waypoint(0, 0, 0),
		new Waypoint(2.2, 1, Pathfinder.d2r(90)),
		new Waypoint(2.2, 3.75, Pathfinder.d2r(90)),
		new Waypoint(0.9, 5.1, Pathfinder.d2r(180)),
	};
	
	// 4/2/2018 5:51 PM
	public static Waypoint[] leftStartToRightSwitchFarReverseOne = {
		new Waypoint(0, 0, 0),
		new Waypoint(7, 0, 0),
	};
	public static Waypoint[] leftStartToRightSwitchFarReverseTwo = {
		new Waypoint(0, 0, 0),
		new Waypoint(1.3, 1, Pathfinder.d2r(90)),
		new Waypoint(1.3, 5.4, Pathfinder.d2r(90)),
		new Waypoint(2.2, 6.4, 0),
		new Waypoint(4.7, 5.7, Pathfinder.d2r(-90)),
	};
	
	// 4/2/2018 7:43 PM
	public static Waypoint[] leftScaleToCubeOne = {
		new Waypoint(0, 0, 0),
		new Waypoint(1.3, 1, Pathfinder.d2r(90)),
		new Waypoint(1.3, 3.3, Pathfinder.d2r(90)),
		new Waypoint(2.4, 3.9, 0),
	};
}
