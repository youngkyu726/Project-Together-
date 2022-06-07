package com.coding404.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecVO {

	   
	   private Integer user_num;
	   private String user_id;
	   private String user_pw;
	   private String user_name;
	   private String user_age;
	   private String user_gen;
	   private String user_email;
	   private String user_phone;
	   private Integer user_salary;
	   private Integer user_size;
	   private Integer user_stay;
	   private String user_like;
	   private String optradio;
	   private int rec_list_num;
	   private int user_cnt;
	   
	   
	   
	   private Integer ANIMAL_NUM;
	   private String ANIMAL_TYPE;
	   private String ANIMAL_SIZE;
	   private String ANIMAL_NAME;
	   private String ANIMAL_GENDER;
	   private Integer ANIMAL_AGE;
	   private String ANIMAL_CONTENT;
	   private String ANIMAL_DISEASE;
	   private String ANIMAL_NEUTRAL;
	   private Integer ANIMAL_COST;
	   private Integer ANIMAL_TIME;
	   private String ANIMAL_FILENAME;
	   private String ANIMAL_PATH;
	   private String ANIMAL_UUID;
	   private String ANIMAL_DATE;
	   private Integer ADMIN_NUM;
	   private Integer LIST_NUM;
	   
	   private Integer sal_cnt;
	   private Integer stay_cnt;
	   private Integer size_cnt;
	   private Integer like_cnt;
	   private Integer opt_cnt;

	}

