package kr.or.ddit.dto;

import lombok.Data;


/**
 * 패키지 구조는 간단한 프로젝트이기떄문에 따로 member와 adrs로 나누지않음
 */
@Data
public class AdrsDTO {
	private Integer adrsNo;
	private String memId;
	private String adrsName;
	private String adrsTel;
	private String adrsAdd;
	private String adrsMail;
}
