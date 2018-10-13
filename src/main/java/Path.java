import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

public class Path implements Serializable {
	transient Trajectory.FitMethod fitMethod = PathGen.fitMethod;
	int fitMethodType = Trajectory.FitMethod.HERMITE_CUBIC == fitMethod ? 0 : 1;
	double max_vel = PathGen.max_vel;
	double max_accel = PathGen.max_accel;
	double max_jerk = PathGen.max_jerk;
	int samples = PathGen.samples;
	double period = PathGen.period;
	
	public String name;
	public transient Waypoint[] path;
	public SerializedWaypoint serializedPath;
	
	transient Trajectory.Config config;
	
	public Path(String name, Waypoint[] waypoints) {
		path = waypoints;
		serializedPath = new SerializedWaypoint(path);
		this.name = name;
		reconfigureSettings();
	}
	
	public void setMaxVel(double max_vel) {
		this.max_vel = max_vel;
		reconfigureSettings();
	}
	
	public void setMaxAccel(double max_accel) {
		this.max_accel = max_accel;
		reconfigureSettings();
	}
	
	public void setMaxJerk(double max_jerk) {
		this.max_jerk = max_jerk;
		reconfigureSettings();
	}
	
	public void setFitMethod(Trajectory.FitMethod fitMethod) {
		this.fitMethod = fitMethod;
		this.fitMethodType = Trajectory.FitMethod.HERMITE_CUBIC == fitMethod ? 0 : 1;
		reconfigureSettings();
	}
	
	public void setSamples(int samples) {
		this.samples = samples;
		reconfigureSettings();
	}
	
	public void setPeriod(double period) {
		this.period = period;
		reconfigureSettings();
	}
	
	public void reconfigureSettings() {
		config = new Trajectory.Config(fitMethod, samples, period, max_vel, max_accel, max_jerk);
	}
	
	public int hashCode() {
		return Objects.hash(Arrays.hashCode(path));
	}
	
	public boolean equals(Object obj) {
		if(obj.getClass() != this.getClass())
			return false;
		Path testObj = (Path) obj;
		if(!testObj.name.equals(this.name))
			return false;
		if(!testObj.serializedPath.equals(this.serializedPath))
			return false;
		if(testObj.fitMethodType != this.fitMethodType)
			return false;
		if(testObj.max_vel != this.max_vel)
			return false;
		if(testObj.max_accel != this.max_accel)
			return false;
		if(testObj.max_jerk != this.max_jerk)
			return false;
		if(testObj.samples != this.samples)
			return false;
		if(testObj.period != this.period)
			return false;
		return true;
	}
	
	public boolean isConfigDefault(Object obj) {
		if(obj.getClass() != this.getClass())
			return false;
		Path testObj = (Path) obj;
		if(testObj.max_vel != this.max_vel)
			return false;
		if(testObj.max_accel != this.max_accel)
			return false;
		if(testObj.max_jerk != this.max_jerk)
			return false;
		return true;
	}
}
