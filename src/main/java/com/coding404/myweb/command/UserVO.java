package com.coding404.myweb.command;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {
	
	
	private Integer user_num;
	
	@NotBlank(message = "아이디는 필수 입니다.")
	private String user_id;
	
	@NotBlank(message = "비밀번호는 필수 입니다.")
	private String user_pw;
	
	@NotBlank(message = "이름은 필수 입니다.")
	private String user_name;
	
	@NotBlank(message = "생년월일은 필수 입니다.")
	@Pattern(regexp = "[0-9]{4}", message = "yyyy-mm-dd 형식입니다.")
	private String user_year;
	
	@NotBlank(message = "생년월일은 필수 입니다.")
	private String user_month;
	
	@NotBlank(message = "생년월일은 필수 입니다.")
	@Pattern(regexp = "[0-9]{2}", message = "yyyy-mm-dd 형식입니다.")
	private String user_day;
	
	private String user_age;
	
	@NotBlank(message = "성별은 필수 입니다.")
	private String user_gen;
	
	@NotBlank(message = "이메일은 필수 입니다.")
	@Email(message = "이메일 형식이어야 합니다.") //공백을 허용시키기 때문에 NotBlank와 같이 사용
	private String user_email;
	
	@NotBlank(message = "전화번호는 필수 입니다.")
	@Pattern(regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}", message = "전화번호는 000-0000-0000형식 입니다.")
	private String user_phone;
	
	
	
	
	
	@NotNull(message = "필수 입력사항 입니다.")
	private Integer user_salary;
	
	@NotNull(message = "필수 입력사항 입니다.")
	@Min(value = 0, message = "평수는 0평 이상입니다.")
	private Integer user_size;
	
	@NotNull(message = "필수 입력사항 입니다.")
	@Min(value = 0, message = "시간은 0시간 이상입니다.")
	private Integer user_stay;
	
	@NotBlank(message = "선호동물선택은 필수 입니다.")
	private String user_like;
	
	@NotBlank(message = "동물여부선택은 필수 입니다.")
	private String optradio;
	
	
	
	
	
	
	
	private int rec_list_num;

}
