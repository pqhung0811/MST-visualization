package Main;

import Graph.*;
import Algorithm.AlgorithmFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.animation.FillTransition;
import javafx.util.Duration;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import javafx.animation.StrokeTransition;
import javafx.collections.FXCollections;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
	
	@FXML
	private Button initNewGraph;
	@FXML
	private Button execute;
	@FXML
	private Button executeAll;
	@FXML
	private Button reset; 
	@FXML
	private Label chooseAl;
	@FXML
	private ComboBox<String> alBox;
	@FXML
	private Group group;
	@FXML
	private AnchorPane childPane;
	@FXML
	private Label result;
	@FXML
	private AnchorPane mainPane;
	@FXML
	private Pane drawGraph;
	@FXML
	private VBox vBox;
	@FXML
	private ImageView imageView;
	@FXML
	private Button speedup;
	@FXML
	private Button slowdown;
	@FXML
	private Text speedText;
	@FXML
	private Button exampleGraph;
	
    private Graph graph;
    private List<Vertex> selectedVertice = new ArrayList<Vertex>();
    private boolean graphLocked = false;
    private Context context;
    private String alnameString;
    private static int ver=0;
    
    @FXML
    public void handle(MouseEvent event) 
	{ 
        addVertexEvent.handle(event);
    };
    
	public EventHandler<MouseEvent> addVertexEvent = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if(event.getButton() == MouseButton.PRIMARY && !event.getSource().equals(group)) {
				ver++;
				Vertex vertex = new Vertex(event.getX(), event.getY(), 18, ver);
				addVerToGraph(vertex);
			}
			 else if (event.getButton() == MouseButton.SECONDARY) 
         	{
                 unhighlightAllVertices(selectedVertice);
                 selectedVertice.clear();
             }
		}
	};
	
	public EventHandler<MouseEvent> addEdgeEvent = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			System.out.println("Pointed!");
			Vertex circle = (Vertex) event.getSource();
			
			selectedVertice.remove(circle);	
			selectedVertice.add(circle);
		
			for (Vertex v: selectedVertice)
				System.out.println(v.getID());
		
			if (selectedVertice.size () == 1)	
				highlightAllVertices(selectedVertice);
						
			else if (!graph.isExistsEdge(selectedVertice.get(0), selectedVertice.get(1))) {
				  TextInputDialog dialog = new TextInputDialog();

			      dialog.setTitle("Hello world!");
			      dialog.setHeaderText("Enter the weight:");
			      Optional<String> result = dialog.showAndWait();
			      int w=0;
			      if (result.isPresent()) {
                  	w = Integer.parseInt(result.get());
                  } 
			      else {}
			      if(w!=0) {
			      Edge newEdge = new Edge(selectedVertice.get(0), selectedVertice.get(1), w);
			      addEdgeToGraph(newEdge);
			      }
			unhighlightAllVertices(selectedVertice);
            selectedVertice.clear();
			}
			else
			{
				unhighlightAllVertices(selectedVertice);
				selectedVertice.clear();
			}
		}
		
	}; 
	
	@FXML
	public void ExampleGraph(ActionEvent event) {
		 String string;
		 ChoiceDialog<String> dialog = new ChoiceDialog<String>("graph 1", "graph 1", "graph 2", "graph 3");

	    	dialog.setTitle("Hello world!!!");
	    	dialog.setHeaderText("Select a example graph:");
	    	dialog.setContentText("Example graph:");

	    	Optional<String> result = dialog.showAndWait();
	    	string = result.get();
		 switch (string) {
			 case "graph 1":
				 for(int i=1; i<4; i++) {
					 ver++;
					 Vertex vertex = new Vertex(100, i*100, 18, ver);
					 addVerToGraph(vertex);
				 }
				 for(int i=1; i<4; i++) {
					 ver++;
					 Vertex vertex = new Vertex(230, i*100, 18, ver);
					 addVerToGraph(vertex);
				 }
				 Edge edge1 = new Edge(graph.getListVertice().get(0), graph.getListVertice().get(1), 2);
				 Edge edge2 = new Edge(graph.getListVertice().get(0), graph.getListVertice().get(3), 1);
				 Edge edge3 = new Edge(graph.getListVertice().get(3), graph.getListVertice().get(4), 3);
				 Edge edge4 = new Edge(graph.getListVertice().get(1), graph.getListVertice().get(4), 4);
				 Edge edge5 = new Edge(graph.getListVertice().get(4), graph.getListVertice().get(5), 2);
				 Edge edge6 = new Edge(graph.getListVertice().get(2), graph.getListVertice().get(5), 3);
				 Edge edge7 = new Edge(graph.getListVertice().get(1), graph.getListVertice().get(2), 2);
				 addEdgeToGraph(edge1);
				 addEdgeToGraph(edge2);
				 addEdgeToGraph(edge3);
				 addEdgeToGraph(edge4);
				 addEdgeToGraph(edge5);
				 addEdgeToGraph(edge6);
				 addEdgeToGraph(edge7);
				break;
			 case "graph 2": 
				 for(int i=1; i<4; i++) {
					 ver++;
					 Vertex vertex1 = new Vertex(100, i*100, 18, ver);
					 addVerToGraph(vertex1);
				 }
				 for(int i=1; i<3; i++) {
					 ver++;
					 Vertex vertex1 = new Vertex(200, i*100, 18, ver);
					 addVerToGraph(vertex1);
				 }
				 ver++;
				 Vertex vertex1 = new Vertex(300, 100, 18, ver);
				 addVerToGraph(vertex1);
				 Edge edge11 = new Edge(graph.getListVertice().get(0), graph.getListVertice().get(1), 2);
				 Edge edge21 = new Edge(graph.getListVertice().get(0), graph.getListVertice().get(3), 1);
				 Edge edge31 = new Edge(graph.getListVertice().get(3), graph.getListVertice().get(4), 3);
				 Edge edge41 = new Edge(graph.getListVertice().get(1), graph.getListVertice().get(4), 4);
				 Edge edge51 = new Edge(graph.getListVertice().get(4), graph.getListVertice().get(5), 6);
				 Edge edge61 = new Edge(graph.getListVertice().get(3), graph.getListVertice().get(5), 3);
				 Edge edge71 = new Edge(graph.getListVertice().get(1), graph.getListVertice().get(2), 2);
				 Edge edge81 = new Edge(graph.getListVertice().get(2), graph.getListVertice().get(4), 1);
				 addEdgeToGraph(edge11);
				 addEdgeToGraph(edge21);
				 addEdgeToGraph(edge31);
				 addEdgeToGraph(edge41);
				 addEdgeToGraph(edge51);
				 addEdgeToGraph(edge61);
				 addEdgeToGraph(edge71);
				 addEdgeToGraph(edge81);
				 break;
			 default:
				 for(int i=1; i<3; i++) {
					 ver++;
					 Vertex vertex2 = new Vertex(100, i*100, 18, ver);
					 addVerToGraph(vertex2);
				 }
				 for(int i=1; i<3; i++) {
					 ver++;
					 Vertex vertex2 = new Vertex(250, i*100, 18, ver);
					 addVerToGraph(vertex2);
				 }
				 ver++;
				 Vertex vertex2 = new Vertex(175, 150, 18, ver);
				 addVerToGraph(vertex2);
				 Edge edge12 = new Edge(graph.getListVertice().get(0), graph.getListVertice().get(1), 2);
				 Edge edge22 = new Edge(graph.getListVertice().get(0), graph.getListVertice().get(2), 1);
				 Edge edge32 = new Edge(graph.getListVertice().get(3), graph.getListVertice().get(2), 3);
				 Edge edge42 = new Edge(graph.getListVertice().get(3), graph.getListVertice().get(1), 4);
				 Edge edge52 = new Edge(graph.getListVertice().get(4), graph.getListVertice().get(0), 6);
				 Edge edge62 = new Edge(graph.getListVertice().get(4), graph.getListVertice().get(1), 3);
				 Edge edge72 = new Edge(graph.getListVertice().get(4), graph.getListVertice().get(2), 2);
				 Edge edge82 = new Edge(graph.getListVertice().get(4), graph.getListVertice().get(3), 1);
				 addEdgeToGraph(edge12);
				 addEdgeToGraph(edge22);
				 addEdgeToGraph(edge32);
				 addEdgeToGraph(edge42);
				 addEdgeToGraph(edge52);
				 addEdgeToGraph(edge62);
				 addEdgeToGraph(edge72);
				 addEdgeToGraph(edge82);
				break;
		}
	}
	
    @FXML
	public void speedup(ActionEvent event) {
    	context.speedup();
		speedText.setText("Speed: " + String.valueOf(2*context.getspeed()) + "second\nper step");
	}
	
    @FXML
	public void slowdown(ActionEvent event) {
		context.slowdown();
		speedText.setText("Speed: " + String.valueOf(2*context.getspeed()) + "second\nper step");
	}
	
	public void boxchoose(ActionEvent event) {
			alnameString = alBox.getValue();
			System.out.println(alnameString);
	}
	
	protected void highlightAllVertices(List<Vertex> v)	{	
		for (Vertex i : v) 
			highlight(i);	
	}
	
	protected void highlight(Vertex v) {	
		v.draw(Color.RED);	
	}
	
	protected void unhighlightAllVertices(List<Vertex> v)	{	
		for (Vertex i: v) 
			unhighlight(i);	
	}
	
	protected void unhighlight(Vertex v) {	
		FillTransition ft1 = new FillTransition(Duration.millis(300), v);
        ft1.setToValue(Color.BLACK);
        ft1.play();
	}
	
	protected void unhighlightAllEdges(List<Edge> e)	{	
		for (Edge i: e)
			unhighlight(i);	
		}
	protected void unhighlight(Edge e) {	
		 StrokeTransition ftEdge = new StrokeTransition(Duration.millis(500), e);
         ftEdge.setToValue(Color.BLACK);
         ftEdge.play();
	}
	
	public void addVerToGraph(Vertex vertex) {
		group.getChildren().addAll(vertex.drawableObjects());
        graph.addVertex(vertex);
        System.out.println("ver " + vertex.getID());
        System.out.println(graph.getListVertice().size());
        for(int i=0; i<graph.getListVertice().size(); i++) {
			System.out.println("Vertex " + graph.getListVertice().get(i).getID());
		}
        vertex.setOnMousePressed(addEdgeEvent);
	}
	
	protected void addEdgeToGraph(Edge edge)
    {
        group.getChildren().addAll(edge.drawableObjects());
        graph.addEdge(edge);
    }
	
	@FXML
    private void resetHandle(ActionEvent event) {
        if (graphLocked)
        	unlockGraph();
        unhighlightAllVertices(graph.getListVertice());
        unhighlightAllEdges(graph.getListEdges());
//        context = new Context(alBox.getValue(), graph);
//        context.setup();
        vBox.getChildren().removeAll(vBox.getChildren());
    }
	
	 @FXML
	 private void initHandle(ActionEvent event) {
	    if (graphLocked)
	        unlockGraph();
	    graph.initNewGraph();
	    ver=0;
	    group.getChildren().clear();
	    group.getChildren().addAll(drawGraph);
	    vBox.getChildren().removeAll(vBox.getChildren());
	}

    protected void lockGraph() {
	   	for (Vertex v: graph.getListVertice())
	    	v.setOnMousePressed(e -> {});
	    drawGraph.setOnMouseClicked(e -> {});
	    graphLocked = true;
//    	context = new Context(alBox.getValue(), graph);
	    context.setupAlgorithm(AlgorithmFactory.algorithmCreate(alnameString, graph));
		vBox.getChildren().add(context.getCode().getLine1());
		vBox.getChildren().add(context.getCode().getLine2());
		vBox.getChildren().add(context.getCode().getLine3());
		vBox.getChildren().add(context.getCode().getLine4());
		vBox.getChildren().add(context.getCode().getLine5());
		vBox.getChildren().add(context.getCode().getLine6());
		vBox.getChildren().add(context.getCode().getLine7());
		Text text = new Text("\nDescription: ");
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 18));
		text.setFill(Color.BROWN);
		context.getdescription().setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
		context.getdescription().setFill(Color.BROWN);
		vBox.getChildren().add(text);	
		vBox.getChildren().add(context.getdescription());
	}
	    
	    protected void unlockGraph()
	    {
	    	for (Vertex v: graph.getListVertice())
	    		v.setOnMousePressed(addEdgeEvent);
	    	drawGraph.setOnMouseClicked(addVertexEvent);
	    	graphLocked = false;
	    }
	    
	    public void execute(ActionEvent event) {
	    	if (!graphLocked)
	    	{
	    		lockGraph();
	    	}
			context.executeOnce();
		}
	    
	    public void executeAll(ActionEvent event) {
	    	if (!graphLocked)
	    		lockGraph();
	    	context.executeAll();	
		}
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			System.out.println("Initialize");
			context = new Context();
			String[] al = {"Kruskal", "Prim"};
			speedText.setText("Speed:\n2 seconds\nper step");
			speedText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));
			speedText.setFill(Color.RED);
			alBox.setItems(FXCollections.observableArrayList(al));
			initNewGraph.setStyle("-fx-background-color: violet; -fx-border-color: grey; -fx-border-radius: 5;");
			reset.setStyle("-fx-background-color: coral; -fx-border-color: grey; -fx-border-radius: 5;");
			execute.setStyle("-fx-background-color: aqua; -fx-border-color: grey; -fx-border-radius: 5;");
			executeAll.setStyle("-fx-background-color: turquoise; -fx-border-color: grey; -fx-border-radius: 5;");
			speedup.setStyle("-fx-background-color: beige; -fx-border-color: grey; -fx-border-radius: 5;");
			slowdown.setStyle("-fx-background-color: beige; -fx-border-color: grey; -fx-border-radius: 5;");
			exampleGraph.setStyle("-fx-background-color: B4FF9F; -fx-border-color: grey; -fx-border-radius: 5;");
			if (this.graph != null) {
				group.getChildren().addAll(this.graph.drawableObjects());
				for(Vertex v : this.graph.getListVertice()) {
					v.setOnMousePressed(addEdgeEvent);
				}
			}
			else
				graph = new Graph();
		}
}
