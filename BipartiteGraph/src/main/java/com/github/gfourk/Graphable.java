package com.github.gfourk;

public interface Graphable {
	public void addVertexTypeA(String name) throws NodeExistsException;
	public void addVertexTypeB(String name) throws NodeExistsException;
	public void addEdge(String sA, String sB) throws NodeNotExistsException, NodesAlreadyConnectedException;
	public boolean connected(String sA,String sB);
	public String[] getTypeAVertices();
	public String[] getTypeBVertices();
	public String[] getEdgesConnectedToNodeA(String sA) throws NodeNotExistsException;
	public String[] getEdgesConnectedToNodeB(String sB) throws NodeNotExistsException;
	public String[] getNeighboursFromA(String sA) throws NodeNotExistsException;
	public String[] getNeighboursFromB(String sB) throws NodeNotExistsException;
	public String toString();
	public void clear();

}//end of interface Graphable
