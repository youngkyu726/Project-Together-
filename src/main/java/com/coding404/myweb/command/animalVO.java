package com.coding404.myweb.command;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class animalVO {
	
	private Integer ANIMAL_NUM;
	
	@NotBlank(message = "동물 종류는 필수 입니다.")
	private String ANIMAL_TYPE;
	
	@Pattern(regexp = "[lms]" , message = "크기는 필수 입니다")
	private String ANIMAL_SIZE;
	
	@NotBlank(message = "동물 이름은 필수 입니다.")
	private String ANIMAL_NAME;
	
	@Pattern(regexp = "[mw]" , message = "성별은 필수 입니다")
	private String ANIMAL_GENDER;
	
	@Min(value = 0 , message = "나이는 0 이상입니다")
	@NotNull(message = "동물 나이는 필수 입니다.")
	private Integer ANIMAL_AGE;
	
	@NotBlank(message = "동물 설명은 필수 입니다.")
	private String ANIMAL_CONTENT;
	

	@Pattern(regexp = "[yn]" , message = "동물 질병 유무는 필수 입니다")
	private String ANIMAL_DISEASE;
	
	@NotBlank(message = "동물 중성화여부는 필수 입니다.")
	private String ANIMAL_NEUTRAL;
	
	@Min(value = 0 , message = "유지비는 0 이상입니다")
	@NotNull(message = "동물 유지비는 필수 입니다.")
	private Integer ANIMAL_COST;
	
	@Min(value = 0 , message = "돌봄필요시간은 0 이상입니다")
	@NotNull(message = "동물 돌봄필요시간은 필수 입니다.")
	private Integer ANIMAL_TIME;
	
	
	private Integer ADMIN_NUM;
	private Integer LIST_NUM;
	
	
	private String ANIMAL_FILENAME;
	private String ANIMAL_PATH;
	private String ANIMAL_UUID;
	
	private String ANIMAL_DATE;
	
	
}
