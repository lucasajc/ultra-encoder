package com.app.ultraencoder.model;

import com.brightcove.zencoder.client.response.ZencoderInputOutputProgress;

public class InputOutputProgress{
	
	private ZencoderInputOutputProgress inputProgress;
	private ZencoderInputOutputProgress outputProgress;
	
	public InputOutputProgress(ZencoderInputOutputProgress inputProgress, ZencoderInputOutputProgress outputProgress) {
		super();
		this.inputProgress = inputProgress;
		this.outputProgress = outputProgress;
	}

	public ZencoderInputOutputProgress getInputProgress() {
		return inputProgress;
	}

	public void setInputProgress(ZencoderInputOutputProgress inputProgress) {
		this.inputProgress = inputProgress;
	}

	public ZencoderInputOutputProgress getOutputProgress() {
		return outputProgress;
	}

	public void setOutputProgress(ZencoderInputOutputProgress outputProgress) {
		this.outputProgress = outputProgress;
	}
	
	
}