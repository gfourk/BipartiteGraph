package com.github.gfourk;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class BipartiteGraph implements Graphable{

	private Map<String, Set<String>> A;
	private Map<String, Set<String>> B;
	
	public BipartiteGraph()
	{
		A = new TreeMap<String, Set<String>>();
		B = new TreeMap<String, Set<String>>();
	}
	
	
	public void addVertexTypeA(String name) throws NodeExistsException
	{
		boolean check = checkAll(name);
		if ( check )
		{
			A.put(name, new TreeSet<String>());
		}
		else throw new NodeExistsException("Node already exists!");
	}

	public void addVertexTypeB(String name) throws NodeExistsException
	{
		boolean check = checkAll(name);
		if ( check )
		{
			B.put(name, new TreeSet<String>());
		}
		else throw new NodeExistsException("Node already exists!");
	}
	
	public void addEdge(String sA, String sB) throws NodeNotExistsException, NodesAlreadyConnectedException
	{
		if ( !A.containsKey(sA) ) throw new NodeNotExistsException("Node does not exist!");
		if ( !B.containsKey(sB) ) throw new NodeNotExistsException("Node does not exist!");
	
		boolean isconnected = connected(sA, sB);
		
		if ( !isconnected )
		{
			Set<String> ssa = A.get(sA);
			ssa.add(sB);
			Set<String> ssb = B.get(sB);
			ssb.add(sA);
		}
		else throw new NodesAlreadyConnectedException("The nodes are already connected!");
	}
	
	public boolean connected ( String sA , String sB )
	{
		Set<String> ssa = A.get(sA);
		if ( ssa.contains(sB) ) return true;
		Set<String> ssb = B.get(sB);
		if ( ssb.contains(sA) ) return true;
		return false;	
	}
	
	public String[] getTypeAVertices()
	{
		int size = A.size();
		if ( size == 0 ) return null;
		
		String[] vertices = new String[size];
		
		int i = 0;
		for ( String name : A.keySet() )
		{
			vertices[i] = "vertice "+name;
			i++;
		}
		return vertices;
	}
	
	public String[] getTypeBVertices()
	{
		int size = B.size();
		if ( size == 0 ) return null;
		
		String[] vertices = new String[size];
		
		int i = 0;
		for ( String name : B.keySet() )
		{
			vertices[i] = "vertice "+name;
			i++;
		}
		return vertices;
	}
	
	public String[] getEdgesConnectedToNodeA(String sA) throws NodeNotExistsException
	{
		if ( !A.containsKey(sA) ) throw new NodeNotExistsException("Node does not exist!");
	
		Set<String> ss = A.get(sA);
		int len = ss.size();
		if ( len == 0 ) return null;
		
		String[] edges = new String[len];
		
		int i = 0;
		for ( String name : ss )
		{
			edges[i] = "edge "+name;
			i++;
		}
		return edges;
	}
	
	public String[] getEdgesConnectedToNodeB(String sB) throws NodeNotExistsException
	{
		if ( !B.containsKey(sB) ) throw new NodeNotExistsException("Node does not exist!");
		
		Set<String> ss = B.get(sB);
		int len = ss.size();
		if ( len == 0 ) return null;
		
		String[] edges = new String[len];
		
		int i = 0;
		for ( String name : ss )
		{
			edges[i] = "edge "+name;
			i++;
		}
		return edges;
	}
	
	public String[] getNeighboursFromA(String sA) throws NodeNotExistsException
	{
		if ( !A.containsKey(sA) ) throw new NodeNotExistsException("Node does not exist!");
		
		int size = A.size();
		if ( size == 1 ) return null;
		
		String[] vertices = new String[size-1];
		
		int i = 0;
		for ( String name : A.keySet() )
		{
			if ( !name.equals(sA) ) { 
				vertices[i] = "neighbour "+name;
				i++;
			}
		}
		return vertices;
	}
	
	public String[] getNeighboursFromB(String sB) throws NodeNotExistsException
	{
		if ( !B.containsKey(sB) ) throw new NodeNotExistsException("Node does not exist!");
		
		int size = B.size();
		if ( size == 1 ) return null;
		
		String[] vertices = new String[size-1];
		
		int i = 0;
		for ( String name : B.keySet() )
		{
			if ( !name.equals(sB) ) { 
				vertices[i] = "neighbour "+name;
				i++;
			}
		}
		return vertices;
	}
	
	private boolean checkAll(String name)
	{
		
		if( A.containsKey(name) )return false;
		
		if( B.containsKey(name) )return false;
		
		return true;
	}
	
	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nset A\n");
		for ( String name : A.keySet() )
		{
			buffer.append("node "+name+"\n" );
		}
		buffer.append("\nset B\n");
		for ( String name : B.keySet() )
		{
			buffer.append("node "+name+"\n" );
		}
		return buffer.toString();
	}

	@Override
	public void clear() {
		A.clear();
		B.clear();
	}
	
}
