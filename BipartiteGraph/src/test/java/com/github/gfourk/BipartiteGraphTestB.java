package com.github.gfourk;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BipartiteGraphTestB {

	private Graphable graph;

	@BeforeEach
	public void setUp() {
		graph = new BipartiteGraph();
	}
	
	@Test
	public void shouldNotHaveVertices() {
		assertNull(graph.getTypeAVertices());
		assertNull(graph.getTypeBVertices());
	}
	
}
