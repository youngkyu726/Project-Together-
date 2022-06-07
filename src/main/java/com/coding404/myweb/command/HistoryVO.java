package com.coding404.myweb.command;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryVO {
	
	private Integer adopt_list_num;
	private String user_id;
	private String adopt_list_name;
	private String adopt_list_type;
	private String adopt_list_content;
	private String adopt_list_date;
	private String adopt_list_progress;
	
	private int animal_num;

}
