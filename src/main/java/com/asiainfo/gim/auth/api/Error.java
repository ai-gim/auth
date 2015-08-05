package com.asiainfo.gim.auth.api;

import javax.ws.rs.core.Response.Status;

public class Error
{
	private Status status;
	private String message;
	private String detail;

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}
}
