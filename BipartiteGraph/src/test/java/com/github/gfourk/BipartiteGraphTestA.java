package com.github.gfourk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BipartiteGraphTestA {
	
	private Graphable graph;

	@BeforeEach
	public void setUp() {
		graph = new BipartiteGraph();
		
		try {
			graph.addVertexTypeA("Babis");
			graph.addVertexTypeA("Basilis");
			graph.addVertexTypeA("Dimitris");
			graph.addVertexTypeA("Giannis");
			graph.addVertexTypeA("Giorgos");
			graph.addVertexTypeA("Makis");;
			graph.addVertexTypeA("Marios");
			graph.addVertexTypeA("Panos");
			graph.addVertexTypeA("Sakis");
			graph.addVertexTypeA("Telis");
			graph.addVertexTypeA("Xristos");
			
			graph.addVertexTypeB("Fourkis");
			graph.addVertexTypeB("Gianakakis");
			graph.addVertexTypeB("Giorgis");
			graph.addVertexTypeB("Kalaitzakis");
			graph.addVertexTypeB("Lilikakis");
			graph.addVertexTypeB("Lilis");
			graph.addVertexTypeB("Lilitsis");
			graph.addVertexTypeB("Papadakis");
			graph.addVertexTypeB("Stamatakis");
			graph.addVertexTypeB("Valsamakis");
		}
		catch ( NodeExistsException e ) {
			System.out.println("Should not be thrown for these sets");
		}
	}
	
	@AfterEach
	public void tearDown() {
		graph.clear();
	}
	
	@Test
	public void willThrowAfterAddVertexTypeA() {
		Throwable throwable = assertThrows(NodeExistsException.class,
				() -> graph.addVertexTypeA("Babis") );
		assertEquals("Node already exists!",
				throwable.getMessage());
	}
	
	@Test
	public void willThrowAfterAddVertexTypeB() {
		Throwable throwable = assertThrows(NodeExistsException.class,
				() -> graph.addVertexTypeB("Fourkis") );
		assertEquals("Node already exists!",
				throwable.getMessage());
	}
	
	@Test
	public void afterAddEdgeWillThrow() {
		Throwable throwable = assertThrows(NodeNotExistsException.class,
				() -> graph.addEdge("Kostas","Fourkis") );
		assertEquals("Node does not exist!",
				throwable.getMessage());
		throwable = assertThrows(NodeNotExistsException.class,
				() -> graph.addEdge("Giorgos","Stamatiou") );
		assertEquals("Node does not exist!",
				throwable.getMessage());
	}
	
	@Test
	public void afterAddEdgeWillBeConnected() throws NodeNotExistsException, NodesAlreadyConnectedException {
		graph.addEdge("Giorgos","Fourkis");
		assertTrue(graph.connected("Giorgos","Fourkis"));
	}
	
	@Test
	public void afterAddEdgeWillNotBeConnected() throws NodeNotExistsException, NodesAlreadyConnectedException {
		graph.addEdge("Makis","Lilitsis");
		assertFalse(graph.connected("Giorgos","Fourkis"));
	}
	
	@Test
	public void SetAContains() {
		MatcherAssert.assertThat(Arrays.asList(graph.getTypeAVertices()), 
				Matchers.containsInAnyOrder("vertice Babis", "vertice Basilis","vertice Dimitris",
						"vertice Giannis","vertice Giorgos","vertice Makis","vertice Marios",
						"vertice Panos","vertice Sakis","vertice Telis","vertice Xristos"));
	}
	
	@Test
	public void SetBContains() {
		MatcherAssert.assertThat(Arrays.asList(graph.getTypeBVertices()), 
				Matchers.containsInAnyOrder("vertice Fourkis", "vertice Gianakakis","vertice Giorgis",
						"vertice Kalaitzakis","vertice Lilikakis","vertice Lilis","vertice Lilitsis",
						"vertice Papadakis","vertice Stamatakis","vertice Valsamakis"));
	}

	@Test
	public void SetANeighbours() throws NodeNotExistsException {
		MatcherAssert.assertThat(Arrays.asList(graph.getNeighboursFromA("Giannis")), 
				Matchers.containsInAnyOrder("neighbour Babis", "neighbour Basilis","neighbour Dimitris",
						"neighbour Giorgos","neighbour Makis","neighbour Marios",
						"neighbour Panos","neighbour Sakis","neighbour Telis","neighbour Xristos"));
	}
	
	@Test
	public void SetBNeighbours() throws NodeNotExistsException {
		MatcherAssert.assertThat(Arrays.asList(graph.getNeighboursFromB("Lilis")), 
				Matchers.containsInAnyOrder("neighbour Fourkis", "neighbour Gianakakis","neighbour Giorgis",
						"neighbour Kalaitzakis","neighbour Lilikakis","neighbour Lilitsis",
						"neighbour Papadakis","neighbour Stamatakis","neighbour Valsamakis"));
	}
	
	@Test
	public void shouldNotHaveNeighbours() throws NodeNotExistsException {
		Throwable throwable = assertThrows(NodeNotExistsException.class,
				() -> graph.getNeighboursFromA("Kostas") );
		assertEquals("Node does not exist!",
				throwable.getMessage());
		throwable = assertThrows(NodeNotExistsException.class,
				() -> graph.getNeighboursFromB("Stamatiou") );
		assertEquals("Node does not exist!",
				throwable.getMessage());
	}
	
	@Test
	public void shouldNotHaveEdges() throws NodeNotExistsException {
		Throwable throwable = assertThrows(NodeNotExistsException.class,
				() -> graph.getEdgesConnectedToNodeA("Kostas") );
		assertEquals("Node does not exist!",
				throwable.getMessage());
		throwable = assertThrows(NodeNotExistsException.class,
				() -> graph.getEdgesConnectedToNodeB("Stamatiou") );
		assertEquals("Node does not exist!",
				throwable.getMessage());
	}
	
	@Test
	public void afterAddEdgesWillHaveEdges() throws NodeNotExistsException, NodesAlreadyConnectedException {
		graph.addEdge("Giorgos", "Fourkis");
		graph.addEdge("Giorgos", "Gianakakis");
		graph.addEdge("Giorgos", "Papadakis");
		MatcherAssert.assertThat(Arrays.asList(graph.getEdgesConnectedToNodeA("Giorgos")), 
				Matchers.containsInAnyOrder("edge Fourkis", "edge Gianakakis","edge Papadakis"));
		MatcherAssert.assertThat(Arrays.asList(graph.getEdgesConnectedToNodeB("Fourkis")), 
				Matchers.containsInAnyOrder("edge Giorgos"));
		MatcherAssert.assertThat(Arrays.asList(graph.getEdgesConnectedToNodeB("Gianakakis")), 
				Matchers.containsInAnyOrder("edge Giorgos"));
		MatcherAssert.assertThat(Arrays.asList(graph.getEdgesConnectedToNodeB("Papadakis")), 
				Matchers.containsInAnyOrder("edge Giorgos"));
	}
	

	@Test
	public void afterAddEdgeTwoTimesWillThrow() throws NodeNotExistsException, NodesAlreadyConnectedException {
		graph.addEdge("Giorgos", "Fourkis");
		;
		Throwable throwable = assertThrows(NodesAlreadyConnectedException.class,
				() -> graph.addEdge("Giorgos", "Fourkis") );
		assertEquals("The nodes are already connected!",
				throwable.getMessage());
	}
	
	@Test
	public void edgesShouldBeNull() throws NodeNotExistsException {
		assertNull(graph.getEdgesConnectedToNodeA("Giorgos"));
		assertNull(graph.getEdgesConnectedToNodeB("Fourkis"));
	}
}
