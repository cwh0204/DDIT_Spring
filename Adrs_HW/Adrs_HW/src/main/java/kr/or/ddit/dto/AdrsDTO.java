package kr.or.ddit.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * insert
 * 	길이
 * 	adrsName 30
 *  adrsTel 20
 *  adrsAdd 200
 *  adrsMail 100
 * update
 * adrsNo NotBlank
 * delete
 * adrsNo NotBlank
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class AdrsDTO {
	
	@NotNull(groups = { UpdateGroup.class, DeleteGroup.class })
	private Integer adrsNo;
	private String memId;
	@Length(max = 30)
	private String adrsName;
	@Length(max = 20)
	@Pattern(regexp = "010-\\d{3,4}-\\d{4}")
	private String adrsTel;
	@Length(max = 200)
	private String adrsAdd;
	@Length(max = 100)
	@Email
	private String adrsMail;
}
