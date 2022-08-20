package Main;

import Algorithm.Algorithm;
import Algorithm.PseudoCode;
import javafx.scene.text.Text;

public class Context {
//	private String algorithmname;
	private Algorithm algorithm;
//	private Graph graph;
	
	public void speedup() {
		algorithm.speedup();
	}
	
	public void slowdown() {
		algorithm.slowdown();
	}

	public Context() {
		super();
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

//	public Context(String algorithmname) {
//		super();
//		this.algorithmname = algorithmname;
//		this.graph = graph;
//		this.algorithm = AlgorithmFactory.algorithmCreate(this.algorithmname, this.graph);
//		algorithm.setup();
//	}

	public void setupAlgorithm(Algorithm algorithm) {
		this.algorithm=algorithm;
	}
	
	public void executeOnce() {
		algorithm.execute();
	}
	
	public void executeAll() {
		algorithm.executeAll();
	}
	
	public float getspeed() {
		return algorithm.getSpeeddown()/algorithm.getSpeed();
	}
	
	public Text getdescription() {
		return algorithm.getDescriptionText();
	}
	
	public PseudoCode getCode() {
		return algorithm.getpCode();
	}
	
}
