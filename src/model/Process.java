package model;

public class Process {
	
	private String name;
	private long time;
	private boolean isBlocked;
	private long size;
	private String namePartition;
	
	public Process(String name, long time,long size, boolean isBlocked, String namePartition) {
		super();
		this.name = name;
		this.time = time;
		this.size = size;
		this.isBlocked = isBlocked;
		this.namePartition = namePartition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	public String getNamePartition() {
		return namePartition;
	}

	public void setNamePartition(String namePartition) {
		this.namePartition = namePartition;
	}

	public Object[] toObjectVector() {
        return new Object[] {name, time, size, isBlocked, namePartition};
    }

}
