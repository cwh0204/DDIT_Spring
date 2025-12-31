package kr.or.ddit.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
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
	
	@NotBlank(groups = { Default.class, DeleteGroup.class })
	private String memId;
	@NotBlank(groups = { Default.class, DeleteGroup.class })
	@Length(min = 4, max = 8, groups = { Default.class, DeleteGroup.class })
	private String memPass;
	@NotBlank
	@Length(max = 6)
	private String memName;

	@NotBlank(groups = InsertGroup.class)
	@Length(min = 6, max = 6, groups = InsertGroup.class)
	@ToString.Exclude
	private transient String memRegno1;
	@NotBlank(groups = InsertGroup.class)
	@Length(min = 7, max = 7, groups = InsertGroup.class)
	@ToString.Exclude
	private transient String memRegno2;

	private LocalDate memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	@NotBlank
	@Pattern(regexp = "010-\\d{3,4}-\\d{4}")
	private String memHp;
	@NotBlank
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private LocalDate memMemorialday;

	@Min(value = 0, groups = UpdateGroup.class)
	private Integer memMileage;
	private Boolean memDelete;
	private String memRole;
}
