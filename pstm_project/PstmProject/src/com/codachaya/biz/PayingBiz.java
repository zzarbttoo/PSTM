package com.codachaya.biz;

import java.util.List;

import com.codachaya.dao.PayingDao;
import com.codachaya.dto.LessonDto;
import com.codachaya.dto.ReceiptinfoDto;
import com.codachaya.dto.UserDto;

public class PayingBiz {

	private PayingDao payingdao = new PayingDao();

	public List<UserDto> selectTrainerList() {

		System.out.println("DEBUG");

		return payingdao.selectTrainerList();

	}

	public UserDto selectTrainerOne(int trainerUserId) {

		return payingdao.selectTrainerOne(trainerUserId);

	}

	public UserDto selectNormalUserOne(int normalUser) {

		return payingdao.selectNormalUserOne(normalUser);

	}

	public List<UserDto> selectTrainerPaging(int offset, int count) {

		System.out.println("biz" + offset + "," + count);
		return payingdao.selectTrainerPaging(offset, count);

	}

	public List<UserDto> selectNormalUserPaging(int offset, int count) {

		return payingdao.selectNormalUserPaging(offset, count);
	}

	public int getTrainerCount() {

		return payingdao.getTrainerCount();

	}

	public int getNormalUserCount() {

		return payingdao.getNormalUserCount();

	}

	public int insertNewReceiptinfo(ReceiptinfoDto newReceiptinfoDto) {

		return payingdao.insertNewReceiptinfo(newReceiptinfoDto);
	}

	public int selectIdReceipt(String imp_uid) {

		return payingdao.selectIdReceipt(imp_uid);
	}

	// 기존에 있던 jsonString 과 새로 추가된 학생 데이터를 합쳐준다
	public int updateLessonStudents(LessonDto updatelessonDto) {

		LessonDto BeforeupdateDto;
		String beforelessonString;
		String updatelessonString;
		String addlessonString;
		int trainerNum = updatelessonDto.getUserid();

		// 문자열 처리
		BeforeupdateDto = payingdao.selectOneLessonDto(trainerNum);

		addlessonString = updatelessonDto.getStudentid().substring(1);
		System.out.println("addlessonString" + addlessonString);

		beforelessonString = BeforeupdateDto.getStudentid();
		System.out.println("beforelessonString" + beforelessonString);

		if (BeforeupdateDto.getStudentcount() == 0) {

			updatelessonString = beforelessonString.substring(0, beforelessonString.length() - 1) + addlessonString;

		} else {
			updatelessonString = beforelessonString.substring(0, beforelessonString.length() - 1) + ","
					+ addlessonString;

		}

		System.out.println("updatelessonString" + updatelessonString);
		updatelessonDto.setStudentid(updatelessonString);
		updatelessonDto.setStudentcount(BeforeupdateDto.getStudentcount() + 1);
		System.out.println(updatelessonDto.getClasscontent());

		return payingdao.updateLessonStudents(updatelessonDto);
	}
}
