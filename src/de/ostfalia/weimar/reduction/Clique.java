package de.ostfalia.weimar.reduction;

import java.util.Set;

public class Clique<T> {
	Graph<T> graph;
	int n;
	
	public boolean validate(Set<T> clique){
		for (T start: clique){
			for (T end: clique){
				if (start != end && ! graph.edges.contains(
						graph.new Edge(start,end))){
					return false;
				}
			}
		}
		return true;
	}
}
