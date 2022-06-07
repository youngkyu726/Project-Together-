package com.coding404.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentVO {

	private Integer comment_num; //댓글번호
	private String comment_id; //유저id
	private Integer user_list_num; //유저리스트번호
	private String comment_content; //댓글내용
	private String comment_date; //날짜
	private String update_comment; //댓글수정
}
