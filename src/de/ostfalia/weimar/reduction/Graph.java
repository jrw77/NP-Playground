package de.ostfalia.weimar.reduction;

import java.util.HashSet;
import java.util.Set;

public class Graph<T> {
	public class Edge {
		T from;
		T to;
		public Edge(T from , T to){
			this.from = from;
			this.to = to;
		}
		@Override
		public boolean equals(Object other){
			if (! (other instanceof Graph.Edge)){
				return false;
			}
			@SuppressWarnings("unchecked")
			Edge othere = (Edge)other;
			return this.from == othere.from && this.to== othere.to;
		}
		@Override
		public int hashCode(){
			return from.hashCode() + to.hashCode();
		}
	}
	public Set<Edge> edges;
	public Set<T> nodes;
	
	public Graph(){
		nodes = new HashSet<T> ();
		edges = new HashSet<Edge> ();
	}
	
	public void add(Edge e){
		nodes.add(e.from);
		nodes.add(e.to);
		edges.add(e);
	}

	public void addBidirectional(Edge e){
		nodes.add(e.from);
		nodes.add(e.to);
		edges.add(e);
		edges.add(new Edge(e.to, e.from));
	}

}
