package com.shinhan.recipe;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.shinhan.member.MemberDTO;
import com.shinhan.recipe_content.RecipeContentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
	private int r_no;
	private String r_name;
	private String r_food;
	private int r_price;
	private Date r_date;
	private String rc_content;
}