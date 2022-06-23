package org.kosta.myproject.vo;

import java.io.Serializable;

import lombok.Data;
@Data
public class TempVO implements Serializable{
	private static final long serialVersionUID = 6440047762418162093L;
	private MemberVO memberVO;
	private int temp;
	private int tempCount;
}
