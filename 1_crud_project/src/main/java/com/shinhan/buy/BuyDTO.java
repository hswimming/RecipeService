package com.shinhan.buy;

import java.sql.Date;

import com.shinhan.recipe.RecipeDTO;

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
public class BuyDTO extends RecipeDTO {
	private int b_no;
	private int m_code;
	private int r_no;
	private Date b_date;
}