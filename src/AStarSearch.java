import java.util.List;

public class AStarSearch implements SearchAlgorithm {
	boolean solutionFound = false;
	int nbNodeExpansions = 0;
	int maxFrontierSize = 0;
	Node solutionNode = null;

	private Heuristics heuristics;
	public AStarSearch(Heuristics h) {
		this.heuristics = h;
	}

	@Override
	public void doSearch(Environment env) {
		heuristics.init(env);
		//while solution not found, keep searching
		
		// TODO implement the search here
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
			return getPlan().size();
		}
		return -1;
	}

}
