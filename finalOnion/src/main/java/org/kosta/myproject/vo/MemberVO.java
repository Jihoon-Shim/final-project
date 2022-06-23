package org.kosta.myproject.vo;

import java.io.Serializable;

import lombok.Data;
@Data
public class MemberVO implements Serializable{
	private static final long serialVersionUID = 6440047762418162093L;
	private String memberId;
	private String memberName;
	private String memberPassword;
	private String memberAddress;
	private String memberTel;
	private int onionPoint;
	private String memberNickname;
	private String memberPicture;
	private int enabled;
}
