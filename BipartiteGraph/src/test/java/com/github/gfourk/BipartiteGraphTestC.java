package com.github.gfourk;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BipartiteGraphTestC {

	private Graphable graph;

	@BeforeEach
	public void setUp() {
		graph = new BipartiteGraph();
		
		try {
			graph.addVertexTypeA("Giorgos");
			
			graph.addVertexTypeB("Fourkis");
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
	public void shouldNotHaveNeighbours() throws NodeNotExistsException {
		assertNull(graph.getNeighboursFromA("Giorgos"));
		assertNull(graph.getNeighboursFromB("Fourkis"));
	}
}
