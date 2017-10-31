package com.conwaygame.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;

import com.conwaygame.constants.Constants;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	private Cell[] cells;
	private Set<Integer> cellsToDie = new HashSet<>();
	private Set<Integer> cellsToBorn = new HashSet<>();
	private int countLiveNeighbours=0;
	private TimePanel timePanel;
	
	public Board(TimePanel timePanel){
		this.timePanel = timePanel;
		
		initializeLayout();
		paintBoard();
	}

	private void initializeLayout() {
		setCells(new Cell[Constants.NUMBER_OF_ROWS*Constants.NUMBER_OF_COLUMNS]);
		GridLayout gridLayout = new GridLayout(Constants.NUMBER_OF_ROWS,Constants.NUMBER_OF_COLUMNS);
		setLayout(gridLayout);
	}

	public void refreshBoard(int id) {
		getCells()[id].setAlive(true);
		getCells()[id].setBackground(Color.decode(Constants.GREEN_COLOR));
	}

	public void refreshBoard1(int id,boolean b) {
		getCells()[id].setAlive(true);
		getCells()[id].setBackground(Color.decode(Constants.GREEN_COLOR));
	}
	
	public void newIteration(){
		
		
		for(int i=0;i<Constants.NUMBER_OF_ROWS*Constants.NUMBER_OF_COLUMNS;i++){
			
			countLiveNeighbours=0;
		
		
			if (isFirstRow(i)) {
				if (isFirstCol(i)) {
					if(right(i)) countLiveNeighbours++; 
					if(bottom(i)) countLiveNeighbours++; 
					if(bottomRight(i)) countLiveNeighbours++; 
					
				} else if(isLastCol(i)) {
					if(left(i)) countLiveNeighbours++; 
					if(bottomLeft(i)) countLiveNeighbours++; 
					if(bottom(i)) countLiveNeighbours++; 

				}else {
					if(left(i)) countLiveNeighbours++; 
					if(right(i)) countLiveNeighbours++; 
					if(bottomLeft(i)) countLiveNeighbours++; 
					if(bottom(i)) countLiveNeighbours++; 
					if(bottomRight(i)) countLiveNeighbours++; 
					
				}
			} else if(isLatRow(i)) {
				if (isFirstCol(i)) {
					if(top(i)) countLiveNeighbours++; 
					if(topRight(i)) countLiveNeighbours++; 
					if(right(i)) countLiveNeighbours++; 
					
				} else if(isLastCol(i)) {
					if(topLeft(i)) countLiveNeighbours++; 
					if(top(i)) countLiveNeighbours++; 
					if(left(i)) countLiveNeighbours++; 

				}else {
					if(topLeft(i)) countLiveNeighbours++; 
					if(top(i)) countLiveNeighbours++; 
					if(topRight(i)) countLiveNeighbours++; 
					if(left(i)) countLiveNeighbours++; 
					if(right(i)) countLiveNeighbours++; 
					
				}
			} else if(isFirstCol(i)) {    //First cols without corner
				
				if(top(i)) countLiveNeighbours++; 
				if(topRight(i)) countLiveNeighbours++; 
				if(right(i)) countLiveNeighbours++; 
				if(bottom(i)) countLiveNeighbours++; 
				if(bottomRight(i)) countLiveNeighbours++; 
				
			} else if(isLastCol(i)) {	 //last cols without corner
				
				if(topLeft(i)) countLiveNeighbours++; 
				if(top(i)) countLiveNeighbours++; 
				if(left(i)) countLiveNeighbours++; 
				if(bottomLeft(i)) countLiveNeighbours++; 
				if(bottom(i)) countLiveNeighbours++; 
				
			}else {
				
				if(topLeft(i)) countLiveNeighbours++; 
				if(top(i)) countLiveNeighbours++; 
				if(topRight(i)) countLiveNeighbours++; 
				if(left(i)) countLiveNeighbours++; 
				if(right(i)) countLiveNeighbours++; 
				if(bottomLeft(i)) countLiveNeighbours++; 
				if(bottom(i)) countLiveNeighbours++; 
				if(bottomRight(i)) countLiveNeighbours++; 
				
/*				if( cells[i-1].isAlive() ) countLiveNeighbours++;       //left
				if( cells[i+1].isAlive() ) countLiveNeighbours++;		//right
				if( cells[i-Constants.NUMBER_OF_ROWS+1].isAlive() ) countLiveNeighbours++;  //top right
				if( cells[i-Constants.NUMBER_OF_ROWS-1].isAlive() ) countLiveNeighbours++;  //top left
				if( cells[i-Constants.NUMBER_OF_ROWS].isAlive() ) countLiveNeighbours++;    //top
				if( cells[i+Constants.NUMBER_OF_ROWS+1].isAlive() ) countLiveNeighbours++;  //bottom right
				if( cells[i+Constants.NUMBER_OF_ROWS].isAlive() ) countLiveNeighbours++;	//bottom 
				if( cells[i+Constants.NUMBER_OF_ROWS-1].isAlive() ) countLiveNeighbours++;	//bottom left
*/			
				
			}
				
			
			// cell with exactly 3 neighbors --> becomes a live cell
			if( countLiveNeighbours == 3 && !getCells()[i].isAlive()) cellsToBorn.add(i);
			
			// underpopulation and overpopulation
			if( countLiveNeighbours < 2 || countLiveNeighbours > 3){
				cellsToDie.add(i);
			}
			
			// any live cell with 2 or 3 neighbors lives on to the next generation
			if( countLiveNeighbours == 3 || countLiveNeighbours == 2 && getCells()[i].isAlive() ) cellsToBorn.add(i);
		
		}
		
		repaintBoard();
		timePanel.refresh();
	}

	public void repaintBoard(){
		
		for(Integer integer : cellsToBorn){
			getCells()[integer].setBackground(Color.decode(Constants.GREEN_COLOR));
			getCells()[integer].setAlive(true);
		}
		
		for(Integer integer : cellsToDie){
			getCells()[integer].setBackground(Color.WHITE);
			getCells()[integer].setAlive(false);
		}
		
		cellsToBorn.clear();
		cellsToDie.clear();
	}
	
	public void paintBoard(){
		for(int i=0;i<Constants.NUMBER_OF_ROWS*Constants.NUMBER_OF_COLUMNS;i++){
			
			getCells()[i]=new Cell(i,this);
			getCells()[i].setAlive(false);
			add(getCells()[i]);
		}	
	}
	
	public void resetBoard() {
		for(int i=0;i<Constants.NUMBER_OF_ROWS*Constants.NUMBER_OF_COLUMNS;i++){
			getCells()[i].setBackground(Color.WHITE);
			getCells()[i].setAlive(false);		
		}		
	}
	public boolean isFirstRow(final int i){		
		return (i<=Constants.NUMBER_OF_ROWS-1);		
	}
	public boolean isFirstCol(final int i){		
		return i% Constants.NUMBER_OF_ROWS==0 ;		
	}
	public boolean isLastCol(final int i){		
		return (  i % Constants.NUMBER_OF_ROWS)-(Constants.NUMBER_OF_ROWS -1)  ==0 ;		
	}
	public boolean isLatRow(final int i){		
		return i>((Constants.NUMBER_OF_ROWS-1)* Constants.NUMBER_OF_COLUMNS-1)  ;		
	}
	

	public boolean topLeft(int i){
		return ( getCells()[i-Constants.NUMBER_OF_ROWS-1].isAlive() );
	}
	public boolean top(int i){
		return ( getCells()[i-Constants.NUMBER_OF_ROWS].isAlive() );
	}
	public boolean topRight(int i){
		return ( getCells()[i-Constants.NUMBER_OF_ROWS+1].isAlive() );
	}
	public boolean left(int i){
		return ( getCells()[i-1].isAlive() );
	}
	public boolean right(int i){
		return ( getCells()[i+1].isAlive() );
	}
	public boolean bottomLeft(int i){
		return ( getCells()[i+Constants.NUMBER_OF_ROWS-1].isAlive() );
	}
	public boolean bottom(int i){
		return ( getCells()[i+Constants.NUMBER_OF_ROWS].isAlive() );
	}
	public boolean bottomRight(int i){
		return ( getCells()[i+Constants.NUMBER_OF_ROWS+1].isAlive() );
	}

	public Cell[] getCells() {
		return cells;
	}

	public void setCells(Cell[] cells) {
		this.cells = cells;
	}
	
}
