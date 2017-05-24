package de.ostfalia.weimar.reduction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * An instance of the SAT-problem: A formula in propositional calculus
 */
public class SAT {
	/**
	 * root of the expression tree.
	 */
	public Term root;
	
	/**
	 * A variable occurs in the formula which is undefined in the given assignment
	 */
	public class UndefinedVariableException extends Exception{
		private static final long serialVersionUID = 1L;
		
		public UndefinedVariableException(String var){
			super(" Variable "+var+" is undefined");
		}
	}

	/**
	 * A term in the propositional calculus.
	 */
	public interface Term {
		/**
		 * determin the value of this term under the given assignment
		 * @param assignment should contain all variables which occur in this term.
		 * @return true if the term evaluates to true under this assignment
		 * @throws UndefinedVariableException if a variable in the 
		 * 		term is not defined in the assignment.
		 */
		public abstract boolean value(HashMap<String, Boolean> assignment) 
					throws UndefinedVariableException;
	}
	
	/**
	 * A literal in the propositional calculus, can be 
	 * variable or negated variable.
	 */
	public class Literal implements Term{
		public final boolean positive;
		public final String variable;
		
		public Literal(String var){
			this.variable = var;
			this.positive = true;
		}
		public Literal(String var, boolean isPositive){
			this.variable = var;
			this.positive = isPositive;
		}
		public boolean value(HashMap<String, Boolean> assignment) 
				throws UndefinedVariableException{
			Boolean v = assignment.get(variable);
			if (v == null){
				throw new UndefinedVariableException(variable);
			}
			return positive == v ;
		}
		public String toString(){
			if (positive){ 
				return variable;
			}else{
				return "¬"+variable;
			}
		}
	}

	/**
	 * An And-clause, may contain any number of parts.
	 */
	public class And implements Term{
		public final Term[] parts;
		public And(Term[] parts){
			this.parts = parts;
		}
		public boolean value(HashMap<String, Boolean> assignment) 
				throws UndefinedVariableException{
			boolean res = true;
			for (Term part : parts){
				res &= part.value(assignment);
				if (res == false){ // shortcut evaluation
					return res;
				}
			}
			return res ;
		}
		@Override
		public String toString(){
			if (parts.length == 0){
				return "1";
			}
			if (parts.length == 1){
				return parts[0].toString();
			}
			StringBuilder sb = new StringBuilder("(");
			boolean first = true;
			for (Term part : parts){
				if (first){
					first = false;
				}else{
					sb.append("∧");
				}
				sb.append(part);
			}
			sb.append(")");
			return sb.toString();
		}

	}
	/**
	 * An Or-clause, may contain any number of parts.
	 */
	public class Or implements Term{
		public final Term[] parts;
		public Or(Term[] parts){
			this.parts = parts;
		}
		public boolean value(HashMap<String, Boolean> assignment) 
				throws UndefinedVariableException{
			boolean res = false;
			for (Term part : parts){
				res |= part.value(assignment);
				if (res == true){ // shortcut evaluation
					return res;
				}
			}
			return res ;
		}
		@Override
		public String toString(){
			if (parts.length == 0){
				return "0";
			}
			if (parts.length == 1){
				return parts[0].toString();
			}
			StringBuilder sb = new StringBuilder("(");
			boolean first = true;
			for (Term part : parts){
				if (first){
					first = false;
				}else{
					sb.append("∨");
				}
				sb.append(part);
			}
			sb.append(")");
			return sb.toString();
		}

	}
	/**
	 * A Not-clause, may contain only one term.
	 */
	public class Not implements Term{
		public final Term part;
		public Not(Term part){
			this.part = part;
		}
		public boolean value(HashMap<String, Boolean> assignment) 
				throws UndefinedVariableException{
			return !part.value(assignment);
		}

	}
	
	public SAT(Term root){
		this.root = root;
	}
	
	/**
	 * determine the value of this expression under the given assignment
	 * @param assignment should contain all variables which occur in this term.
	 * @return true if the term evaluates to true under this assignment
	 * @throws UndefinedVariableException if a variable in the 
	 * 		term is not defined in the assignment.
	 */
	public boolean value(HashMap<String, Boolean> assignment) 
			throws UndefinedVariableException{
		return root.value(assignment);
	}
	
	/**
	 * collect all variables used in this formula
	 * @return a (hash)set of all variables.
	 */
	public Set<String> collectVariables(){
		HashSet<String> res = new HashSet<String>(10);
		collectVariables(res, root);
		return res;
	}
	
	/**
	 * helper function to avoid cluttering the Term interface.
	 */
	private void collectVariables(Set<String> set, Term t){
		if (t instanceof Literal){
			set.add(((Literal)t).variable);
		}else if (t instanceof Not){
			collectVariables(set, ((Not)t).part);
		}else if (t instanceof And){
			for (Term tt: ((And)t).parts){
				collectVariables(set, tt);
			}
		}else if (t instanceof Or){
			for (Term tt: ((Or)t).parts){
				collectVariables(set, tt);
			}
		}
	}

	/**
	 * determine if this is a KNF formula.
	 * @return true if it is KNF.
	 */
	public boolean isKNF(){
		if (! (root instanceof And)){
			return false;
		}
		for (Term part : ((And)root).parts){
			if (! (part instanceof Or)){
				return false;
			}
			for (Term partpart : ((Or)part).parts){
				if (!(partpart instanceof Literal)){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * determine if this is a KNF formula with at most three literals in each clause.
	 * @return true if it is KNF.
	 */
	public boolean is3KNF(){
		if (! (root instanceof And)){
			return false;
		}
		for (Term part : ((And)root).parts){
			if (! (part instanceof Or)){
				return false;
			}
			if (((Or)part).parts.length > 3){
				return false;
			}
			for (Term partpart : ((Or)part).parts){
				if (!(partpart instanceof Literal)){
					return false;
				}
			}
		}
		return true;
	}

}
