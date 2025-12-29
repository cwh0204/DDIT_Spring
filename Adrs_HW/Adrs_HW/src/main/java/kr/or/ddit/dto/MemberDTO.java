package kr.or.ddit.dto;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "memId")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberDTO {
	
	private String memId;
	private String memPass;
	private String memName;
	@ToString.Exclude
	private transient String memRegno1;
	@ToString.Exclude
	private transient String memRegno2;
	private LocalDate memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private LocalDate memMemorialday;
	private Integer memMileage;
	private Boolean memDelete;
	private String memRole;
}
