import java.io.Serializable;
import java.util.*;

public class NewCourse implements Serializable{
	private Courses course;
	private GradingScheme scheme;
	private GradingScale scale;

	
	public NewCourse(){}
	
	public Courses getCourse() {
		return course;
	}
	
	public void setCourse(Courses course) {
		this.course = course;
	}
	public GradingScheme getScheme() {
		return scheme;
	}
	public void setScheme(GradingScheme scheme) {
		this.scheme = scheme;
	}
	public GradingScale getScale() {
		return scale;
	}
	public void setScale(GradingScale scale) {
		this.scale = scale;
	}
		
}
