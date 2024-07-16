package com.shinhan.member;

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
public class MemberDTO extends RecipeDTO {
	private int m_code;
	private String m_id;
	private String m_pwd;
	private String m_phone;
	private String m_address;
	private int m_money;
}