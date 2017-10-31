package com.conwaygame.conway;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.conwaygame.constants.Constants;
import com.conwaygame.gui.Board;
import com.conwaygame.gui.Cell;
import com.conwaygame.gui.TimePanel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConwayApplicationTests {

	
	private TimePanel tp = new TimePanel();
	private Board b1 = new Board(tp);
	private Cell[] cells;
	private Cell[] boardCells;
	
	@Before
	public void initialize(){	
		
		cells = new Cell[Constants.NUMBER_OF_ROWS * Constants.NUMBER_OF_COLUMNS]; 
		
		for (int j = 0; j < cells.length; j++) {
			
			cells[j] = new Cell(j,b1);
		}
		
		b1.setCells(cells);
		boardCells = b1.getCells();
	}
	
	@Test
	public void populationArraytest() {	
		
		boardCells[0].setAlive(true);
			
		assertTrue((boardCells[0].isAlive()));
		
		for (int i = 1; i < boardCells.length; i++) {
			assertTrue(!(boardCells[i].isAlive()));
		}
	}
	
	@Test
	public void underCrowded() {			
					
		boardCells[0].setAlive(true);
		boardCells[1].setAlive(true);
		boardCells[2].setAlive(true);
		
		b1.newIteration();
		assertTrue(!(boardCells[0].isAlive()));
	}
	
	@Test
	public void nextGenerationToLiveWith2Neighbors(){
				
		boardCells[0].setAlive(true);
		boardCells[1].setAlive(true);
		boardCells[2].setAlive(true);
				
		b1.newIteration();
		assertTrue(boardCells[1].isAlive());
		
	}
	
	@Test
	public void nextGenerationToLiveWith3Neighbors(){
				
		boardCells[0].setAlive(true);
		boardCells[1].setAlive(true);
		boardCells[2].setAlive(true);
		boardCells[1+Constants.NUMBER_OF_COLUMNS].setAlive(true);		
		
		b1.newIteration();
		assertTrue(boardCells[1].isAlive());
		
	}
	
	@Test
	public void overcrowded(){
		
	
		boardCells[0].setAlive(true);
		boardCells[1].setAlive(true);
		boardCells[2].setAlive(true);
		boardCells[1+Constants.NUMBER_OF_COLUMNS].setAlive(true);
		boardCells[1+Constants.NUMBER_OF_COLUMNS-1].setAlive(true);
				
		b1.newIteration();
		assertTrue(!boardCells[1].isAlive());
		
	}
	
	@Test
	public void newbornForNextGeneration(){
		
		boardCells[0].setAlive(true);
		boardCells[1].setAlive(true);
		boardCells[2].setAlive(true);
		
		b1.newIteration();
		assertTrue(boardCells[1 + Constants.NUMBER_OF_COLUMNS].isAlive());
		
	}

}
