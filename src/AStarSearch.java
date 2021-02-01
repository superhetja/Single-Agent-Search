import java.util.List;


import java.util.ArrayList;
import java.util.HashMap;

public class AStarSearch implements SearchAlgorithm {
	boolean solutionFound = false;
	HashMap<State, Integer> hashMap = new HashMap<State, Integer>();
	int nbNodeExpansions = 0;
	int maxFrontierSize = 0;
	Node solutionNode = null;
	ArrayList<Node> frontier;
	Node currentNode;
	Environment environment;

	private Heuristics heuristics;
	public AStarSearch(Heuristics h) {
		this.heuristics = h;
	}

	@Override
	public void doSearch(Environment env) {
		environment = env;
		heuristics.init(environment);
		currentNode = new Node(environment.getCurrentState(), Integer.MAX_VALUE);
		frontier = new ArrayList<Node>();
		addNode(currentNode);

		// TODO implement the search here
		while(!(solutionFound)){
			currentNode= findNextNode();
			extendNode();

			// Better to do this when we expand but....
			if (currentNode.evaluation==0){
				solutionFound= true;
				solutionNode = currentNode;
			}
		}
	}

	@Override
	public List<Action> getPlan() {
		if (!(solutionNode instanceof Node) ) {
			System.out.println("inside getPlan() : solutionNode is null"); 
			return null;
		}
		List<Action> ret = solutionNode.getPlan();
		// TODO Auto-generated method stub
		if (ret != null && !ret.isEmpty()) {
			return ret;
		} else if ( ret == null) {
			System.out.println("inside getPlan() : solutionNode.getPlan() is null"); 
			
		} else if (ret.isEmpty()) {
			System.out.println("inside getPlan() : solutionNode.getPlan() is empty"); 
			
		}
		return null;
	}
	private Node findNextNode() {
		Node next = frontier.get(0);
		for (int i = 1; i < frontier.size(); i++) {
			if (frontier.get(i).evaluation < next.evaluation) {
				next = frontier.get(i);
			}
		}
		return next;
	}
	private void extendNode(){
		if (currentNode != null) {
			List<Action> legal_moves= environment.legalMoves(currentNode.state);
			for (int i=0; i< legal_moves.size(); i++) {
				State tmpState = environment.getNextState(currentNode.state, legal_moves.get(i));
				if (!containsState(tmpState)) {
					// Add extended nodes to the frontier
					addNode(new Node(currentNode, tmpState, legal_moves.get(i), heuristics.eval(tmpState)));
					nbNodeExpansions++;
				}
			}
			// Remove the old node frontier
			removeNode(currentNode);
		}

	}
	private void addNode(Node node){
		frontier.add(node);
		hashMap.put(node.state, node.state.hashCode());
	}
	private void removeNode(Node node){
		// cheack if the fronter is bigger then ever, before we decrees it
		if (frontier.size()> maxFrontierSize){
			maxFrontierSize=frontier.size();
		}
		frontier.remove(node);
	}
	private boolean containsState(State s) {
		return hashMap.containsKey(s);

	}

	@Override
	public int getNbNodeExpansions() {
		// TODO Auto-generated method stub
		return nbNodeExpansions;
	}

	@Override
	public int getMaxFrontierSize() {
		// TODO Auto-generated method stub
		return maxFrontierSize;
	}

	@Override
	public int getPlanCost() {
		// TODO Auto-generated method stub

		if (solutionNode != null) {
			return solutionNode.evaluation;
		}
		System.out.println("inside getPlanCost() : solutionNode.getPlan() is still null"); 
		return -1;
	}

}
