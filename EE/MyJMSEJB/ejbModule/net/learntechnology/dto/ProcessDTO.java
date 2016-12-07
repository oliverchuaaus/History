package net.learntechnology.dto;

import java.io.Serializable;

import net.learntechnology.util.ProcessResult;

public class ProcessDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private ProcessResult processResult;
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ProcessResult getProcessResult() {
		return processResult;
	}

	public void setProcessResult(ProcessResult processResult) {
		this.processResult = processResult;
	}

	@Override
    public String toString() {
        return "name: "+name+", processResult: "+processResult;
    }

}
