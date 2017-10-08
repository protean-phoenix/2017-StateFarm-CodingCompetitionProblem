package sf.codingcomp.blocks.solution;

import java.util.ArrayList;
import java.util.Iterator;

import sf.codingcomp.blocks.BuildingBlock;
import sf.codingcomp.blocks.CircularReferenceException;

public class BuildingBlockImpl implements BuildingBlock {
	
	private BuildingBlock blockOver = null;
	private BuildingBlock blockUnder = null;
	private ArrayList<BuildingBlock> list = null;

    @Override
    public Iterator<BuildingBlock> iterator() {
    	list = new ArrayList<BuildingBlock>();
    	BuildingBlock bottomBlock = this;
    	while(bottomBlock.findBlockUnder() != null) {
    		bottomBlock = bottomBlock.findBlockUnder();
    	}
    	while(bottomBlock.findBlockOver() != null) {
    		list.add(bottomBlock);
    		bottomBlock = bottomBlock.findBlockOver();
    	}
    	list.add(bottomBlock);
        return list.iterator();
    }

    @Override
    public void stackOver(BuildingBlock b) {
    	boolean impossible = false;
    	
    	Iterator<BuildingBlock> it = this.iterator();
    	if(b == null) {
    	}
    	else {
	    	while(it.hasNext()) {
	    		if(it.next() == b) {
	    			impossible = true;
	    		}
	    	}
    	}
    	if((impossible && this.findBlockUnder() == null) || (impossible && this.findBlockUnder() != b)) {
    		throw(new CircularReferenceException());
    	}
    	if(this.findBlockUnder() == null) {
    	}
    	else {
    		this.findBlockUnder().stackUnder(null);
    	}
    	if(b == null) {
    		blockUnder = b;
    	}
	    else {
	    	blockUnder = b;
	    	if(b.findBlockOver() == null) {
	    		b.stackUnder(this);
	    	}
	    	else if(b.findBlockOver() != this) {
	    		b.findBlockOver().stackOver(null);
	    		b.stackUnder(this);
	    	}
    	}
    }

    @Override
    public void stackUnder(BuildingBlock b) {
    	boolean impossible = false;
    	
    	Iterator<BuildingBlock> it = this.iterator();
    	if(b == null) {
    	}
    	else {
	    	while(it.hasNext()) {
	    		if(it.next() == b) {
	    			impossible = true;
	    		}
	    	}
    	}
    	if((impossible && this.findBlockOver() == null) || (impossible && this.findBlockOver() != b)) {
    		throw(new CircularReferenceException());
    	}
    	/*if(this.findBlockOver() == null) {
    	}
    	else{
    		this.findBlockOver().stackOver(null);
    	}*/
    	if(b == null) {
    		blockOver = b;
    	}
	    else {
	    	blockOver = b;
	    	if(b.findBlockUnder() == null) {
	    		b.stackOver(this);
	    	}
	    	else if(b.findBlockUnder() != this) {
	    		b.findBlockUnder().stackUnder(null);
	    		b.stackOver(this);
	    	}
    	}
    }

    @Override
    public BuildingBlock findBlockUnder() {
        return blockUnder;
    }

    @Override
    public BuildingBlock findBlockOver() {
        return blockOver;
    }

}
