package com.coding404.myweb.command;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreeVO {
	
	private Integer free_list_num; //유저리스트 번호
	
	@NotBlank(message = "제목은 필수 입니다")
	private String free_list_title; //제목
	
	@NotBlank(message = "내용은 필수 입니다")
	private String free_list_content; //내용
	
	@NotBlank(message = "게시글 종류는 필수 입니다")
	private String free_list_type; //종류
	
	
	private String free_list_date; //오늘날짜
	private Integer free_list_views; //조회수
	private String free_list_writer; //작성자
	private Integer user_num;
	
}
