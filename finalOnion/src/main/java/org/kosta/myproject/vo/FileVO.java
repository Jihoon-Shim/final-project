package org.kosta.myproject.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9213072334964418928L;
	public int fileNo;
	public String fileName;
	private String contentType;
}
