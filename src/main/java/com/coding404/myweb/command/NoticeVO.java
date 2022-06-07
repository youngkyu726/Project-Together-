package com.coding404.myweb.command;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeVO {
	
	//자동증가값 
	private LocalDateTime ad_list_date;
	private Integer ad_list_num; 
	
	
	//글 등록
	@NotBlank(message = "제목은 필수 입니다")
	private String ad_list_title;
	private String ad_list_writer;
	@NotBlank(message = "글 작성은 필수입니다")
	private String ad_list_content;
	
	
	//관리자 정보
	private String admin_id;
	//@Pattern(regexp = "[0-9]")
	private String admin_pw;
	
	
}
